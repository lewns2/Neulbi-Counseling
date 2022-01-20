DROP TABLE IF EXISTS `mask`;
CREATE TABLE `mask` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `img` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

LOCK TABLES `mask` WRITE;
INSERT INTO `mask` VALUES (1,'white mask',NULL);
UNLOCK TABLES;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES (1,'user'),(2,'admin');
UNLOCK TABLES;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT '1',
  `mask_id` bigint(20) DEFAULT '1',
  `nickname` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `is_consultant` bit(1) DEFAULT false,
  `point_tot` double DEFAULT '0',
  `profile_img` varchar(255) DEFAULT NULL,
  `is_penalty` bit(1) DEFAULT false,
  `report_cnt` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `user_role_fk_idx` (`role_id`),
  KEY `user_mask_fk_idx` (`mask_id`),
  CONSTRAINT `user_mask_fk` FOREIGN KEY (`mask_id`) REFERENCES `mask` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user` WRITE;
UNLOCK TABLES;