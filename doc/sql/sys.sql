

CREATE DATABASE `ynnjiiweb` DEFAULT CHARACTER SET utf8 ;

use `ynnjiiweb`;

CREATE TABLE `sys_user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `department_id` INT NULL COMMENT '部门id',
  `login_name` varchar(100)  NOT NULL COMMENT '登录名',
  `password` varchar(100)  NOT NULL COMMENT '密码',
  `no` varchar(100)  DEFAULT NULL COMMENT '工号',
  `name` varchar(100)  NOT NULL COMMENT '姓名',
  `email` varchar(100)  DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(100)  DEFAULT NULL COMMENT '电话',
  `mobile` varchar(100)  DEFAULT NULL COMMENT '手机',
  `user_type` INT  DEFAULT NULL COMMENT '用户类型',
  `photo` varchar(200)  DEFAULT NULL COMMENT '用户头像',
  `login_ip` varchar(100)  DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `login_flag` INT  NOT NULL DEFAULT 0 COMMENT '是否可登录 0正常 1不可登录',
  `create_by` INT  NOT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` INT  NOT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255)  DEFAULT NULL COMMENT '备注信息',
  `del_flag` INT  NOT NULL DEFAULT 0 COMMENT '删除标记 0正常 1删除',
  `salt` varchar(50)  NULL COMMENT '标识',
  PRIMARY KEY (`id`))
COMMENT = '用户表';

CREATE TABLE `sys_role` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` VARCHAR(100) NULL COMMENT '角色名称',
  `en_name` VARCHAR(100) NULL COMMENT '角色编码',
  `role_type` TINYINT(4) NULL COMMENT '角色类型',
  `remark` VARCHAR(300) NULL COMMENT '备注',
  `status` TINYINT(4) NULL COMMENT '状态 0初始 1正常',
  `create_by` INT  NOT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` INT  NOT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` INT  NOT NULL DEFAULT 0 COMMENT '删除标记 0正常 1删除',
  PRIMARY KEY (`id`))
  COMMENT = '角色表';

CREATE TABLE `sys_user_role` (
  `user_id` INT NOT NULL  COMMENT '用户id',
  `role_id` INT  NOT NULL COMMENT '角色id')
  COMMENT = '用户角色关联表';

CREATE TABLE `sys_menu` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` VARCHAR(50) NULL COMMENT '菜单名称',
  `parent_id` INT NULL COMMENT '上级菜单ID',
  `menu_type` TINYINT(4) NULL COMMENT '菜单类型 0菜单 1按钮',
  `menu_code` VARCHAR(50) NULL COMMENT '菜单编码',
  `url` VARCHAR(300) NULL COMMENT '路径',
  `icon` VARCHAR(100) NULL COMMENT '图标',
  `sort` INT NULL COMMENT '排序',
  `remark` VARCHAR(300) NULL COMMENT '备注',
  `status` TINYINT(4) NULL COMMENT '状态 0初始 1正常',
  `create_by` INT  NOT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` INT  NOT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` INT  NOT NULL DEFAULT 0 COMMENT '删除标记 0正常 1删除',
  PRIMARY KEY (`id`))
  COMMENT = '菜单表';

CREATE TABLE `sys_role_menu` (
  `role_id` INT NOT NULL  COMMENT '角色id',
  `menu_id` INT  NOT NULL COMMENT '菜单id')
  COMMENT = '角色菜单关联表';

CREATE TABLE `sys_department` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` VARCHAR(50) NULL COMMENT '部门名称',
  `parent_id` INT NULL COMMENT '上级部门ID',
  `code` VARCHAR(50) NULL COMMENT '部门编码',
  `sort` INT NULL COMMENT '排序',
  `remark` VARCHAR(300) NULL COMMENT '备注',
  `status` TINYINT(4) NULL COMMENT '状态 0初始 1正常',
  `create_by` INT  NOT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` INT  NOT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` INT  NOT NULL DEFAULT 0 COMMENT '删除标记 0正常 1删除',
  PRIMARY KEY (`id`))
  COMMENT = '部门表';

CREATE TABLE `sys_role_department` (
  `role_id` INT NOT NULL  COMMENT '角色id',
  `department_id` INT  NOT NULL COMMENT '部门id')
  COMMENT = '角色部门关联表';

CREATE TABLE `sys_dict` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` VARCHAR(50) NULL COMMENT '字典名称',
  `parent_id` INT NULL COMMENT '上级字典ID',
  `code` VARCHAR(50) NULL COMMENT '字典编码',
  `sort` INT NULL COMMENT '排序',
  `remark` VARCHAR(300) NULL COMMENT '备注',
  `status` TINYINT(4) NULL COMMENT '状态 0初始 1正常',
  `create_by` INT  NOT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` INT  NOT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` INT  NOT NULL DEFAULT 0 COMMENT '删除标记 0正常 1删除',
  PRIMARY KEY (`id`))
  COMMENT = '数据字典表';
