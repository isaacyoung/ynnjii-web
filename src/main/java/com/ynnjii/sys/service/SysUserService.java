package com.ynnjii.sys.service;

import com.ynnjii.base.PageResult;
import com.ynnjii.sys.domain.SysUser;
import com.ynnjii.sys.dto.SysUserVo;

public interface SysUserService {

    /**
     * 添加数据
     */
    int insertData(SysUser entry);

    /**
     * 修改数据
     */
    int updateData(SysUser entry);

    /**
     * 删除数据
     */
    int deleteData(Integer id);

    /**
     * 查询单记录
     */
    SysUser selectById(Integer id);

    /**
     * 查询多记录
     */
    PageResult selectList(SysUserVo vo);

    /**
     * 查询记录数
     */
    int selectCount(SysUserVo vo);
}