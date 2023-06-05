package com.cy.platform.manage.server.domain.mapper;

import com.cy.platform.manage.server.domain.po.AccountInfoPo;
import com.cy.platform.model.db.core.PlatformMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账号Mapper
 */
@Mapper
public interface AccountMapper extends PlatformMapper<AccountInfoPo> {
}
