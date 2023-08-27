package com.cy.generate.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.generate.common.ServiceAssert;
import com.cy.generate.common.ServiceException;
import com.cy.generate.core.FileTemplateEngine;
import com.cy.generate.core.JdbcToJavaHelper;
import com.cy.generate.core.TemplateEngine;
import com.cy.generate.core.model.TableInfo;
import com.cy.generate.domain.mapper.DataSourceMapper;
import com.cy.generate.domain.mapperx.BaseGeneratorMapper;
import com.cy.generate.domain.mapperx.MySqlGeneratorMapper;
import com.cy.generate.domain.mapperx.SqlServerGeneratorMapper;
import com.cy.generate.domain.model.ColumnInfoPo;
import com.cy.generate.domain.model.DataSourcePo;
import com.cy.generate.domain.model.TableInfoPo;
import com.cy.generate.domain.vo.CodeInfoVo;
import com.cy.generate.domain.vo.CodeParamsVo;
import com.cy.generate.domain.vo.CommonDataVo;
import com.cy.generate.domain.vo.DataSourceVo;
import com.cy.generate.domain.vo.code.CodeListVo;
import com.cy.generate.domain.vo.field.FieldMapItemVo;
import com.cy.generate.domain.vo.template.TemplateInfoVo;
import com.cy.generate.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.velocity.VelocityContext;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 生成服务
 *
 * @author 56807
 */
