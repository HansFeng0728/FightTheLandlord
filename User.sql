/*
Navicat MySQL Data Transfer

Source Server         : fhw
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : fightlandlords

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-10-20 10:12:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(5) NOT NULL AUTO_INCREMENT COMMENT '用户的主键',
  `userName` text NOT NULL COMMENT '用户的帐号名',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('19', 'Hans');
INSERT INTO `user` VALUES ('20', 'qw');
INSERT INTO `user` VALUES ('21', 'iii');
