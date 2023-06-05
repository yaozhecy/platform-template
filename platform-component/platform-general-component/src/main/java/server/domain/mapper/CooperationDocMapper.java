package server.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.platform.generation.server.domain.model.CooperationDocPo;
import com.cy.platform.generation.server.domain.vo.CooperationDocVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 协作文档
 *
 * @author 56807
 */
@Mapper
public interface CooperationDocMapper extends BaseMapper<CooperationDocPo> {
    /**
     * 文档列表查询
     *
     * @return 文档列表
     */
    List<CooperationDocVo> querDocList();
}
