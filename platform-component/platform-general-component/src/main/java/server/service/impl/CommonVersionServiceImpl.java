package server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.platform.generation.server.domain.dto.CommonVersionDto;
import com.cy.platform.generation.server.domain.mapper.CommonVersionMapper;
import com.cy.platform.generation.server.domain.model.CommonVersionPo;
import com.cy.platform.generation.server.service.ICommonVersionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 公共版本管理
 *
 * @author 56807
 */
@Service
public class CommonVersionServiceImpl extends ServiceImpl<CommonVersionMapper, CommonVersionPo>
    implements ICommonVersionService {

    @Override
    public Optional<CommonVersionDto> queryCurrentVersion(String businessId) {
        //查询当前业务
        List<CommonVersionDto> list = baseMapper.queryCurrentByBusId(businessId);
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(list.get(0));
    }

    @Override
    public List<Integer> queryVersionList(String businessId) {
        return baseMapper.selectList(new LambdaQueryWrapper<CommonVersionPo>()
                .eq(CommonVersionPo::getBusinessCode, businessId)).stream()
            .map(CommonVersionPo::getVersion).collect(Collectors.toList());
    }

    @Override
    public Integer increment(String businessId) {
        return null;
    }
}
