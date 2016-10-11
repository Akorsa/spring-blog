SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authorities
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `authority_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`authority_id`),
  UNIQUE KEY `uc_authorities` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `authorities` VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for contacts
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
  `contact_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `created_by_user` varchar(50) NOT NULL,
  `creation_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_by_user` varchar(50) NOT NULL,
  `modification_time` timestamp NULL DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of contacts
-- ----------------------------
INSERT INTO `contacts` VALUES ('1', 'Summer', 'Glass', '1977-09-15', 'nec@enimSuspendisse.ca', 'admin', '2015-09-10 19:18:38', 'admin', '2015-09-10 19:18:38', '0');
INSERT INTO `contacts` VALUES ('2', 'Libby', 'Goff', '1964-07-27', 'lectus.ante.dictum@placeratvelit.ca', 'admin', '2015-09-10 19:18:38', 'admin', '2015-09-10 19:18:38', '0');
INSERT INTO `contacts` VALUES ('3', 'Shad', 'Mason', '1963-09-28', 'nunc.ac.mattis@metusVivamuseuismod.net', 'admin', '2015-09-10 19:18:38', 'admin', '2015-09-10 19:18:38', '0');
INSERT INTO `contacts` VALUES ('4', 'Aidan', 'Watkins', '1970-06-21', 'sit.amet.metus@Inmi.org', 'admin', '2015-09-10 19:18:38', 'admin', '2015-09-10 19:18:38', '0');
INSERT INTO `contacts` VALUES ('5', 'Gareth', 'Montgomery', '1979-03-22', 'Cum.sociis@amifringilla.ca', 'admin', '2015-09-10 19:18:38', 'admin', '2015-09-10 19:18:38', '0');
INSERT INTO `contacts` VALUES ('6', 'Cruz', 'Rogers', '1975-03-30', 'eget.laoreet.posuere@ornareIn.org', 'admin', '2015-09-10 19:18:38', 'admin', '2015-09-10 19:18:38', '0');
INSERT INTO `contacts` VALUES ('7', 'Freya', 'Carson', '1982-07-26', 'facilisis@aodio.org', 'admin', '2015-09-10 19:18:38', 'admin', '2015-09-10 19:18:38', '0');
INSERT INTO `contacts` VALUES ('8', 'Raymond', 'Hickman', '1968-02-24', 'Nam.nulla@pedenonummyut.edu', 'admin', '2015-09-10 19:18:38', 'admin', '2015-09-10 19:18:38', '0');
INSERT INTO `contacts` VALUES ('9', 'Denise', 'Hull', '1958-10-17', 'euismod.in.dolor@ametultricies.com', 'admin', '2015-09-10 19:18:38', 'admin', '2015-09-10 19:18:38', '0');
INSERT INTO `contacts` VALUES ('10', 'Xenos', 'Thompson', '1959-12-10', 'sit.amet.massa@elementumduiquis.ca', 'admin', '2015-09-10 19:18:38', 'admin', '2015-09-10 19:18:38', '0');

