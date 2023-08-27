package com.cy.generate.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.generate.common.ServiceException;
import com.cy.generate.core.JdbcToJavaHelper;
import com.cy.generate.core.TemplateEngine;
import com.cy.generate.domain.constant.DataSourceType;
import com.cy.generate.domain.mapper.CommonDataMapper;
import com.cy.generate.domain.mapper.DataSourceMapper;
import com.cy.generate.domain.model.ColumnInfoPo;
import com.cy.generate.domain.model.CommonDataPo;
import com.cy.generate.domain.model.DataSourcePo;
import com.cy.generate.domain.model.TableInfoPo;
import com.cy.generate.domain.transfor.DataSourceTransfor;
import com.cy.generate.domain.vo.CommonDataVo;
import com.cy.generate.domain.vo.DataSourceQueryParam;
import com.cy.generate.domain.vo.DataSourceVo;
import com.cy.generate.domain.vo.doc.DocParamVo;
import com.cy.generate.service.IColumnInfoService;
import com.cy.generate.service.IDataSourceService;
import com.cy.generate.service.ITableInfoService;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据源服务
 *
 * @author 56807
 */
@Service
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, DataSourcePo>
        implements IDataSourceService {
    @Autowired
    private CommonDataMapper commonDataMapper;
    @Autowired
    private ITableInfoService tableInfoService;
    @Autowired
    private IColumnInfoService columnInfoService;

    @Override
    public IPage<DataSourceVo> queryDataSourcePage(long current, long size, DocParamVo paramVo) {
        return baseMapper.selectPage(new Page<>(current, size),
                        new LambdaQueryWrapper<DataSourcePo>().like(StringUtils.isNotBlank(paramVo.getName()),
                                        DataSourcePo::getName, paramVo.getName())
                                .orderByDesc(DataSourcePo::getCreateTime))
                .convert(DataSourceTransfor.INSTANCE::toVo);
    }

    @Override
    public List<DataSourceVo> queryDataSourceList(DataSourceQueryParam param) {
        return baseMapper.selectList(new LambdaQueryWrapper<DataSourcePo>().like(StringUtils
                        .isNotBlank(param.getQuery()), DataSourcePo::getName, param.getQuery()))
                .stream().map(DataSourceTransfor.INSTANCE::toVo).collect(Collectors.toList());
    }

    @Override
    public void addSourceInfo(DataSourceVo dataSourceVo) {
        DataSourcePo po = DataSourceTransfor.INSTANCE.toPo(dataSourceVo);
        po.setCreateTime(new Date());
        //判断是否是Excel类型
        boolean isSave = StringUtils.isBlank(po.getId());
        if (isSave) {
            po.setId(IdWorker.getIdStr());
        }
        if (po.getDbType() == DataSourceType.Excel) {
            //读取Excel文件
            String filePath = "tempfile" + File.separator + dataSourceVo.getFileId() +
                    "_" + dataSourceVo.getDbName();
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(filePath);
                //执行解析Excel
                importExcel(po.getId(), fileInputStream);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new ServiceException(e.getMessage());
            } finally {
                IOUtils.closeQuietly(fileInputStream);
            }
            po.setFilePath(filePath);
        }
        if (isSave) {
            save(po);
        } else {
            updateById(po);
        }
    }

    @Override
    public DataSourceVo queryInfo(String id) {
        return DataSourceTransfor.INSTANCE.toVo(baseMapper.selectById(id));
    }

    @Override
    public CommonDataVo queryCommonDataVo(String sourceId) {
        LambdaQueryWrapper<CommonDataPo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CommonDataPo::getDataSourceId, sourceId);
        CommonDataPo po = commonDataMapper.selectOne(wrapper);
        CommonDataVo vo = new CommonDataVo();
        vo.setDataSourceId(sourceId);
        if (po == null) {
            Configuration configuration = TemplateEngine.getConfig();
            vo.setCommonPackage(configuration.getString("commonpackage"));
            vo.setPackagePath(configuration.getString("package"));
            vo.setModuleName(configuration.getString("moduleName"));
        } else {
            vo.setId(po.getId());
            vo.setDataSourceId(po.getDataSourceId());
            vo.setCommonPackage(po.getCommonPackage());
            vo.setPackagePath(po.getPackagePath());
            vo.setModuleName(po.getModuleName());
        }
        return vo;
    }

    @Override
    public void updateCommonDataVo(CommonDataVo vo) {
        if (StringUtils.isBlank(vo.getId())) {
            commonDataMapper.insert(DataSourceTransfor.INSTANCE.toPo(vo));
        } else {
            commonDataMapper.updateById(DataSourceTransfor.INSTANCE.toPo(vo));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importExcel(String id, InputStream is) throws Exception {
        //step 1.获取库表列表Sheet
        Workbook wb = WorkbookFactory.create(is);
        Sheet sheet = wb.getSheet("库表列表");
        if (sheet == null) {
            throw new ServiceException("文件sheet不存在");
        }
        List<TableInfoPo> tablePos = dealTableInfoPos(id, sheet);
        dealColumnPos(id, wb, tablePos);
    }

    /**
     * 处理库表数据
     *
     * @param id    号源ID
     * @param sheet Sheet
     * @return 库表列表
     */
    private List<TableInfoPo> dealTableInfoPos(String id, Sheet sheet) {
        //step 2.解析库表列表数据
        int rows = sheet.getLastRowNum();
        List<TableInfoPo> list = tableInfoService.list(new LambdaQueryWrapper<TableInfoPo>()
                .eq(TableInfoPo::getDsId, id).orderByDesc(TableInfoPo::getTbSort));
        int sort = 0;
        if (list != null && !list.isEmpty()) {
            sort = list.get(0).getTbSort();
        }
        List<TableInfoPo> tablePos = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            Row row = sheet.getRow(i);
            String tableName = this.getCellValue(row, 1);
            if (StringUtils.isBlank(tableName)) {
                continue;
            }
            TableInfoPo tablePo = new TableInfoPo();
            tablePo.setId(IdWorker.get32UUID());
            tablePo.setDsId(id);
            tablePo.setTbSort(i + sort);
            tablePo.setChineseName(tableName);
            tablePo.setTableName(getCellValue(row, 2));
            tablePo.setEntityName(JdbcToJavaHelper.convertToClassName(
                    getCellValue(row, 2)));
            tablePo.setEnglishName(this.getCellValue(row, 3));
            String desc = this.getCellValue(row, 4);
            if (StringUtils.isNotBlank(desc)) {
                tablePo.setTableDesc(desc);
            } else {
                tablePo.setTableDesc(this.getCellValue(row, 1));
            }
            tablePo.setStatus(0);
            tablePo.valid();
            tablePos.add(tablePo);
        }
        if (tablePos.isEmpty()) {
            throw new ServiceException("库表列表为空，导入失败");
        }

        //step 4.批量保存节点数据
        Set<String> tableSet = tablePos.stream().map(TableInfoPo::getTableName)
                .collect(Collectors.toSet());
        //step 5.检查是否已经保存了表格信息
        LambdaQueryWrapper<TableInfoPo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TableInfoPo::getDsId, id).in(TableInfoPo::getTableName, tableSet);
        List<TableInfoPo> poList = tableInfoService.list(queryWrapper);
        Map<String, TableInfoPo> poMap = poList.stream().collect(Collectors.toMap(
                TableInfoPo::getTableName, n -> n, (n1, n2) -> n1));
        List<TableInfoPo> saveList = new ArrayList<>();
        List<TableInfoPo> updateTbList = new ArrayList<>();
        for (TableInfoPo tablePo : tablePos) {
            TableInfoPo tmpPo = poMap.get(tablePo.getTableName());
            if (tmpPo == null) {
                saveList.add(tablePo);
                continue;
            }
            tablePo.setId(tmpPo.getId());
            updateTbList.add(tmpPo);
        }
        tableInfoService.saveBatch(saveList);
        tableInfoService.updateBatchById(updateTbList);
        return tablePos;
    }

    /**
     * 处理列数据
     *
     * @param id       号源ID
     * @param wb       WB
     * @param tablePos 库表列表
     */
    private void dealColumnPos(String id, Workbook wb, List<TableInfoPo> tablePos) {
        Sheet sheet;
        List<ColumnInfoPo> list = columnInfoService.list(new LambdaQueryWrapper<ColumnInfoPo>()
                .eq(ColumnInfoPo::getDsId, id).orderByDesc(ColumnInfoPo::getColSort));
        int sort = 0;
        if (list != null && !list.isEmpty()) {
            sort = list.get(0).getColSort();
        }
        //step 3.读取库表数据
        List<ColumnInfoPo> columnPos = new ArrayList<>();
        for (TableInfoPo tablePo : tablePos) {
            sheet = wb.getSheet(tablePo.getChineseName());
            if (sheet == null) {
                throw new ServiceException(tablePo.getChineseName() + "sheet不存在");
            }
            int rows = sheet.getLastRowNum();
            for (int i = 2; i < rows; i++) {
                Row row = sheet.getRow(i);
                String columnName = this.getCellValue(row, 0);
                if (StringUtils.isBlank(columnName)) {
                    continue;
                }

                ColumnInfoPo columnPo = new ColumnInfoPo();
                columnPo.setId(IdWorker.get32UUID());
                columnPo.setDsId(id);
                columnPo.setColSort(i - 1 + sort);
                columnPo.setTableId(tablePo.getId());
                columnPo.setColumnKey("是".equals(this.getCellValue(row, 6)) ? 0 : 1);
                columnPo.setColumnName(columnName);
                columnPo.setColumnType(this.getCellValue(row, 1));
                columnPo.setDataType(JdbcToJavaHelper.convertToJava(this.getCellValue(row, 1)));
                columnPo.setEnglishName(this.getCellValue(row, 5));
                columnPo.setChineseName(this.getCellValue(row, 4));
                columnPo.setPropertyName(this.getCellValue(row, 0));
                columnPo.setColumnComment(this.getCellValue(row, 7));
                columnPo.setRequired("是".equals(this.getCellValue(row, 2)) ? 1 : 0);
                columnPo.setLength(this.getCellValue(row, 3));
                columnPo.setStatus(0);
                columnPo.valid();
                columnPos.add(columnPo);
            }
        }
        //step 4.批量保存节点数据
        Set<String> columnSet = columnPos.stream().map(ColumnInfoPo::getColumnName)
                .collect(Collectors.toSet());
        //step 5.检查是否已经保存了表格信息
        LambdaQueryWrapper<ColumnInfoPo> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(ColumnInfoPo::getDsId, id).in(ColumnInfoPo::getColumnName, columnSet);
        List<ColumnInfoPo> poList2 = columnInfoService.list(queryWrapper2);
        Map<String, ColumnInfoPo> poMap2 = poList2.stream().collect(Collectors.toMap(
                ColumnInfoPo::getColumnName, n -> n, (n1, n2) -> n1));
        List<ColumnInfoPo> saveList2 = new ArrayList<>();
        List<ColumnInfoPo> updateTbList2 = new ArrayList<>();
        for (ColumnInfoPo columnInfoPo : columnPos) {
            ColumnInfoPo tmpPo = poMap2.get(columnInfoPo.getColumnName());
            if (tmpPo == null) {
                saveList2.add(columnInfoPo);
                continue;
            }
            updateTbList2.add(tmpPo);
        }
        columnInfoService.saveBatch(saveList2);
        columnInfoService.updateBatchById(updateTbList2);
    }

    @Override
    public void delete(String id) {
        baseMapper.deleteColumnByDs(id);
        baseMapper.deleteTableByDs(id);
        baseMapper.deleteById(id);
    }

    @Override
    public void validDb(DataSourceVo vo) {
        try (DruidDataSource dataSource = DruidDataSourceBuilder.create().build()) {
            //step 1.连接数据库
            dataSource.setDriverClassName(vo.getDbType().getClassDriverName());
            dataSource.setUrl(vo.getUrl());
            dataSource.setUsername(vo.getUser());
            dataSource.setPassword(vo.getPasswd());
            checkDataSource(dataSource);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void checkDataSource(DruidDataSource dataSource) throws SQLException {
        //step 1.数据源初始化
        dataSource.init();
        //step 2.构建连接参数
        Properties physicalConnectProperties = new Properties();
        if (dataSource.getConnectProperties() != null) {
            physicalConnectProperties.putAll(dataSource.getConnectProperties());
        }
        if (StringUtils.isNotBlank(dataSource.getUsername())) {
            physicalConnectProperties.put("user", dataSource.getUsername());
        }
        if (StringUtils.isNotBlank(dataSource.getPassword())) {
            physicalConnectProperties.put("password", dataSource.getPassword());
        }
        //step 3.执行连接，连接完成后关闭
        dataSource.getDriver().connect(dataSource.getUrl(), physicalConnectProperties).close();
    }

    /**
     * 获取单元格值
     *
     * @param row    获取的行
     * @param column 获取单元格列号
     * @return 单元格值
     */
    private String getCellValue(Row row, int column) {
        if (row == null) {
            return null;
        }
        Object val = "";
        try {
            Cell cell = row.getCell(column);
            if (cell != null) {
                if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
                    val = cell.getNumericCellValue();
                    if (DateUtil.isCellDateFormatted(cell)) {
                        val = DateUtil.getJavaDate((Double) val);
                    } else {
                        if ((Double) val % 1 != 0) {
                            val = new BigDecimal(val.toString());
                        } else {
                            val = new DecimalFormat("0").format(val);
                        }
                    }
                } else if (cell.getCellType() == CellType.STRING) {
                    val = cell.getStringCellValue();
                } else if (cell.getCellType() == CellType.BOOLEAN) {
                    val = cell.getBooleanCellValue();
                } else if (cell.getCellType() == CellType.ERROR) {
                    val = cell.getErrorCellValue();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return String.valueOf(val);
    }
}
