package com.cy.generate.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.generate.core.model.ColumnInfo;
import com.cy.generate.core.model.TableInfo;
import com.cy.generate.domain.model.TableInfoPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库表Mapper
 *
 * @author 56807
 */
@Mapper
public interface TableInfoMapper extends BaseMapper<TableInfoPo> {
    /**
     * 查库表详情
     *
     * @param id ID
     * @return 库表详情
     */
    TableInfo queryTableInfo(@Param("id") String id);

    /**
     * 列列表
     *
     * @param id ID
     * @return 列列表
     */
    List<ColumnInfo> queryColumn(@Param("id") String id);

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
