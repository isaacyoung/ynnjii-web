package com.ynnjii.sys.service;

import com.ynnjii.base.BaseService;
import com.ynnjii.sys.domain.SysMenu;
import com.ynnjii.sys.dto.MenuTree;

import java.util.List;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
public interface SysMenuService extends BaseService<SysMenu> {
    /**
     * 获取左侧菜单
     * @return
     */
    List<MenuTree> getMenuTree();
}