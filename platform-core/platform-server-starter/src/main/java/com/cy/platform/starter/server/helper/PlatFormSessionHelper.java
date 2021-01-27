package com.cy.platform.starter.server.helper;

import com.cy.platform.common.SessionHelper;
import org.springframework.stereotype.Component;

@Component
public class PlatFormSessionHelper implements SessionHelper {
    
    @Override
    public Long getUserId() {
        return 0L;
    }

    @Override
    public String getUserName() {
        return "";
    }

    /**
     * 获取Token数据
     *
     * @return Token数据
     */
    @Override
    public String getToken() {
        return "";
    }
}
