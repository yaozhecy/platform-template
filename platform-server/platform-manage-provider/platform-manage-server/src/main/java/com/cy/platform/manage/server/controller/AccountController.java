package com.cy.platform.manage.server.controller;

import com.cy.platform.manage.server.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService accountService;

}
