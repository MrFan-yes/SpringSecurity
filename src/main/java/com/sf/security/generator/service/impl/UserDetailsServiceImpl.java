package com.sf.security.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sf.security.generator.domain.LoginUser;
import com.sf.security.generator.domain.SysUser;
import com.sf.security.generator.mapper.SysMenuMapper;
import com.sf.security.generator.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// TODO Security核心代码实现
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName, username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);

        // 如果查询不到数据就通过抛出异常来给出提示
        if (Objects.isNull(sysUser)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // TODO 根据用户查询权限信息(特殊含义的字符串) 添加到LoginUser中
        List<String> list = sysMenuMapper.selectPermsByUserId
                (sysUser.getId());  // [system:dept:list, system:test:list]


        // 封装成UserDetails对象返回
        return new LoginUser(sysUser, list);
    }
}
