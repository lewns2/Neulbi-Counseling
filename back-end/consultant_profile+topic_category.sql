CREATE TABLE `ssafy_web_db`.`topic_category` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
  
  
CREATE TABLE `ssafy_web_db`.`consultant_profile` (
  `id` BIGINT(20) NOT NULL auto_increment,
  `user_id` BIGINT(20) NULL unique, 
  `topic_category_id` BIGINT(20) NULL,
  `description` VARCHAR(50) NULL,
  `consulting_cnt` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafy_web_db`.`user` (`id`),
  CONSTRAINT `topic_categoty_id`
    FOREIGN KEY (`topic_category_id`)
    REFERENCES `ssafy_web_db`.`topic_category` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;