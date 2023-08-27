package com.cy.generate.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.generate.domain.model.CommonDataPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通用配置Mapper
 *
 * @author 56807
 */
@Mapper
public interface CommonDataMapper extends BaseMapper<CommonDataPo> {
}
