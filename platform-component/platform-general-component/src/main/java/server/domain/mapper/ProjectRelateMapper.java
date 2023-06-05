package server.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.platform.generation.server.domain.model.ProjectRelatePo;

/**
 * 工程关联数据
 */
public interface ProjectRelateMapper extends BaseMapper<ProjectRelatePo> {

    Page<?> queryProjectDataSource(String projectId);
}
