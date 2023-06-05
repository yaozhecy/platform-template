package server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.platform.generation.server.domain.dto.CommonVersionDto;
import com.cy.platform.generation.server.domain.model.CommonVersionPo;

import java.util.List;
import java.util.Optional;

/**
 * 公共版本管理服务接口
 *
 * @author 56807
 */
public interface ICommonVersionService extends IService<CommonVersionPo> {
    /**
     * 查询当前业务版本
     *
     * @param businessId 业务ID
     * @return 版本号
     */
    Optional<CommonVersionDto> queryCurrentVersion(String businessId);

    /**
     * 查询版本列表
     *
     * @param businessId 业务编码
     * @return 版本列表
     */
    List<Integer> queryVersionList(String businessId);

    /**
     * 版本递增
     *
     * @param businessId 业务ID
     * @return 新版本号
     */
    Integer increment(String businessId);
}
