package com.cy.platform.gateway.domain.mapper;

import com.cy.platform.gateway.domain.po.AccountInfoPo;
import com.cy.platform.model.db.core.PlatformMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账号Mapper
 */
@Mapper
public interface AccountMapper extends PlatformMapper<AccountInfoPo> {
}
