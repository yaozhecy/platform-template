package com.cy.generate.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cy.generate.domain.model.DataSourcePo;
import com.cy.generate.domain.vo.DataSourceVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源查询接口
 *
 * @author 56807
 */
@Mapper
public interface DataSourceMapper extends BaseMapper<DataSourcePo> {

    IPage<DataSourceVo> queryPage();

    /**
     * 根据数据源删除库表
     *
     * @param ds 数据源
     * @return 行数
     */
    int deleteTableByDs(String ds);

    /**
     * 根据数据源删除列数据
     *
     * @param ds 数据源
     * @return 行数
     */
    int deleteColumnByDs(String ds);
}
