package com.ynnjii.impl;

import com.ynnjii.common.PageResult;
import com.ynnjii.entity.SysUser;
import com.ynnjii.mapper.SysUserMapper;
import com.ynnjii.service.SysUserService;
import com.ynnjii.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * 添加数据
     */
    public int insertData(SysUser entry) {
        return sysUserMapper.insertData(entry);
    }

    /**
     * 修改数据
     */
    public int updateData(SysUser entry) {
        return sysUserMapper.updateData(entry);
    }

    /**
     * 删除数据
     */
    public int deleteData(Integer id) {
        return sysUserMapper.deleteData(id);
    }

    /**
     * 查询单记录
     */
    @Transactional(readOnly = true)
    public SysUser selectById(Integer id) {
        return sysUserMapper.selectById(id);
    }

    /**
     * 查询多记录
     */
    @Transactional(readOnly = true)
    public PageResult selectList(SysUserVo vo) {
        PageResult pageResult = new PageResult();
        pageResult.setTotal(sysUserMapper.selectCount(vo));
        pageResult.setRows(sysUserMapper.selectList(vo));
        return pageResult;
    }

    /**
     * 查询记录数
     */
    @Transactional(readOnly = true)
    public int selectCount(SysUserVo vo) {
        return sysUserMapper.selectCount(vo);
    }
}