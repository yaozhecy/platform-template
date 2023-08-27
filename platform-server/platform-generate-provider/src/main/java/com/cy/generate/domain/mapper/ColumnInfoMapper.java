package com.cy.generate.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.generate.domain.model.ColumnInfoPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 列Mapper
 *
 * @author 56807
 */
@Mapper
public interface ColumnInfoMapper extends BaseMapper<ColumnInfoPo> {
    /**
     * 刷新成临时状态
     *
     * @param id 数据ID
     * @return 行数
     */
    int updateToTemp(@Param("id") String id);

    /**
     * 删除临时数据
     *
     * @param id 数据源ID
     * @return 行数
     */
    int deleteTemp(@Param("id") String id);
}
