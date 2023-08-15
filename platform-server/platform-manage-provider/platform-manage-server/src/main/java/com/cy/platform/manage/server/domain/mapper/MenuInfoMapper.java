package com.cy.platform.manage.server.domain.mapper;

import com.cy.platform.manage.server.domain.po.AccountInfoPo;
import com.cy.platform.model.db.core.PlatformMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuInfoMapper  extends PlatformMapper<AccountInfoPo> {
}