-- ----------------------------
-- Table structure for contact_hobby_ids
-- ----------------------------
DROP TABLE IF EXISTS `contact_hobby_ids`;
CREATE TABLE `contact_hobby_ids` (
  `contact_hobby_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contact_id` bigint(20) NOT NULL,
  `hobby_id` bigint(20) NOT NULL,
  PRIMARY KEY (`contact_hobby_id`),
  KEY `fk_hobby_contact_id` (`contact_id`),
  KEY `fk_hobby_hobby_id` (`hobby_id`),
  CONSTRAINT `fk_hobby_contact_id` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`contact_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_hobby_hobby_id` FOREIGN KEY (`hobby_id`) REFERENCES `hobbies` (`hobby_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of contact_hobby_ids
-- ----------------------------
INSERT INTO `contact_hobby_ids` VALUES ('1', '1', '1');
INSERT INTO `contact_hobby_ids` VALUES ('2', '1', '2');
INSERT INTO `contact_hobby_ids` VALUES ('3', '2', '3');
INSERT INTO `contact_hobby_ids` VALUES ('4', '2', '4');
INSERT INTO `contact_hobby_ids` VALUES ('5', '3', '5');
INSERT INTO `contact_hobby_ids` VALUES ('6', '3', '1');
INSERT INTO `contact_hobby_ids` VALUES ('7', '4', '2');
INSERT INTO `contact_hobby_ids` VALUES ('8', '4', '3');
INSERT INTO `contact_hobby_ids` VALUES ('9', '5', '4');
INSERT INTO `contact_hobby_ids` VALUES ('10', '5', '5');
INSERT INTO `contact_hobby_ids` VALUES ('11', '6', '1');
INSERT INTO `contact_hobby_ids` VALUES ('12', '6', '2');
INSERT INTO `contact_hobby_ids` VALUES ('13', '7', '3');
INSERT INTO `contact_hobby_ids` VALUES ('14', '7', '4');
INSERT INTO `contact_hobby_ids` VALUES ('15', '8', '5');
INSERT INTO `contact_hobby_ids` VALUES ('16', '8', '1');
INSERT INTO `contact_hobby_ids` VALUES ('17', '9', '2');
INSERT INTO `contact_hobby_ids` VALUES ('18', '9', '3');
INSERT INTO `contact_hobby_ids` VALUES ('19', '10', '4');
INSERT INTO `contact_hobby_ids` VALUES ('20', '10', '5');
INSERT INTO `contact_hobby_ids` VALUES ('21', '2', '5');

-- ----------------------------
-- Table structure for contact_phones
-- ----------------------------
DROP TABLE IF EXISTS `contact_phones`;
CREATE TABLE `contact_phones` (
  `contact_phone_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contact_id` bigint(20) NOT NULL,
  `phone_type` varchar(20) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`contact_phone_id`),
  KEY `fk_contact_phones_contact_id` (`contact_id`),
  CONSTRAINT `fk_contact_phones_contact_id` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of contact_phones
-- ----------------------------
INSERT INTO `contact_phones` VALUES ('1', '1', 'Mobile', '1-113-753-8020', '0');
INSERT INTO `contact_phones` VALUES ('2', '1', 'Home', '1-996-507-0853', '0');
INSERT INTO `contact_phones` VALUES ('3', '2', 'Mobile', '1-407-100-1341', '0');
INSERT INTO `contact_phones` VALUES ('4', '2', 'Home', '1-285-981-2510', '0');
INSERT INTO `contact_phones` VALUES ('5', '3', 'Mobile', '1-274-311-9291', '0');
INSERT INTO `contact_phones` VALUES ('6', '3', 'Home', '1-499-112-9185', '0');
INSERT INTO `contact_phones` VALUES ('7', '4', 'Mobile', '1-234-628-6511', '0');
INSERT INTO `contact_phones` VALUES ('8', '4', 'Home', '1-560-178-3273', '0');
INSERT INTO `contact_phones` VALUES ('9', '5', 'Mobile', '1-430-941-9233', '0');
INSERT INTO `contact_phones` VALUES ('10', '5', 'Home', '1-271-831-8886', '0');
INSERT INTO `contact_phones` VALUES ('11', '6', 'Mobile', '1-255-105-0103', '0');
INSERT INTO `contact_phones` VALUES ('12', '6', 'Home', '1-481-652-4155', '0');
INSERT INTO `contact_phones` VALUES ('13', '7', 'Mobile', '1-917-917-8478', '0');
INSERT INTO `contact_phones` VALUES ('14', '7', 'Home', '1-766-831-2271', '0');
INSERT INTO `contact_phones` VALUES ('15', '8', 'Mobile', '1-863-515-3218', '0');
INSERT INTO `contact_phones` VALUES ('16', '8', 'Home', '1-930-909-9849', '0');
INSERT INTO `contact_phones` VALUES ('17', '9', 'Mobile', '1-423-399-6903', '0');
INSERT INTO `contact_phones` VALUES ('18', '9', 'Home', '1-294-840-1996', '0');
INSERT INTO `contact_phones` VALUES ('19', '10', 'Mobile', '1-661-300-3848', '0');
INSERT INTO `contact_phones` VALUES ('20', '10', 'Home', '1-972-479-8970', '0');

-- ----------------------------
-- Table structure for hobbies
-- ----------------------------
DROP TABLE IF EXISTS `hobbies`;
CREATE TABLE `hobbies` (
  `hobby_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hobby_title` varchar(20) NOT NULL,
  PRIMARY KEY (`hobby_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hobbies
-- ----------------------------
INSERT INTO `hobbies` VALUES ('1', 'Jogging');
INSERT INTO `hobbies` VALUES ('2', 'Movies');
INSERT INTO `hobbies` VALUES ('3', 'Programming');
INSERT INTO `hobbies` VALUES ('4', 'Reading');
INSERT INTO `hobbies` VALUES ('5', 'Swimming');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(150) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `account_expired` tinyint(1) NOT NULL,
  `account_locked` tinyint(1) NOT NULL,
  `credentials_expired` tinyint(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', 'admin@aol.com', 'Admin', 'Jones', '1', '0', '0', '0', '$2a$10$F2a2W8RtbD99xXd9xtwjbuI4zjSYe04kS.s0FyvQcAIDJfh/6jjLW', '0');
INSERT INTO `users` VALUES ('2', 'bubba', 'user@aol.com', 'User', 'Charlie', '1', '0', '0', '0', '$2a$10$F2a2W8RtbD99xXd9xtwjbuI4zjSYe04kS.s0FyvQcAIDJfh/6jjLW', '0');
INSERT INTO `users` VALUES ('7', 'john', 'john@email.com', 'John', 'Bubkis', '1', '0', '0', '0', '$2a$10$F2a2W8RtbD99xXd9xtwjbuI4zjSYe04kS.s0FyvQcAIDJfh/6jjLW', '0');
INSERT INTO `users` VALUES ('8', 'ken', 'ken@email.com', 'Ken', 'Watts', '1', '0', '0', '0', '$2a$10$F2a2W8RtbD99xXd9xtwjbuI4zjSYe04kS.s0FyvQcAIDJfh/6jjLW', '0');

-- ----------------------------
-- Table structure for user_authorities
-- ----------------------------
DROP TABLE IF EXISTS `user_authorities`;
CREATE TABLE `user_authorities` (
  `user_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  UNIQUE KEY `uc_user_authorities` (`user_id`,`authority_id`),
  KEY `fk_user_authorities_2` (`authority_id`),
  CONSTRAINT `fk_user_authorities_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_user_authorities_2` FOREIGN KEY (`authority_id`) REFERENCES `authorities` (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_authorities
-- ----------------------------
INSERT INTO `user_authorities` VALUES ('1', '1');
INSERT INTO `user_authorities` VALUES ('1', '2');
INSERT INTO `user_authorities` VALUES ('2', '2');
INSERT INTO `user_authorities` VALUES ('7', '2');
INSERT INTO `user_authorities` VALUES ('8', '2');

-- ----------------------------
-- Table structure for user_profiles
-- ----------------------------
DROP TABLE IF EXISTS `user_profiles`;
CREATE TABLE `user_profiles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_profiles
-- ----------------------------

-- ----------------------------
-- View structure for v_contact_hobbies
-- ----------------------------
DROP VIEW IF EXISTS `v_contact_hobbies`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_contact_hobbies` AS select `contacts`.`contact_id` AS `contact_id`,`contacts`.`first_name` AS `first_name`,`contacts`.`last_name` AS `last_name`,`contacts`.`birth_date` AS `birth_date`,`contacts`.`email` AS `email`,`hobbies`.`hobby_id` AS `hobby_id`,`hobbies`.`hobby_title` AS `hobby_title` from ((`contacts` join `contact_hobby_ids` on((`contacts`.`contact_id` = `contact_hobby_ids`.`contact_id`))) join `hobbies` on((`contact_hobby_ids`.`hobby_id` = `hobbies`.`hobby_id`))) order by `contacts`.`contact_id` ;

-- ----------------------------
-- View structure for v_contact_phones
-- ----------------------------
DROP VIEW IF EXISTS `v_contact_phones`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_contact_phones` AS select `contacts`.`contact_id` AS `contact_id`,`contacts`.`first_name` AS `first_name`,`contacts`.`last_name` AS `last_name`,`contacts`.`birth_date` AS `birth_date`,`contacts`.`email` AS `email`,`contact_phones`.`phone_type` AS `phone_type`,`contact_phones`.`phone_number` AS `phone_number`,`contact_phones`.`contact_phone_id` AS `contact_phone_id` from (`contacts` join `contact_phones` on((`contacts`.`contact_id` = `contact_phones`.`contact_id`))) ;