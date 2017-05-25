package com.ynnjii.sys.domain;

import java.util.Date;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
public class SysRole {
    /** id */
    private Integer id;

    /** 角色名称 */
    private String name;

    /** 角色类型 */
    private Byte roleType;

    /** 备注 */
    private String remark;

    /** 状态 0初始 1正常 */
    private Byte status;

    /** 创建者 */
    private Integer createBy;

    /** 创建时间 */
    private Date createDate;

    /** 更新者 */
    private Integer updateBy;

    /** 更新时间 */
    private Date updateDate;

    /** 删除标记 0正常 1删除 */
    private Integer delFlag;

    /** 获得id */
    public Integer getId() {
        return id;
    }

    /** 设置id */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获得角色名称 */
    public String getName() {
        return name;
    }

    /** 设置角色名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获得角色类型 */
    public Byte getRoleType() {
        return roleType;
    }

    /** 设置角色类型 */
    public void setRoleType(Byte roleType) {
        this.roleType = roleType;
    }

    /** 获得备注 */
    public String getRemark() {
        return remark;
    }

    /** 设置备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 获得状态 0初始 1正常 */
    public Byte getStatus() {
        return status;
    }

    /** 设置状态 0初始 1正常 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获得创建者 */
    public Integer getCreateBy() {
        return createBy;
    }

    /** 设置创建者 */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /** 获得创建时间 */
    public Date getCreateDate() {
        return createDate;
    }

    /** 设置创建时间 */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /** 获得更新者 */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /** 设置更新者 */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    /** 获得更新时间 */
    public Date getUpdateDate() {
        return updateDate;
    }

    /** 设置更新时间 */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /** 获得删除标记 0正常 1删除 */
    public Integer getDelFlag() {
        return delFlag;
    }

    /** 设置删除标记 0正常 1删除 */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}