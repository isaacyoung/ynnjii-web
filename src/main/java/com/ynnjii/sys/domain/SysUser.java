package com.ynnjii.sys.domain;

import java.util.Date;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2017/05/25
 * description：
 */
public class SysUser {
    /** id */
    private Integer id;

    /** 部门id */
    private Integer departmentId;

    /** 登录名 */
    private String loginName;

    /** 密码 */
    private String password;

    /** 工号 */
    private String no;

    /** 姓名 */
    private String name;

    /** 邮箱 */
    private String email;

    /** 电话 */
    private String phone;

    /** 手机 */
    private String mobile;

    /** 用户类型 */
    private Integer userType;

    /** 用户头像 */
    private String photo;

    /** 最后登陆IP */
    private String loginIp;

    /** 最后登陆时间 */
    private Date loginDate;

    /** 是否可登录 0正常 1不可登录 */
    private Integer loginFlag;

    /** 创建者 */
    private Integer createBy;

    /** 创建时间 */
    private Date createDate;

    /** 更新者 */
    private Integer updateBy;

    /** 更新时间 */
    private Date updateDate;

    /** 备注信息 */
    private String remarks;

    /** 删除标记 0正常 1删除 */
    private Integer delFlag;

    /** 标识 */
    private String salt;

    /** 获得id */
    public Integer getId() {
        return id;
    }

    /** 设置id */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获得部门id */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /** 设置部门id */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /** 获得登录名 */
    public String getLoginName() {
        return loginName;
    }

    /** 设置登录名 */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /** 获得密码 */
    public String getPassword() {
        return password;
    }

    /** 设置密码 */
    public void setPassword(String password) {
        this.password = password;
    }

    /** 获得工号 */
    public String getNo() {
        return no;
    }

    /** 设置工号 */
    public void setNo(String no) {
        this.no = no;
    }

    /** 获得姓名 */
    public String getName() {
        return name;
    }

    /** 设置姓名 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获得邮箱 */
    public String getEmail() {
        return email;
    }

    /** 设置邮箱 */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 获得电话 */
    public String getPhone() {
        return phone;
    }

    /** 设置电话 */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** 获得手机 */
    public String getMobile() {
        return mobile;
    }

    /** 设置手机 */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /** 获得用户类型 */
    public Integer getUserType() {
        return userType;
    }

    /** 设置用户类型 */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /** 获得用户头像 */
    public String getPhoto() {
        return photo;
    }

    /** 设置用户头像 */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /** 获得最后登陆IP */
    public String getLoginIp() {
        return loginIp;
    }

    /** 设置最后登陆IP */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /** 获得最后登陆时间 */
    public Date getLoginDate() {
        return loginDate;
    }

    /** 设置最后登陆时间 */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /** 获得是否可登录 0正常 1不可登录 */
    public Integer getLoginFlag() {
        return loginFlag;
    }

    /** 设置是否可登录 0正常 1不可登录 */
    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
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

    /** 获得备注信息 */
    public String getRemarks() {
        return remarks;
    }

    /** 设置备注信息 */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /** 获得删除标记 0正常 1删除 */
    public Integer getDelFlag() {
        return delFlag;
    }

    /** 设置删除标记 0正常 1删除 */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /** 获得标识 */
    public String getSalt() {
        return salt;
    }

    /** 设置标识 */
    public void setSalt(String salt) {
        this.salt = salt;
    }
}