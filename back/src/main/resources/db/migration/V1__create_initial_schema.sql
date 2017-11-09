-- task_tracker


CREATE TABLE role (
  `id` VARCHAR(36) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE user (
  `id` VARCHAR(36) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role_idx` (`role_id` ASC),
  INDEX `email_idx` (`email` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `task_tracker`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `task_tracker`.`comment` (
  `id` VARCHAR(36) NOT NULL,
  `text` TEXT(500) NOT NULL,
  `user_id` VARCHAR(36) NOT NULL,
  `creation_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `task_tracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `task_tracker`.`project` (
  `id` VARCHAR(36) NOT NULL,
  `description` TEXT(400) NOT NULL,
  `start_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_time` TIMESTAMP NULL,
  `status` ENUM('DEVELOPING', 'COMPLETED') NOT NULL DEFAULT 'DEVELOPING',
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `task_tracker`.`task` (
  `id` VARCHAR(36) NOT NULL,
  `description` TEXT(200) NOT NULL,
  `project_id` VARCHAR(36) NOT NULL,
  `start_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_time` TIMESTAMP NULL,
  `status` ENUM('APPOINTED', 'PERFORMING', 'FULFILLED', 'VERIFIED') NOT NULL DEFAULT 'APPOINTED',
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_task_project1_idx` (`project_id` ASC),
  CONSTRAINT `fk_task_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `task_tracker`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `task_tracker`.`task_has_comment` (
  `task_id` VARCHAR(36) NOT NULL,
  `comment_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`task_id`, `comment_id`),
  INDEX `fk_task_has_comment_comment1_idx` (`comment_id` ASC),
  INDEX `fk_task_has_comment_task1_idx` (`task_id` ASC),
  CONSTRAINT `fk_task_has_comment_task1`
    FOREIGN KEY (`task_id`)
    REFERENCES `task_tracker`.`task` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_has_comment_comment1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `task_tracker`.`comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `task_tracker`.`project_has_comment` (
  `project_id` VARCHAR(36) NOT NULL,
  `comment_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`project_id`, `comment_id`),
  INDEX `fk_project_has_comment_comment1_idx` (`comment_id` ASC),
  INDEX `fk_project_has_comment_project1_idx` (`project_id` ASC),
  CONSTRAINT `fk_project_has_comment_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `task_tracker`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_has_comment_comment1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `task_tracker`.`comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `task_tracker`.`user_has_task` (
  `task_id` VARCHAR(36) NOT NULL,
  `user_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`task_id`, `user_id`),
  INDEX `fk_task_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_task_has_user_task1_idx` (`task_id` ASC),
  CONSTRAINT `fk_task_has_user_task1`
    FOREIGN KEY (`task_id`)
    REFERENCES `task_tracker`.`task` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_task_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `task_tracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `task_tracker`.`user_in_project` (
  `project_id` VARCHAR(36) NOT NULL,
  `user_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`project_id`, `user_id`),
  INDEX `fk_project_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_project_has_user_project1_idx` (`project_id` ASC),
  CONSTRAINT `fk_project_has_user_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `task_tracker`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `task_tracker`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

DELIMITER $$
CREATE TRIGGER `task_tracker`.`role_BEFORE_INSERT` BEFORE INSERT ON `role` FOR EACH ROW
  BEGIN
    SET NEW.id = UUID();
  END$$

CREATE TRIGGER `task_tracker`.`user_BEFORE_INSERT` BEFORE INSERT ON `user` FOR EACH ROW
  BEGIN
    SET NEW.id = UUID();
  END$$

CREATE TRIGGER `task_tracker`.`comment_BEFORE_INSERT` BEFORE INSERT ON `comment` FOR EACH ROW
  BEGIN
    SET NEW.id = UUID();
  END$$

CREATE TRIGGER `task_tracker`.`project_BEFORE_INSERT` BEFORE INSERT ON `project` FOR EACH ROW
  BEGIN
    SET NEW.id = UUID();
  END$$

CREATE TRIGGER `task_tracker`.`task_BEFORE_INSERT` BEFORE INSERT ON `task` FOR EACH ROW
  BEGIN
    SET NEW.id = UUID();
  END$$


DELIMITER ;

START TRANSACTION;
  INSERT INTO `role` (`id`, `name`) VALUES (uuid(), 'manager');
  INSERT INTO `role` (`id`, `name`) VALUES (uuid(), 'developer');
COMMIT;

