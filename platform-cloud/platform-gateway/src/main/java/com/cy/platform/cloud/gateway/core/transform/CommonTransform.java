package com.cy.platform.cloud.gateway.core.transform;

import com.cy.platform.cloud.gateway.db.model.AuthUserEntity;
import com.cy.platform.cloud.gateway.db.vo.AuthUserVo;
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