@Service
@Slf4j
public class GenerationServiceImpl extends ServiceImpl<DataSourceMapper, DataSourcePo>
        implements IGenerationService {
    @Autowired
    private IDataSourceService dataSourceService;
    @Autowired
    private IColumnInfoService columnInfoService;
    @Autowired
    private ITableInfoService tableInfoService;
    @Autowired
    private ITemplateService templateService;
    @Autowired
    private TemplateEngine engine;
    @Autowired
    private FileTemplateEngine fileTemplateEngine;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void generation(String ds) {
        //step 1.查询数据源信息
        DataSourceVo vo = dataSourceService.queryInfo(ds);
        if (vo == null) {
            return;
        }
        //step 2.读取表数据
        List<ColumnInfoPo> columnInfoPos = new ArrayList<>();
        List<TableInfoPo> tableInfoPos = new ArrayList<>();
        try (DruidDataSource dataSource = DruidDataSourceBuilder.create().build()) {
            //step 1.连接数据库
            dataSource.setDriverClassName(vo.getDbType().getClassDriverName());
            dataSource.setUrl(vo.getUrl());
            dataSource.setUsername(vo.getUser());
            dataSource.setPassword(vo.getPasswd());
            try {
                dataSourceService.checkDataSource(dataSource);
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw new ServiceException("数据库连接失败");
            }
            //step 3.创建SqlSessionFactory
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);
            sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath*:mappingx/*.xml"));
            SqlSessionFactory sqlSessionFactory = sessionFactoryBean.getObject();
            if (sqlSessionFactory == null) {
                return;
            }

            //执行数据查询
            try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
                BaseGeneratorMapper mapper;
                switch (vo.getDbType()) {
                    case MYSQL:
                        mapper = sqlSession.getMapper(MySqlGeneratorMapper.class);
                        break;
                    default:
                        mapper = sqlSession.getMapper(SqlServerGeneratorMapper.class);
                }
                List<Map<String, Object>> maps = mapper.queryList(new HashMap<>(16));
                for (Map<String, Object> map : maps) {
                    TableInfoPo tableInfoPo = new TableInfoPo();
                    tableInfoPo.setId(IdWorker.get32UUID());
                    tableInfoPo.setDsId(ds);
                    tableInfoPo.setTableName((String) map.get("tableName"));
                    tableInfoPo.setTableDesc((String) map.get("tableComment"));
                    tableInfoPo.setEntityName(JdbcToJavaHelper.convertToClassName(
                            tableInfoPo.getTableName()));
                    tableInfoPo.setEnglishName(tableInfoPo.getEntityName());

                    List<Map<String, String>> columns = mapper.queryColumns((String) map.get("tableName"));
                    for (Map<String, String> column : columns) {
                        ColumnInfoPo colPo = new ColumnInfoPo();
                        colPo.setTableId(tableInfoPo.getId());
                        colPo.setDsId(ds);
                        colPo.setColumnKey("PRI".equals(column.get("columnKey")) ? 0 : 1);
                        colPo.setColumnName(column.get("columnName"));
                        colPo.setPropertyName(column.get("columnName"));
                        colPo.setEnglishName(column.get("columnName"));
                        colPo.setColumnType(column.get("dataType"));
                        colPo.setDataType(JdbcToJavaHelper.convertToJava(colPo.getColumnType()));
                        colPo.setColumnComment(column.get("columnComment"));
                        columnInfoPos.add(colPo);
                    }
                    tableInfoPos.add(tableInfoPo);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        }
        tableInfoService.generateTableInfo(ds, tableInfoPos);
        columnInfoService.generateColumnInfo(ds, columnInfoPos);
    }

    @Override
    public CodeInfoVo generationCode(String dsId, String tableId) {
        //step 1.查询数据
        TableInfo tableInfo = tableInfoService.queryGenTableInfo(tableId);
        CommonDataVo columnInfo = dataSourceService.queryCommonDataVo(dsId);

        //step 2.使用引擎封装参数
        CodeInfoVo codeInfoVo = engine.generate(columnInfo, tableInfo);
        List<TableInfo> list = new ArrayList<>();
        list.add(tableInfo);

        //step 3.生成Sql
        codeInfoVo.setSql(engine.generateSql(list));
        return codeInfoVo;
    }

    @Override
    public CodeListVo generationCode(String templateGroupId, String dsId, String tableId) {
        //step 1.查询系统模板
        List<TemplateInfoVo> templateInfoVos = templateService.queryInfoList(templateGroupId);
        ServiceAssert.isTure(templateInfoVos.isEmpty(), "生成失败，模板为空");
        //step 2.查询表信息
        TableInfo tableInfo = tableInfoService.queryGenTableInfo(tableId);
        //step 3.查询字段信息
        CommonDataVo columnInfo = dataSourceService.queryCommonDataVo(dsId);
        //step 4.遍历模板生成代码
        CodeListVo listVo = CodeListVo.newCodeList();
        List<FieldMapItemVo> fieldMaps = new ArrayList<>();
        VelocityContext context = fileTemplateEngine.getVelocityContext(fieldMaps,
                tableInfo, columnInfo);
        for (TemplateInfoVo templateInfoVo : templateInfoVos) {
            CodeListVo.CodeInfo codeInfo = CodeListVo.newCodeInfo(templateInfoVo.getTemplateName(),
                    "");
            //step 4.1.使用代码引擎生成代码
            String body = fileTemplateEngine.getString(templateInfoVo.getTemplatePath(), context);
            log.info("代码：" + body);
            codeInfo.setFileBody(body);
            listVo.getCode().add(codeInfo);
        }
        return listVo;
    }

    @Override
    public byte[] exportZip(CodeParamsVo params) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = null;
        try {
            zip = new ZipOutputStream(outputStream);
            for (String table : params.getTables()) {
                TableInfo tableInfo = tableInfoService.queryGenTableInfo(table);
                CommonDataVo columnInfo = dataSourceService.queryCommonDataVo(params.getDs());
                CodeInfoVo info = engine.generate(columnInfo, tableInfo);
                zip.putNextEntry(new ZipEntry("po" + File.separator + tableInfo.getEnglishName() + "Po.java"));
                IOUtils.write(info.getPo(), zip, "UTF-8");
                zip.closeEntry();
                zip.putNextEntry(new ZipEntry("dto" + File.separator + tableInfo.getEnglishName() + "Dto.java"));
                IOUtils.write(info.getDto(), zip, "UTF-8");
                zip.closeEntry();
                zip.putNextEntry(new ZipEntry("vo" + File.separator + tableInfo.getEnglishName() + "Vo.java"));
                IOUtils.write(info.getVo(), zip, "UTF-8");
                zip.closeEntry();
                zip.putNextEntry(new ZipEntry("transfor" + File.separator + tableInfo.getEnglishName() + "Transfor.java"));
                IOUtils.write(info.getTransfor(), zip, "UTF-8");
                zip.closeEntry();
                zip.putNextEntry(new ZipEntry("mapper" + File.separator + tableInfo.getEnglishName() + "Mapper.java"));
                IOUtils.write(info.getMapper(), zip, "UTF-8");
                zip.closeEntry();
                zip.putNextEntry(new ZipEntry("mapperXml" + File.separator + tableInfo.getEnglishName() + "Mapper.xml"));
                IOUtils.write(info.getMapperXml(), zip, "UTF-8");
                zip.closeEntry();
                zip.putNextEntry(new ZipEntry("service" + File.separator + "I" + tableInfo.getEnglishName() + "Service.java"));
                IOUtils.write(info.getService(), zip, "UTF-8");
                zip.closeEntry();
                zip.putNextEntry(new ZipEntry("service" + File.separator + "impl" + File.separator + tableInfo.getEnglishName() + "ServiceImpl.java"));
                IOUtils.write(info.getServiceImpl(), zip, "UTF-8");
                zip.closeEntry();
                zip.putNextEntry(new ZipEntry("controller" + File.separator + tableInfo.getEnglishName() + "Controller.java"));
                IOUtils.write(info.getController(), zip, "UTF-8");
                zip.closeEntry();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(zip);
        }
        return outputStream.toByteArray();
    }
}
