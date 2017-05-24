

CREATE DATABASE `ynnjiiweb` DEFAULT CHARACTER SET utf8 ;

use `ynnjiiweb`;

CREATE TABLE `sys_user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `office_id` INT NULL COMMENT '部门id',
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
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` INT  NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255)  DEFAULT NULL COMMENT '备注信息',
  `del_flag` INT  NOT NULL DEFAULT 0 COMMENT '删除标记 0正常 1删除',
  PRIMARY KEY (`id`))
COMMENT = '用户表';

