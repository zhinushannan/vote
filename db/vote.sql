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

 Date: 15/09/2022 09:07:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for candidate
-- ----------------------------
DROP TABLE IF EXISTS `candidate`;
CREATE TABLE `candidate` (
  `candidate_id` int(10) NOT NULL AUTO_INCREMENT,
  `create_timestamp` datetime NOT NULL,
  `version_current` int(11) NOT NULL,
  PRIMARY KEY (`candidate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of candidate
-- ----------------------------
BEGIN;
INSERT INTO `candidate` (`candidate_id`, `create_timestamp`, `version_current`) VALUES (4, '2022-09-12 00:16:39', 0);
INSERT INTO `candidate` (`candidate_id`, `create_timestamp`, `version_current`) VALUES (6, '2022-09-12 18:38:47', 0);
INSERT INTO `candidate` (`candidate_id`, `create_timestamp`, `version_current`) VALUES (7, '2022-09-14 14:44:16', 0);
INSERT INTO `candidate` (`candidate_id`, `create_timestamp`, `version_current`) VALUES (8, '2022-09-14 14:44:51', 0);
INSERT INTO `candidate` (`candidate_id`, `create_timestamp`, `version_current`) VALUES (9, '2022-09-14 14:45:10', 0);
INSERT INTO `candidate` (`candidate_id`, `create_timestamp`, `version_current`) VALUES (10, '2022-09-14 14:47:12', 0);
INSERT INTO `candidate` (`candidate_id`, `create_timestamp`, `version_current`) VALUES (11, '2022-09-14 14:47:56', 0);
INSERT INTO `candidate` (`candidate_id`, `create_timestamp`, `version_current`) VALUES (12, '2022-09-14 14:48:17', 0);
INSERT INTO `candidate` (`candidate_id`, `create_timestamp`, `version_current`) VALUES (13, '2022-09-14 14:49:08', 0);
COMMIT;

-- ----------------------------
-- Table structure for candidate_user
-- ----------------------------
DROP TABLE IF EXISTS `candidate_user`;
CREATE TABLE `candidate_user` (
  `candidate_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `candidate_user_user_id` (`user_id`),
  KEY `candidate_user_candidate_id` (`candidate_id`),
  CONSTRAINT `candidate_user_candidate_id` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`candidate_id`),
  CONSTRAINT `candidate_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of candidate_user
-- ----------------------------
BEGIN;
INSERT INTO `candidate_user` (`candidate_id`, `user_id`) VALUES (4, 6);
INSERT INTO `candidate_user` (`candidate_id`, `user_id`) VALUES (6, 6);
INSERT INTO `candidate_user` (`candidate_id`, `user_id`) VALUES (7, 6);
INSERT INTO `candidate_user` (`candidate_id`, `user_id`) VALUES (8, 6);
INSERT INTO `candidate_user` (`candidate_id`, `user_id`) VALUES (9, 6);
INSERT INTO `candidate_user` (`candidate_id`, `user_id`) VALUES (10, 6);
INSERT INTO `candidate_user` (`candidate_id`, `user_id`) VALUES (11, 6);
INSERT INTO `candidate_user` (`candidate_id`, `user_id`) VALUES (12, 6);
INSERT INTO `candidate_user` (`candidate_id`, `user_id`) VALUES (13, 6);
COMMIT;

-- ----------------------------
-- Table structure for candidate_versioin
-- ----------------------------
DROP TABLE IF EXISTS `candidate_versioin`;
CREATE TABLE `candidate_versioin` (
  `candidate_id` int(10) NOT NULL,
  `image` varchar(255) NOT NULL,
  `candidate_name` varchar(10) NOT NULL,
  `candidate_abstract` text NOT NULL,
  `introduction_md` text NOT NULL,
  `introduction_html` text NOT NULL,
  `modify_timestamp` datetime NOT NULL,
  `version_id` int(11) NOT NULL,
  `version_description` varchar(255) DEFAULT NULL,
  KEY `candidate_version_candidate_id` (`candidate_id`),
  CONSTRAINT `candidate_version_candidate_id` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`candidate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of candidate_versioin
-- ----------------------------
BEGIN;
INSERT INTO `candidate_versioin` (`candidate_id`, `image`, `candidate_name`, `candidate_abstract`, `introduction_md`, `introduction_html`, `modify_timestamp`, `version_id`, `version_description`) VALUES (4, 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/6ef2ba4d1662905985112.png', '123', '', '123123', '<p>123123</p>\n', '2022-09-12 00:16:39', 0, NULL);
INSERT INTO `candidate_versioin` (`candidate_id`, `image`, `candidate_name`, `candidate_abstract`, `introduction_md`, `introduction_html`, `modify_timestamp`, `version_id`, `version_description`) VALUES (6, 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/190938521662978886300.jpg', '王建文', '王建文，19数据班长，中国共产党党员。', '# 个人简介\n王建文，2001年9月出生于江苏省邳州市。\n# 宗教信仰\n共产主义\n# 政治面貌\n2015年加入中国共产主义青年团，2021年加入中国共产党。\n# 教育经历\n|阶段|学校|\n|-|-|-|\n|小学|邳州市李口小学|\n|初中|江苏省运河中学|\n|高中|邳州市第二中学|\n|大学|江苏师范大学科文学院|\n', '<h1><a id=\"_0\"></a>个人简介</h1>\n<p>王建文，2001年9月出生于江苏省邳州市。</p>\n<h1><a id=\"_2\"></a>宗教信仰</h1>\n<p>共产主义</p>\n<h1><a id=\"_4\"></a>政治面貌</h1>\n<p>2015年加入中国共产主义青年团，2021年加入中国共产党。</p>\n<h1><a id=\"_6\"></a>教育经历</h1>\n<table>\n<thead>\n<tr>\n<th>阶段</th>\n<th>学校</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>小学</td>\n<td>邳州市李口小学</td>\n</tr>\n<tr>\n<td>初中</td>\n<td>江苏省运河中学</td>\n</tr>\n<tr>\n<td>高中</td>\n<td>邳州市第二中学</td>\n</tr>\n<tr>\n<td>大学</td>\n<td>江苏师范大学科文学院</td>\n</tr>\n</tbody>\n</table>\n', '2022-09-12 18:38:47', 0, NULL);
INSERT INTO `candidate_versioin` (`candidate_id`, `image`, `candidate_name`, `candidate_abstract`, `introduction_md`, `introduction_html`, `modify_timestamp`, `version_id`, `version_description`) VALUES (7, 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/caba78b51663137828994.png', '拜登', '约瑟夫·拜登，即小约瑟夫·罗宾内特·拜登 1942年11月20，一般作乔·拜登，美国爱尔兰裔政治家、律师，曾任第47任美国副总统，现任第46任美国总统。', '拜登毕业于特拉华大学和雪城大学，当过一段时间律师，1970年踏入政界 ，1972年11月当选联邦参议员并六次连任至2009年。\n1988年和2007年两度竞选美国总统，都没有成功。期间担任参议院司法委员会主席及高级成员16年、对外关系委员会主席及高级成员12年。\n2008、2013年两度成为奥巴马的竞选搭档。2009—2017年任副总统。2019年4月25日宣布参选美国总统。 2021年1月7日确认当选。 1月20日宣誓就任。\n', '<p>拜登毕业于特拉华大学和雪城大学，当过一段时间律师，1970年踏入政界 ，1972年11月当选联邦参议员并六次连任至2009年。<br />\n1988年和2007年两度竞选美国总统，都没有成功。期间担任参议院司法委员会主席及高级成员16年、对外关系委员会主席及高级成员12年。<br />\n2008、2013年两度成为奥巴马的竞选搭档。2009—2017年任副总统。2019年4月25日宣布参选美国总统。 2021年1月7日确认当选。 1月20日宣誓就任。</p>\n', '2022-09-14 14:44:16', 0, NULL);
INSERT INTO `candidate_versioin` (`candidate_id`, `image`, `candidate_name`, `candidate_abstract`, `introduction_md`, `introduction_html`, `modify_timestamp`, `version_id`, `version_description`) VALUES (8, 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/2c3c263f1663137879103.png', '陈朝鲜', '男，1988年8月出生，籍贯福建东山，中共正式党员，2007级微电子学2班学生，现任07学生党支部副书记、校团委组织部事务组负责人、院记者团团长、班级党章委员。', '自入学以来，一直保持积极向上的精神面貌。思想上，积极与党组织保持一致，并积极配合年段党支部开展各项工作;学习上，勤奋刻苦;工作上，认真负责，能积极协调各方面关系，做好各项工作;生活上，关心同学，有广泛的人际关系。\n', '<p>自入学以来，一直保持积极向上的精神面貌。思想上，积极与党组织保持一致，并积极配合年段党支部开展各项工作;学习上，勤奋刻苦;工作上，认真负责，能积极协调各方面关系，做好各项工作;生活上，关心同学，有广泛的人际关系。</p>\n', '2022-09-14 14:44:51', 0, NULL);
INSERT INTO `candidate_versioin` (`candidate_id`, `image`, `candidate_name`, `candidate_abstract`, `introduction_md`, `introduction_html`, `modify_timestamp`, `version_id`, `version_description`) VALUES (9, 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/a098ad541663137908121.png', '方惠梅', '方惠梅，女，1989年7月8日出生。福建漳州人，入党积极分子，现任学习委员，组织部部员', '方惠梅，女，1989年7月8日出生。福建漳州人，入党积极分子，08级信通四班学生，现任学习委员，组织部部员。工作认真负责，能吃苦耐劳，忍耐性强，微笑着面对一切艰难困苦，老实真诚，富有爱心。于2008--2009学年上学期荣获福州大学一等奖学金。2009年4月份荣获福州大学优秀学干。\n\n', '<p>方惠梅，女，1989年7月8日出生。福建漳州人，入党积极分子，08级信通四班学生，现任学习委员，组织部部员。工作认真负责，能吃苦耐劳，忍耐性强，微笑着面对一切艰难困苦，老实真诚，富有爱心。于2008–2009学年上学期荣获福州大学一等奖学金。2009年4月份荣获福州大学优秀学干。</p>\n', '2022-09-14 14:45:10', 0, NULL);
INSERT INTO `candidate_versioin` (`candidate_id`, `image`, `candidate_name`, `candidate_abstract`, `introduction_md`, `introduction_html`, `modify_timestamp`, `version_id`, `version_description`) VALUES (10, 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/d54759f11663137951184.png', '李易峰', '李易峰，1987年5月4日出生于四川省成都市，中国内地男演员、流行乐歌手、影视制片人，毕业于四川师范大学电影电视学院。', '2007年，参加东方卫视选秀娱乐节目《加油！好男儿》的比赛，获得全国总决赛第八名，从而正式出道  。\n2009年，发行首张个人专辑《小先生》 。2010年，主演都市爱情剧《幸福一定强》。2015年，主演年代剧《活色生香》和网络季播剧《盗墓笔记》。\n2016年，主演谍战剧《麻雀》 、仙侠剧《青云志》和电影《心理罪》  。2018年，主演电影《动物世界》。2020年，参演青春励志剧《我在北京等你》 。\n2022年，发行单曲《倒叙的生活》\n**2022年9月入狱。**\n![WechatIMG36535.jpeg](https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/b0e98dab1663138028876.jpeg)', '<p>2007年，参加东方卫视选秀娱乐节目《加油！好男儿》的比赛，获得全国总决赛第八名，从而正式出道  。<br />\n2009年，发行首张个人专辑《小先生》 。2010年，主演都市爱情剧《幸福一定强》。2015年，主演年代剧《活色生香》和网络季播剧《盗墓笔记》。<br />\n2016年，主演谍战剧《麻雀》 、仙侠剧《青云志》和电影《心理罪》  。2018年，主演电影《动物世界》。2020年，参演青春励志剧《我在北京等你》 。<br />\n2022年，发行单曲《倒叙的生活》<br />\n<strong>2022年9月入狱。</strong><br />\n<img src=\"https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/b0e98dab1663138028876.jpeg\" alt=\"WechatIMG36535.jpeg\" /></p>\n', '2022-09-14 14:47:12', 0, NULL);
INSERT INTO `candidate_versioin` (`candidate_id`, `image`, `candidate_name`, `candidate_abstract`, `introduction_md`, `introduction_html`, `modify_timestamp`, `version_id`, `version_description`) VALUES (11, 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/f52b1d711663138074384.png', '罗志祥', '罗志祥（Show Lo），1979年7月30日出生于中国台湾省基隆市，中国台湾流行乐男歌手、主持人、舞者、演员。', '1995年因参加“四大天王就是你”模仿大赛正式出道。2003年推出首张个人专辑《Show Time》。 2005年举行个人首场演唱会，成为第一位踏上台北小巨蛋体育馆的流行歌手。\n2008年发行第六张专辑《潮男正传》，获MTV亚洲音乐大奖。 2010年专辑《罗生门》获“五白金”。2012年前往日本乐坛发展，首张日语单曲《Dante》推出首周即登上日本公信榜单曲周榜第十名，使罗志祥成为首位于日本公信榜单曲获得前十名的台湾男歌手。 \n2013年第十张专辑《狮子吼》获认证四白金唱片，使罗志祥连续四年（2010—2013）成为台湾地区年度唱片销售总冠军。2016年，开启个人第四次世界巡回演唱会“CRAZY WORLD疯狂世界”。', '<p>1995年因参加“四大天王就是你”模仿大赛正式出道。2003年推出首张个人专辑《Show Time》。 2005年举行个人首场演唱会，成为第一位踏上台北小巨蛋体育馆的流行歌手。<br />\n2008年发行第六张专辑《潮男正传》，获MTV亚洲音乐大奖。 2010年专辑《罗生门》获“五白金”。2012年前往日本乐坛发展，首张日语单曲《Dante》推出首周即登上日本公信榜单曲周榜第十名，使罗志祥成为首位于日本公信榜单曲获得前十名的台湾男歌手。<br />\n2013年第十张专辑《狮子吼》获认证四白金唱片，使罗志祥连续四年（2010—2013）成为台湾地区年度唱片销售总冠军。2016年，开启个人第四次世界巡回演唱会“CRAZY WORLD疯狂世界”。</p>\n', '2022-09-14 14:47:56', 0, NULL);
INSERT INTO `candidate_versioin` (`candidate_id`, `image`, `candidate_name`, `candidate_abstract`, `introduction_md`, `introduction_html`, `modify_timestamp`, `version_id`, `version_description`) VALUES (12, 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/e652f4dc1663138094179.png', '唐纳德·特朗普', '唐纳德·特朗普 1946年6月14日，出生于美国纽约，祖籍德国巴伐利亚自由州  ，德裔美国共和党籍政治家、企业家、房地产商人、电视人  ，第45任美国总统', '\n\n特朗普于1968年获得宾夕法尼亚大学沃顿商学院经济学学士学位，随后任职于父亲弗雷德·特朗普的房地产公司。\n1971年接管公司，从事房地产开发，投资范围逐步延伸至其他多个行业。特朗普于2015年6月以美国共和党人身份宣布参选美国总统，\n2016年11月9日当选美国第45任总统，2017年1月20日宣誓就职  。2020年12月，特朗普败选，连任失败  。2021年1月19日，特朗普发表告别演说   。\n\n', '<p>特朗普于1968年获得宾夕法尼亚大学沃顿商学院经济学学士学位，随后任职于父亲弗雷德·特朗普的房地产公司。<br />\n1971年接管公司，从事房地产开发，投资范围逐步延伸至其他多个行业。特朗普于2015年6月以美国共和党人身份宣布参选美国总统，<br />\n2016年11月9日当选美国第45任总统，2017年1月20日宣誓就职  。2020年12月，特朗普败选，连任失败  。2021年1月19日，特朗普发表告别演说   。</p>\n', '2022-09-14 14:48:17', 0, NULL);
INSERT INTO `candidate_versioin` (`candidate_id`, `image`, `candidate_name`, `candidate_abstract`, `introduction_md`, `introduction_html`, `modify_timestamp`, `version_id`, `version_description`) VALUES (13, 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/0e7755851663138145803.png', '吴亦凡', '涉强奸罪加拿大籍男艺人，1990年11月6日出生于广东省广州市，加拿大籍华语流行乐男歌手、影视演员、音乐制作人。', '吴亦凡出生于广东省广州市，父母离异后随母亲生活，10岁时和家人移民加拿大温哥华。2005年，在广州市第七中学就读初三，期间担任篮球队队长，参加少年NBA中国初中篮球联赛，随后，回到加拿大在加拿大温斯顿爵士丘吉尔中学就读。2007年，高二时参加了SM公司的全球选秀，成为练习生\n\n++2021年被捕++', '<p>吴亦凡出生于广东省广州市，父母离异后随母亲生活，10岁时和家人移民加拿大温哥华。2005年，在广州市第七中学就读初三，期间担任篮球队队长，参加少年NBA中国初中篮球联赛，随后，回到加拿大在加拿大温斯顿爵士丘吉尔中学就读。2007年，高二时参加了SM公司的全球选秀，成为练习生</p>\n<p><ins>2021年被捕</ins></p>\n', '2022-09-14 14:49:08', 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `md5` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`md5`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of image
-- ----------------------------
BEGIN;
INSERT INTO `image` (`md5`, `url`) VALUES ('076053cab870ee9b35aac0940cc030bc', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/b0e98dab1663138028876.jpeg');
INSERT INTO `image` (`md5`, `url`) VALUES ('1fbb8b1bfed68089af073cd9eb73ae82', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/1df668e31662906164117.png');
INSERT INTO `image` (`md5`, `url`) VALUES ('29e6d818fd7edab7463f31f553d53089', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/20699f661662906262412.png');
INSERT INTO `image` (`md5`, `url`) VALUES ('54a80f092bab90337433a0a69903c140', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/0a2127cf1663138895575.png');
INSERT INTO `image` (`md5`, `url`) VALUES ('6a818703ca47435e2e0204708f68c5e6', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/f52b1d711663138074384.png');
INSERT INTO `image` (`md5`, `url`) VALUES ('6ad0983f55f3d88b4e687f1a31e6d172', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/598da46f1662903962422.png');
INSERT INTO `image` (`md5`, `url`) VALUES ('6d8739a48c736d14af62f026aa597191', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/d54759f11663137951184.png');
INSERT INTO `image` (`md5`, `url`) VALUES ('7057679b9cd453733b68383b3c6cfc8d', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/e652f4dc1663138094179.png');
INSERT INTO `image` (`md5`, `url`) VALUES ('9b73ad3e5289ed2148334a64eea7213a', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/2c3c263f1663137879103.png');
INSERT INTO `image` (`md5`, `url`) VALUES ('9ebe0433778ad7d107e2cc5026f1b493', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/6ef2ba4d1662905985112.png');
INSERT INTO `image` (`md5`, `url`) VALUES ('b1367a4e01584ade5561926412de688d', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/caba78b51663137828994.png');
INSERT INTO `image` (`md5`, `url`) VALUES ('c67a451e80287b6da8a7b04079615bd0', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/a098ad541663137908121.png');
INSERT INTO `image` (`md5`, `url`) VALUES ('d96be7fa3b2aab4c65ae80bc636d9bc4', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/0e7755851663138145803.png');
INSERT INTO `image` (`md5`, `url`) VALUES ('e0c576a362cd3d8fffd390b7cbf3ac44', 'https://book-mall-1304360121.cos.ap-shanghai.myqcloud.com/190938521662978886300.jpg');
COMMIT;

-- ----------------------------
-- Table structure for poll
-- ----------------------------
DROP TABLE IF EXISTS `poll`;
CREATE TABLE `poll` (
  `user_id` int(11) NOT NULL,
  `vote_id` int(11) NOT NULL,
  `candidate_id` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `ip` varchar(255) NOT NULL,
  `ip_addr` varchar(255) DEFAULT NULL,
  `create_timestamp` datetime NOT NULL,
  KEY `poll_user_id` (`user_id`),
  KEY `poll_vote_id` (`vote_id`),
  KEY `poll_candidate_id` (`candidate_id`),
  CONSTRAINT `poll_candidate_id` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`candidate_id`),
  CONSTRAINT `poll_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `poll_vote_id` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of poll
-- ----------------------------
BEGIN;
INSERT INTO `poll` (`user_id`, `vote_id`, `candidate_id`, `score`, `ip`, `ip_addr`, `create_timestamp`) VALUES (6, 4, 6, 8, '127.0.0.1', NULL, '2022-09-14 17:00:34');
INSERT INTO `poll` (`user_id`, `vote_id`, `candidate_id`, `score`, `ip`, `ip_addr`, `create_timestamp`) VALUES (6, 4, 7, 6, '127.0.0.1', NULL, '2022-09-14 17:00:34');
INSERT INTO `poll` (`user_id`, `vote_id`, `candidate_id`, `score`, `ip`, `ip_addr`, `create_timestamp`) VALUES (6, 4, 12, 6, '127.0.0.1', NULL, '2022-09-14 17:00:34');
INSERT INTO `poll` (`user_id`, `vote_id`, `candidate_id`, `score`, `ip`, `ip_addr`, `create_timestamp`) VALUES (6, 3, 4, 6, '127.0.0.1', NULL, '2022-09-14 22:32:49');
INSERT INTO `poll` (`user_id`, `vote_id`, `candidate_id`, `score`, `ip`, `ip_addr`, `create_timestamp`) VALUES (6, 3, 6, 9, '127.0.0.1', NULL, '2022-09-14 22:32:49');
INSERT INTO `poll` (`user_id`, `vote_id`, `candidate_id`, `score`, `ip`, `ip_addr`, `create_timestamp`) VALUES (6, 5, 4, 8, '0:0:0:0:0:0:0:1', NULL, '2022-09-15 08:58:38');
INSERT INTO `poll` (`user_id`, `vote_id`, `candidate_id`, `score`, `ip`, `ip_addr`, `create_timestamp`) VALUES (6, 5, 8, 3, '0:0:0:0:0:0:0:1', NULL, '2022-09-15 08:58:38');
COMMIT;

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `province_id` int(2) NOT NULL,
  `province_name` varchar(255) NOT NULL,
  PRIMARY KEY (`province_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of province
-- ----------------------------
BEGIN;
INSERT INTO `province` (`province_id`, `province_name`) VALUES (1, '北京');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (2, '天津');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (3, '河北');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (4, '山西');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (5, '内蒙古');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (6, '辽宁');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (7, '吉林');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (8, '黑龙江');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (9, '上海');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (10, '江苏');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (11, '浙江');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (12, '安徽');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (13, '福建');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (14, '江西');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (15, '山东');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (16, '河南');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (17, '湖北');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (18, '湖南');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (19, '广东');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (20, '广西');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (21, '海南');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (22, '重庆');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (23, '四川');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (24, '贵州');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (25, '云南');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (26, '西藏');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (27, '陕西');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (28, '甘肃');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (29, '青海');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (30, '宁夏');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (31, '新疆');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (32, '香港');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (33, '澳门');
INSERT INTO `province` (`province_id`, `province_name`) VALUES (34, '台湾');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`,`role`) USING BTREE,
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`role_id`, `role`) VALUES (1, 'USER');
INSERT INTO `role` (`role_id`, `role`) VALUES (2, 'ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `province_id` int(2) DEFAULT NULL,
  `create_timestamp` datetime NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_provience_id` (`province_id`),
  CONSTRAINT `user_provience_id` FOREIGN KEY (`province_id`) REFERENCES `province` (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`user_id`, `phone`, `name`, `password`, `province_id`, `create_timestamp`) VALUES (6, '18762237071', '123', '18762237071', 1, '2022-09-11 18:25:00');
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(10) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `user_role_role_id` (`role_id`),
  KEY `user_role_user_id` (`user_id`),
  CONSTRAINT `user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (6, 1);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (6, 2);
COMMIT;

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `vote_id` int(10) NOT NULL AUTO_INCREMENT,
  `status` int(1) NOT NULL,
  `create_timestamp` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `deadline` datetime DEFAULT NULL,
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of vote
-- ----------------------------
BEGIN;
INSERT INTO `vote` (`vote_id`, `status`, `create_timestamp`, `title`, `description`, `deadline`) VALUES (3, 1, '2022-09-14 14:30:57', '投票2', '投票2', '2022-09-15 08:58:13');
INSERT INTO `vote` (`vote_id`, `status`, `create_timestamp`, `title`, `description`, `deadline`) VALUES (4, 1, '2022-09-14 16:58:34', '投票测试', '投票测试', '2022-09-14 17:01:15');
INSERT INTO `vote` (`vote_id`, `status`, `create_timestamp`, `title`, `description`, `deadline`) VALUES (5, 0, '2022-09-15 08:57:43', '投票测试', '投票测试', NULL);
COMMIT;

-- ----------------------------
-- Table structure for vote_candidate
-- ----------------------------
DROP TABLE IF EXISTS `vote_candidate`;
CREATE TABLE `vote_candidate` (
  `vote_id` int(11) NOT NULL,
  `candidate_id` int(10) NOT NULL,
  `sort` int(11) NOT NULL,
  KEY `vote_candidate_candidate_id` (`candidate_id`),
  KEY `vote_candidate_vote_id` (`vote_id`),
  CONSTRAINT `vote_candidate_candidate_id` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`candidate_id`),
  CONSTRAINT `vote_candidate_vote_id` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of vote_candidate
-- ----------------------------
BEGIN;
INSERT INTO `vote_candidate` (`vote_id`, `candidate_id`, `sort`) VALUES (3, 4, 0);
INSERT INTO `vote_candidate` (`vote_id`, `candidate_id`, `sort`) VALUES (3, 6, 1);
INSERT INTO `vote_candidate` (`vote_id`, `candidate_id`, `sort`) VALUES (4, 6, 0);
INSERT INTO `vote_candidate` (`vote_id`, `candidate_id`, `sort`) VALUES (4, 7, 1);
INSERT INTO `vote_candidate` (`vote_id`, `candidate_id`, `sort`) VALUES (4, 8, 2);
INSERT INTO `vote_candidate` (`vote_id`, `candidate_id`, `sort`) VALUES (4, 12, 3);
INSERT INTO `vote_candidate` (`vote_id`, `candidate_id`, `sort`) VALUES (4, 10, 4);
INSERT INTO `vote_candidate` (`vote_id`, `candidate_id`, `sort`) VALUES (5, 4, 0);
INSERT INTO `vote_candidate` (`vote_id`, `candidate_id`, `sort`) VALUES (5, 7, 1);
INSERT INTO `vote_candidate` (`vote_id`, `candidate_id`, `sort`) VALUES (5, 8, 2);
INSERT INTO `vote_candidate` (`vote_id`, `candidate_id`, `sort`) VALUES (5, 10, 3);
INSERT INTO `vote_candidate` (`vote_id`, `candidate_id`, `sort`) VALUES (5, 6, 4);
COMMIT;

-- ----------------------------
-- Table structure for vote_user
-- ----------------------------
DROP TABLE IF EXISTS `vote_user`;
CREATE TABLE `vote_user` (
  `vote_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  KEY `vote_user_vote_id` (`vote_id`),
  KEY `vote_user_user_id` (`user_id`),
  CONSTRAINT `vote_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `vote_user_vote_id` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of vote_user
-- ----------------------------
BEGIN;
INSERT INTO `vote_user` (`vote_id`, `user_id`) VALUES (3, 6);
INSERT INTO `vote_user` (`vote_id`, `user_id`) VALUES (4, 6);
INSERT INTO `vote_user` (`vote_id`, `user_id`) VALUES (5, 6);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
