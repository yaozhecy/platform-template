package com.cy.platform.cloud.gateway.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.platform.cloud.gateway.db.model.AuthUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cy
 * @since 2020-06-10
 */
@Mapper
public interface AuthUserMapper extends BaseMapper<AuthUserEntity> {
}
