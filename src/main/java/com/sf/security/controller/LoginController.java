package com.sf.security.controller;

import com.sf.security.generator.domain.SysUser;
import com.sf.security.generator.service.LoginService;
import com.sf.security.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/sysUser/login")
    public ResponseResult login(@RequestBody SysUser sysUser) {
        return loginService.login(sysUser);
    }

    @RequestMapping("/sysUser/logout")
    public ResponseResult logout() {
        return loginService.logout();
    }


}

