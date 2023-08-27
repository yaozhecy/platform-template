package com.cy.generate.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.generate.domain.model.CooDocChangesPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 协作文档修改历史
 *
 * @author 56807
 */
@Mapper
public interface CooDocChangesMapper extends BaseMapper<CooDocChangesPo> {
}
