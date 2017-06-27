package com.ynnjii.sys.dto;


import com.ynnjii.sys.domain.SysMenu;

import java.util.List;

/**
 * 项目名称：车到山前后台
 * 类描述：
 * 创建人：yzh
 * 创建时间：2017/6/13
 * 备注：
 */
public class MenuTree extends SysMenu {
    private List<SysMenu> children;

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }
}
