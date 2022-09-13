/*
 Navicat Premium Data Transfer

 Source Server         : mysql_master[book]
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : 172.20.0.2:3306
 Source Schema         : vote

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 11/09/2022 14:50:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for candidate
-- ----------------------------
DROP TABLE IF EXISTS `candidate`;
CREATE TABLE `candidate`
(
    `candidate_id`     int(10)      NOT NULL,
    `image`            varchar(255) NOT NULL,
    `candidate_name`   varchar(10)  NOT NULL,
    `introduction`     text         NOT NULL,
    `create_timestamp` datetime     NOT NULL,
    PRIMARY KEY (`candidate_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for candidate_user
-- ----------------------------
DROP TABLE IF EXISTS `candidate_user`;
CREATE TABLE `candidate_user`
(
    `candidate_id` int(11) NOT NULL,
    `user_id`      int(11) NOT NULL,
    KEY `candidate_user_candidate_id` (`candidate_id`),
    KEY `candidate_user_user_id` (`user_id`),
    CONSTRAINT `candidate_user_candidate_id` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`candidate_id`),
    CONSTRAINT `candidate_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for candidate_versioin
-- ----------------------------
DROP TABLE IF EXISTS `candidate_versioin`;
CREATE TABLE `candidate_versioin`
(
    `candidate_id`        int(11)      NOT NULL,
    `image`               varchar(255) NOT NULL,
    `candidate_name`      varchar(10)  NOT NULL,
    `introduction`        text         NOT NULL,
    `modify_timestamp`    datetime     NOT NULL,
    `version_id`          int(11)      NOT NULL,
    `version_description` varchar(255) NOT NULL,
    KEY `candidate_version_candidate_id` (`candidate_id`),
    CONSTRAINT `candidate_version_candidate_id` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`candidate_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province`
(
    `province_id`   int(2)       NOT NULL,
    `province_name` varchar(255) NOT NULL,
    PRIMARY KEY (`province_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `role_id`   int(11)      NOT NULL,
    `role_name` varchar(255) NOT NULL,
    PRIMARY KEY (`role_id`, `role_name`),
    KEY `role_id` (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `user_id`          int(10)      NOT NULL AUTO_INCREMENT,
    `phone`            varchar(11)  NOT NULL,
    `name`             varchar(10)  NOT NULL,
    `password`         varchar(255) NOT NULL,
    `provience_id`     int(2)       NOT NULL,
    `create_timestamp` datetime     NOT NULL,
    PRIMARY KEY (`user_id`),
    KEY `user_provience_id` (`provience_id`),
    CONSTRAINT `user_provience_id` FOREIGN KEY (`provience_id`) REFERENCES `province` (`province_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `user_id` int(10) NOT NULL,
    `role_id` int(11) NOT NULL,
    PRIMARY KEY (`user_id`),
    KEY `user_role_role_id` (`role_id`),
    CONSTRAINT `user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
    CONSTRAINT `user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote`
(
    `vote_id`          int(10)     NOT NULL AUTO_INCREMENT,
    `status`           int(1)      NOT NULL,
    `create_timestamp` datetime    NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `version_current`  int(11)     NOT NULL,
    `title`            varchar(30) NOT NULL,
    `description`      text        NOT NULL,
    PRIMARY KEY (`vote_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for vote_candidate
-- ----------------------------
DROP TABLE IF EXISTS `vote_candidate`;
CREATE TABLE `vote_candidate`
(
    `vote_id`      int(11) NOT NULL,
    `candidate_id` int(11) NOT NULL,
    `sort`         int(11) NOT NULL,
    `version_id`   int(11) NOT NULL,
    KEY `vote_candidate_vote_id` (`vote_id`),
    KEY `vote_candidate_candidate_id` (`candidate_id`),
    KEY `vote_candidate_version_id` (`version_id`),
    CONSTRAINT `vote_candidate_candidate_id` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`candidate_id`),
    CONSTRAINT `vote_candidate_vote_id` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for vote_user
-- ----------------------------
DROP TABLE IF EXISTS `vote_user`;
CREATE TABLE `vote_user`
(
    `vote_id` int(11) NOT NULL,
    `user_id` int(11) NOT NULL,
    KEY `vote_user_vote_id` (`vote_id`),
    KEY `vote_user_user_id` (`user_id`),
    CONSTRAINT `vote_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    CONSTRAINT `vote_user_vote_id` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for vote_version
-- ----------------------------
DROP TABLE IF EXISTS `vote_version`;
CREATE TABLE `vote_version`
(
    `vote_id`             int(11)      NOT NULL,
    `version_id`          int(11)      NOT NULL,
    `version_description` varchar(200) NOT NULL,
    KEY `version_id` (`version_id`),
    KEY `vote_version_vote_id` (`vote_id`),
    CONSTRAINT `vote_version_vote_id` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
