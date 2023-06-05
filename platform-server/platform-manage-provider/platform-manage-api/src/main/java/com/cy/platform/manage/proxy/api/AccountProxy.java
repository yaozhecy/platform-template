package com.cy.platform.manage.proxy.api;

import com.cy.platform.common.http.R;
import com.cy.platform.manage.domain.vo.AccountInfoVO;

public interface AccountProxy {

    R<AccountInfoVO> getAccountInfo();
}
