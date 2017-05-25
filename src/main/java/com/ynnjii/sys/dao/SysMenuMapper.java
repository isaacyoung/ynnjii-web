package com.ynnjii.sys.dao;

import com.ynnjii.base.BaseMapper;
import com.ynnjii.sys.domain.SysMenu;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据id删除
    */
    int deleteById(Integer id);

    /**
     * 根据id查询
    */
    SysMenu selectById(Integer id);
}