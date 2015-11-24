/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : cxber-sys

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2015-11-24 10:09:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PARENT_ID` int(10) unsigned DEFAULT NULL COMMENT '父菜单',
  `TITLE` varchar(50) NOT NULL COMMENT '菜单名称',
  `TITLE_FIRST_SPELL` varchar(50) DEFAULT NULL COMMENT '菜单名称拼音首字母',
  `ICON` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `SHOW_MODE` tinyint(3) unsigned DEFAULT '1' COMMENT '显示方式 {1:默认显示,2:默认隐藏}',
  `DESCN` varchar(2000) DEFAULT NULL COMMENT '描述',
  `SORT_NUM` int(10) unsigned DEFAULT NULL COMMENT '排序',
  `STATUS` tinyint(3) unsigned DEFAULT '1' COMMENT '状态{1:启用,0:停用',
  `RESOURCE_ID` int(10) unsigned DEFAULT NULL COMMENT '资源ID',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('81', '0', '系统设置', 'XTSZ', '', '1', '', '1', '1', null, '2015-07-15 09:26:48');
INSERT INTO `sys_menu` VALUES ('83', '81', '系统用户', 'XTYH', '', '2', '', '1', '1', '9', '2015-07-15 09:26:48');
INSERT INTO `sys_menu` VALUES ('85', '81', '角色管理', 'JSGL', '', '2', '', '2', '1', '21', '2015-07-15 09:26:48');
INSERT INTO `sys_menu` VALUES ('89', '81', '菜单管理', 'CDGL', '', '2', '', '3', '1', '23', '2015-07-15 09:26:48');
INSERT INTO `sys_menu` VALUES ('91', '81', '资源管理', 'ZYGL', '', '2', '', '4', '1', '31', '2015-07-15 09:26:48');
INSERT INTO `sys_menu` VALUES ('92', '0', '业务管理', 'YWGL', '', '1', '', '2', '1', null, '2015-11-06 11:28:27');
INSERT INTO `sys_menu` VALUES ('93', '92', '用户信息', 'YHXX', '', '2', '', '1', '1', '68', '2015-11-06 11:28:46');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `PARENT_ID` int(10) unsigned DEFAULT NULL COMMENT '父ID',
  `TITLE` varchar(50) NOT NULL COMMENT '资源名称',
  `RESTYPE` varchar(50) DEFAULT NULL COMMENT '资源类型',
  `RES_STRING` varchar(255) NOT NULL COMMENT '资源值',
  `PERMISSION_VALUE` varchar(500) NOT NULL COMMENT '权限值',
  `DESCN` varchar(2000) DEFAULT NULL COMMENT '描述',
  `STATUS` tinyint(3) unsigned DEFAULT '1' COMMENT '状态{0:禁用,1:启用}',
  `SORT_NUM` int(11) DEFAULT NULL COMMENT '排序值',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='系统资源表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('9', '0', '用户列表', 'URL', '/module/security/sys_user_list.html', '/module/security/sys_user_list.html', '', '1', '1', '2015-07-15 09:26:48');
INSERT INTO `sys_resource` VALUES ('21', '0', '角色管理', 'URL', '/module/security/sys_role_list.html', '/module/security/sys_role_list.html', '', '1', '2', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('23', '0', '菜单管理', 'URL', '/module/security/sys_menu_list.html', '/module/security/sys_menu_list.html', '', '1', '3', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('31', '0', '资源管理', 'URL', '/module/security/sys_resource_list.html', '/module/security/sys_resource_list.html', '', '1', '5', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('33', '9', '增加用户', 'BUTTON', '/module/security/sys_user_add.html', '/module/security/sys_user_add.html', '', '1', '1', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('35', '9', '修改用户', 'BUTTON', '/module/security/sys_user_update.html', '/module/security/sys_user_update.html', '', '1', '2', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('37', '9', '查看用户', 'BUTTON', '/module/security/sys_user_view.html', '/module/security/sys_user_view.html', '', '1', '3', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('39', '9', '修改密码', 'BUTTON', '/module/security/sys_user_pass.html', '/module/security/sys_user_pass.html', '', '1', '4', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('41', '0', '修改密码', 'URL', '/module/security/change_pass.html', '/module/security/change_pass.html', '', '1', '4', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('43', '23', '增加菜单', 'BUTTON', '/module/security/sys_menu_add.html', '/module/security/sys_menu_add.html', '', '1', '1', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('45', '23', '修改菜单', 'BUTTON', '/module/security/sys_menu_update.html', '/module/security/sys_menu_update.html', '', '1', '2', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('47', '23', '查看菜单', 'BUTTON', '/module/security/sys_menu_view.html', '/module/security/sys_menu_view.html', '', '1', '3', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('49', '23', '删除菜单', 'BUTTON', '/module/security/sys_menu_del.html', '/module/security/sys_menu_del.html', '', '1', '4', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('51', '23', '移动菜单', 'BUTTON', '/module/security/sys_menu_sort.html', '/module/security/sys_menu_sort.html', '', '1', '5', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('53', '21', '增加角色', 'BUTTON', '/module/security/sys_role_add.html', '/module/security/sys_role_add.html', '', '1', '1', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('55', '21', '修改角色', 'BUTTON', '/module/security/sys_role_update.html', '/module/security/sys_role_update.html', '', '1', '2', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('57', '21', '查看角色', 'BUTTON', '/module/security/sys_role_view.html', '/module/security/sys_role_view.html', '', '1', '3', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('59', '23', '增加资源', 'BUTTON', '/module/security/sys_resource_add.html', '/module/security/sys_resource_add.html', '', '1', '6', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('61', '31', '修改资源', 'BUTTON', '/module/security/sys_resource_update.html', '/module/security/sys_resource_update.html', '', '1', '1', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('63', '31', '查看资源', 'BUTTON', '/module/security/sys_resource_view.html', '/module/security/sys_resource_view.html', '', '1', '2', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('65', '31', '删除资源', 'BUTTON', '/module/security/sys_resource_del.html', '/module/security/sys_resource_del.html', '', '1', '3', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('67', '31', '移动资源', 'BUTTON', '/module/security/sys_resource_sort.html', '/module/security/sys_resource_sort.html', '', '1', '4', '2015-07-15 09:26:49');
INSERT INTO `sys_resource` VALUES ('68', '0', '用户信息', 'URL', '/module/test_u/test_ulist.html', '/module/test_u/*', '', '1', '6', '2015-11-06 11:28:02');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ROLE_NAME` varchar(50) NOT NULL COMMENT '角色名称',
  `DESCN` varchar(2000) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '');

