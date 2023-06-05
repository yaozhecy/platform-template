package com.cy.platform.gateway.security;

import com.cy.platform.gateway.domain.bo.AccountInfoBO;
import com.cy.platform.gateway.security.bo.PlatformAuthentication;
import com.cy.platform.gateway.security.bo.PlatformUserDetailsBO;
import com.cy.platform.gateway.service.IAccountManage;
import com.cy.platform.common.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractUserDetailsReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Service
public class PlatformAuthManager extends AbstractUserDetailsReactiveAuthenticationManager {
    @Autowired
    private IAccountManage accountManage;

    public PlatformAuthManager() {
        this.setPasswordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected Mono<UserDetails> retrieveUser(String username) {
        if ("admin".equals(username)) {
            AccountInfoBO accountInfo = new AccountInfoBO();
            accountInfo.setCode("1");
            accountInfo.setAccountName("admin");

            return Mono.just(new PlatformUserDetailsBO());
        }
        return Mono.empty();
    }

    /**
     * 认证接口
     */
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            return Mono.justOrEmpty(authentication);
        }

        PlatformAuthentication platformAuthentication = (PlatformAuthentication) authentication;

        //step 1、解析token
        String token = (String) platformAuthentication.getCredentials();
        Optional<Jws<Claims>> optional = JWTUtils.parseToken(token);
        optional.ifPresentOrElse(jws -> {
            jws.getBody().get("accountCode", String.class);
            platformAuthentication.setAuthenticated(true);
        }, () -> platformAuthentication.setAuthenticated(false));

        return Mono.justOrEmpty(platformAuthentication);
    }
}
