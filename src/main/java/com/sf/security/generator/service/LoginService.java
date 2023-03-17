package com.sf.security.generator.service;

import com.sf.security.generator.domain.SysUser;
import com.sf.security.vo.ResponseResult;

public interface LoginService {

    ResponseResult login(SysUser sysUser);

    ResponseResult logout();
}
