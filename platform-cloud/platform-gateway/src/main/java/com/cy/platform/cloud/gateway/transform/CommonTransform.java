package com.cy.platform.cloud.gateway.transform;

import com.cy.platform.cloud.gateway.model.AuthUserEntity;
import com.cy.platform.cloud.gateway.vo.AuthUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 通用转换器
 *
 * @author develop
 */
@Mapper
public interface CommonTransform {
    CommonTransform MAPPER = Mappers.getMapper(CommonTransform.class);

    /**
     * toEntity
     *
     * @param authUserVo vo
     * @return entity
     */
    @Mapping(source = "loginUser", target = "loginName")
    @Mapping(source = "passWord", target = "loginPwd")
    AuthUserEntity toEntity(AuthUserVo authUserVo);
}
