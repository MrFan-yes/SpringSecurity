package com.sf.security.generator.service.impl;

import com.sf.security.generator.domain.LoginUser;
import com.sf.security.generator.domain.SysUser;
import com.sf.security.generator.service.LoginService;
import com.sf.security.utils.JwtUtil;
import com.sf.security.utils.RedisCache;
import com.sf.security.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(SysUser sysUser) {
        // AuthenticationManager进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken
                        (sysUser.getUserName(), sysUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 如果认证没通过，给出提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        // 如果认证通过，使用userid生成一个jwt，并存入结果集返回前端
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getSysUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);

        // 把完整的用户信息存入redis，userid作为key
        redisCache.setCacheObject("login:" + id, loginUser);

        return new ResponseResult<>(200, "登录成功", map);

    }

    @Override
    public ResponseResult logout() {

        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getSysUser().getId();

        // 删除redis中的值
        redisCache.deleteObject("login:" + id);
        return new ResponseResult(200, "注销成功");

    }
}
