package com.ynnjii.sys.service.impl;

import com.ynnjii.base.PageResult;
import com.ynnjii.sys.domain.SysUser;
import com.ynnjii.sys.dao.SysUserMapper;
import com.ynnjii.sys.service.SysUserService;
import com.ynnjii.sys.dto.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * 添加数据
     */
    @Transactional
    public int insertData(SysUser entry) {
        return sysUserMapper.insertData(entry);
    }

    /**
     * 修改数据
     */
    @Transactional
    public int updateData(SysUser entry) {
        return sysUserMapper.updateData(entry);
    }

    /**
     * 删除数据
     */
    @Transactional
    public int deleteData(Integer id) {
        return sysUserMapper.deleteData(id);
    }

    /**
     * 查询单记录
     */
    public SysUser selectById(Integer id) {
        return sysUserMapper.selectById(id);
    }

    /**
     * 查询多记录
     */
    public PageResult selectList(SysUserVo vo) {
        PageResult pageResult = new PageResult();
        pageResult.setTotal(sysUserMapper.selectCount(vo));
        pageResult.setRows(sysUserMapper.selectList(vo));
        return pageResult;
    }

    /**
     * 查询记录数
     */
    public int selectCount(SysUserVo vo) {
        return sysUserMapper.selectCount(vo);
    }
}