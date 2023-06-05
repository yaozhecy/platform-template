package server.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.platform.generation.server.domain.dto.CommonVersionDto;
import com.cy.platform.generation.server.domain.model.CommonVersionPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通用版本管理接口
 *
 * @author 56807
 */
public interface CommonVersionMapper extends BaseMapper<CommonVersionPo> {
    /**
     * 根据业务ID查询当前版本
     *
     * @param id 业务ID
     * @return 当前信息
     */
    List<CommonVersionDto> queryCurrentByBusId(@Param("id") String id);
}
