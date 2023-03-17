package com.sf.security.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sf.security.generator.domain.SysUser;
import com.sf.security.generator.mapper.SysUserMapper;
import com.sf.security.generator.service.SysUserService;
import org.springframework.stereotype.Service;

/**
* @author fanfan
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-03-07 14:39:24
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




