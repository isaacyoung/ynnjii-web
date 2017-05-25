package com.ynnjii.sys.dao;

import com.ynnjii.base.BaseMapper;
import com.ynnjii.sys.domain.SysDict;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
public interface SysDictMapper extends BaseMapper<SysDict> {
    /**
     * 根据id删除
    */
    int deleteById(Integer id);

    /**
     * 根据id查询
    */
    SysDict selectById(Integer id);
}