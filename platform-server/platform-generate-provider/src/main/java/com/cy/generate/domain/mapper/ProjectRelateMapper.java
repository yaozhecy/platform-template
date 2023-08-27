package com.cy.generate.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.generate.domain.model.ProjectRelatePo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工程关联数据
 */
@Mapper
public interface ProjectRelateMapper extends BaseMapper<ProjectRelatePo> {

    Page<?> queryProjectDataSource(String projectId);
}
