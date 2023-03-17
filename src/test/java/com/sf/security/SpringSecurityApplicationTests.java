package com.sf.security;

import com.sf.security.generator.domain.SysUser;
import com.sf.security.generator.mapper.SysMenuMapper;
import com.sf.security.generator.mapper.SysUserMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testUserMapper() {
        List<SysUser> sysUserList = sysUserMapper.selectList(null);
        System.out.println(sysUserList);
    }

    @Test
    public void testBcryptPasswordEncoder(){
        String encode = passwordEncoder.encode("1234");
        String encode2 = passwordEncoder.encode("1234");
        System.out.println(encode);
        System.out.println(encode2);
        // $2a$10$tq80AVNXqWFATrglro.lFu9Dmlz6f22lEuxeox8g1I14eZSGGTUpe
        boolean matches = passwordEncoder.matches("1234", "$2a$10$tq80AVNXqWFATrglro.lFu9Dmlz6f22lEuxeox8g1I14eZSGGTUpe");
        System.out.println(matches);
        boolean matches2 = passwordEncoder.matches("12334", "$2a$10$tq80AVNXqWFATrglro.lFu9Dmlz6f22lEuxeox8g1I14eZSGGTUpe");
        System.out.println(matches2);
    }

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Test
    public void testSelectPermsByUserId(){
        List<String> perms = sysMenuMapper.selectPermsByUserId(1L);
        System.out.println(perms);
    }

}
