package com.ynnjii.sys.service.impl;

import com.ynnjii.base.BaseServiceImp;
import com.ynnjii.sys.dao.SysMenuMapper;
import com.ynnjii.sys.domain.SysMenu;
import com.ynnjii.sys.dto.MenuTree;
import com.ynnjii.sys.service.SysMenuService;
import com.ynnjii.sys.service.SysUserService;
import com.ynnjii.utils.ContainerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
@Service
public class SysMenuServiceImp extends BaseServiceImp<SysMenu, SysMenuMapper> implements SysMenuService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<MenuTree> getMenuTree() {
//        SysMenu menu = new SysMenu();
//        menu.setDelFlag(DelEnums.NORMAL.getCode());
//        menu.setMenuType((byte) 0);
//
//        Page page = new Page(1,10000);
//        page.setOrderBy("sort,id");
//        List<SysMenu> menuList = selectByPage(menu,page);
        Set<SysMenu> menuList = sysUserService.findMenuByUsername(getUserName());
        if (menuList == null || menuList.size() == 0) {
            return new ArrayList<>();
        }
        return ContainerUtils.changeToMenuTree(menuList.stream().collect(Collectors.toList()));
    }

}