-- ----------------------------
-- Table structure for sys_role_resc
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resc`;
CREATE TABLE `sys_role_resc` (
  `ROLE_ID` int(10) unsigned NOT NULL,
  `RESC_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`RESC_ID`),
  KEY `IND_RESC_ROLE_ID` (`RESC_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色资源表';

-- ----------------------------
-- Records of sys_role_resc
-- ----------------------------
INSERT INTO `sys_role_resc` VALUES ('1', '9');
INSERT INTO `sys_role_resc` VALUES ('1', '21');
INSERT INTO `sys_role_resc` VALUES ('1', '23');
INSERT INTO `sys_role_resc` VALUES ('1', '31');
INSERT INTO `sys_role_resc` VALUES ('1', '33');
INSERT INTO `sys_role_resc` VALUES ('1', '35');
INSERT INTO `sys_role_resc` VALUES ('1', '37');
INSERT INTO `sys_role_resc` VALUES ('1', '39');
INSERT INTO `sys_role_resc` VALUES ('1', '41');
INSERT INTO `sys_role_resc` VALUES ('1', '43');
INSERT INTO `sys_role_resc` VALUES ('1', '45');
INSERT INTO `sys_role_resc` VALUES ('1', '47');
INSERT INTO `sys_role_resc` VALUES ('1', '49');
INSERT INTO `sys_role_resc` VALUES ('1', '51');
INSERT INTO `sys_role_resc` VALUES ('1', '53');
INSERT INTO `sys_role_resc` VALUES ('1', '55');
INSERT INTO `sys_role_resc` VALUES ('1', '57');
INSERT INTO `sys_role_resc` VALUES ('1', '59');
INSERT INTO `sys_role_resc` VALUES ('1', '61');
INSERT INTO `sys_role_resc` VALUES ('1', '63');
INSERT INTO `sys_role_resc` VALUES ('1', '65');
INSERT INTO `sys_role_resc` VALUES ('1', '67');
INSERT INTO `sys_role_resc` VALUES ('1', '68');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `USERNAME` varchar(30) NOT NULL COMMENT '登录用户名',
  `NICKNAME` varchar(30) DEFAULT NULL COMMENT '操作员姓名',
  `PASSWORD` varchar(50) NOT NULL COMMENT '登录密码',
  `SALT` varchar(50) DEFAULT NULL COMMENT '密码加密填充值',
  `USERTYPE` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '用户类型{1:管理员,2:操作员}',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `STATUS` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态{1:启用,0:禁用}',
  `DESCN` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `LAST_LOGIN_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后登录时间',
  `LAST_LOGIN_IP` varchar(30) DEFAULT NULL COMMENT '最后登录IP',
  `EXPIRED_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '过期时间',
  `UNLOCK_DATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '解锁时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IND_USERNAME` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', null, '9af2a8fd301e0277b867c22ac1c80e627ae66596', '0ee0e507d6d413fd', '1', 'admin@feinno.com', '1', '', '2015-07-15 09:26:49', '2015-07-15 09:26:49', '127.0.0.1', '2015-07-15 00:00:00', '2015-07-15 00:00:00');
INSERT INTO `sys_user` VALUES ('2', 'tangqiang', null, '8fd37e9c0de9a399e3ec14cd3571b4c4f36da01a', '178a918d674a4409', '1', 'uoverv@163.com', '1', '', '2015-10-08 13:58:38', '2015-10-08 13:58:38', '192.168.182.1', '2015-10-08 13:58:38', '2015-10-08 13:58:38');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `ROLE_ID` int(10) unsigned NOT NULL,
  `USER_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`USER_ID`),
  UNIQUE KEY `IND_USER_ROLE_ID` (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
