package com.ynnjii.mapper;

import com.ynnjii.entity.SysUser;
import com.ynnjii.vo.SysUserVo;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {

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
    List<SysUser> selectList(SysUserVo vo);

    /**
     * 查询记录数
     */
    int selectCount(SysUserVo vo);
}