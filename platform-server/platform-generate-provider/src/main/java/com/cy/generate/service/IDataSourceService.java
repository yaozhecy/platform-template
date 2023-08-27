package com.cy.generate.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.generate.domain.model.DataSourcePo;
import com.cy.generate.domain.vo.CommonDataVo;
import com.cy.generate.domain.vo.DataSourceQueryParam;
import com.cy.generate.domain.vo.DataSourceVo;
import com.cy.generate.domain.vo.doc.DocParamVo;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据源配置
 *
 * @author 56807
 */
public interface IDataSourceService extends IService<DataSourcePo> {
    /**
     * 查询数据列表
     *
     * @param current 当前页
     * @param size    每页行数
     * @param param   参数
     * @return 数据源列表
     */
    IPage<DataSourceVo> queryDataSourcePage(long current, long size, DocParamVo param);

    /**
     * 查询数据列表
     *
     * @param param 参数
     * @return 数据源列表
     */
    List<DataSourceVo> queryDataSourceList(DataSourceQueryParam param);

    /**
     * 添加数据源
     *
     * @param dataSourceVo 数据源数据
     */
    void addSourceInfo(DataSourceVo dataSourceVo);

    /**
     * 查询数据源信息
     *
     * @param id 数据ID
     * @return 数据源
     */
    DataSourceVo queryInfo(String id);

    /**
     * 查询通用信息
     *
     * @param sourceId 数据源ID
     * @return 通用配置
     */
    CommonDataVo queryCommonDataVo(String sourceId);

    /**
     * 更新通用数据
     *
     * @param vo VO
     */
    void updateCommonDataVo(CommonDataVo vo);

    /**
     * 导入数据
     *
     * @param id 数据源ID
     * @param is 文件流
     * @throws Exception e
     */
    void importExcel(String id, InputStream is) throws Exception;

    /**
     * 删除数据源
     *
     * @param id ID
     */
    void delete(String id);

    /**
     * 数据源校验
     *
     * @param vo VO
     */
    void validDb(DataSourceVo vo);

    /**
     * 检查DataSource
     *
     * @param dataSource dataSource
     */
    void checkDataSource(DruidDataSource dataSource) throws SQLException;
}
