package com.cy.generate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.generate.domain.model.ColumnInfoPo;
import com.cy.generate.domain.vo.ColumnInfoVo;
import com.cy.generate.domain.vo.ColumnListVo;

import java.util.List;

/**
 * 数据源配置
 *
 * @author 56807
 */
public interface IColumnInfoService extends IService<ColumnInfoPo> {
    /**
     * 查询列列表
     *
     * @param id 库表ID
     * @return 列列表
     */
    List<ColumnInfoVo> queryColumnList(String id);

    /**
     * 列信息
     *
     * @param id 列ID
     * @return 列信息
     */
    ColumnInfoVo queryColumnInfo(String id);

    /**
     * 批量更新数据
     *
     * @param list 列表
     */
    void updateColumnInfo(ColumnListVo list);

    /**
     * 生成列数据
     *
     * @param ds            数据源ID
     * @param columnInfoPos 列数据
     */
    void generateColumnInfo(String ds, List<ColumnInfoPo> columnInfoPos);
}
