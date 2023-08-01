package com.cy.platform.gateway.security.bo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 平台信息
 */
public class PlatformAuthentication implements Authentication {
    private Object principal;
    private boolean authenticated;
    private String userName;
    private String credentials;

    /**
     * 根据Token构建参数
     *
     * @param token token信息
     * @return 参数
     */
    public static PlatformAuthentication build(String token) {
        PlatformAuthentication authentication = new PlatformAuthentication();
        //解析token信息
        authentication.credentials = token;
        authentication.setAuthenticated(true);
        return authentication;
    }

    /**
     * 用户名称
     */
    @Override
    public String getName() {
        return this.userName;
    }

    /**
     * 密码信息
     */
    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    /**
     * 细节信息
     */
    @Override
    public Object getDetails() {
        return null;
    }

    /**
     * 身份信息
     */
    @Override
    public Object getPrincipal() {
        return principal;
    }

    /**
     * 权限信息列表
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    /**
     * 是否认证通过
     */
    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }
}
