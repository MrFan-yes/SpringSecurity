package com.sf.security.generator.mapper;

import com.sf.security.generator.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author fanfan
 * @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
 * @createDate 2023-03-11 15:54:47
 * @Entity generator.domain.SysMenu
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectPermsByUserId(Long id);

}




