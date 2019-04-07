-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema eventmanagement
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema eventmanagement
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eventmanagement` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `eventmanagement` ;

-- -----------------------------------------------------
-- Table `eventmanagement`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`address` (
  `id` BIGINT(20) NOT NULL,
  `apartment` VARCHAR(255) NULL DEFAULT NULL,
  `building` VARCHAR(255) NULL DEFAULT NULL,
  `city` VARCHAR(255) NULL DEFAULT NULL,
  `postal_code` VARCHAR(255) NULL DEFAULT NULL,
  `street` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`event` (
  `id` BIGINT(20) NOT NULL,
  `basic_price` DOUBLE NULL DEFAULT NULL,
  `category` VARCHAR(255) NULL DEFAULT NULL,
  `city` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `is_free` BIT(1) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `start_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`agenda_point`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`agenda_point` (
  `id` BIGINT(20) NOT NULL,
  `date` DATE NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `time` TIME NULL DEFAULT NULL,
  `address_id` BIGINT(20) NULL DEFAULT NULL,
  `event_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `key15` (`address_id` ASC) VISIBLE,
  INDEX `key14` (`event_id` ASC) VISIBLE,
  CONSTRAINT `key14`
    FOREIGN KEY (`event_id`)
    REFERENCES `eventmanagement`.`event` (`id`),
  CONSTRAINT `key15`
    FOREIGN KEY (`address_id`)
    REFERENCES `eventmanagement`.`address` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`organization`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`organization` (
  `id` BIGINT(20) NOT NULL,
  `code` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `type` VARCHAR(255) NULL DEFAULT NULL,
  `address_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `addressId` (`address_id` ASC) VISIBLE,
  CONSTRAINT `key3`
    FOREIGN KEY (`address_id`)
    REFERENCES `eventmanagement`.`address` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`equipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`equipment` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `price_per_unit` DOUBLE NULL DEFAULT NULL,
  `quantity` VARCHAR(255) NULL DEFAULT NULL,
  `type` VARCHAR(255) NULL DEFAULT NULL,
  `supplier_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `key18` (`supplier_id` ASC) VISIBLE,
  CONSTRAINT `key18`
    FOREIGN KEY (`supplier_id`)
    REFERENCES `eventmanagement`.`organization` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`agenda_point_equipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`agenda_point_equipment` (
  `agenda_point_id` BIGINT(20) NOT NULL,
  `equipment_id` BIGINT(20) NOT NULL,
  INDEX `key16` (`equipment_id` ASC) VISIBLE,
  INDEX `key17` (`agenda_point_id` ASC) VISIBLE,
  CONSTRAINT `key16`
    FOREIGN KEY (`equipment_id`)
    REFERENCES `eventmanagement`.`equipment` (`id`),
  CONSTRAINT `key17`
    FOREIGN KEY (`agenda_point_id`)
    REFERENCES `eventmanagement`.`agenda_point` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`user` (
  `id` BIGINT(20) NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `organisation_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `organizationId` (`organisation_id` ASC) VISIBLE,
  CONSTRAINT `key2`
    FOREIGN KEY (`organisation_id`)
    REFERENCES `eventmanagement`.`organization` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`agenda_point_presenters`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`agenda_point_presenters` (
  `agenda_point_id` BIGINT(20) NOT NULL,
  `presenters_id` BIGINT(20) NOT NULL,
  INDEX `key13` (`presenters_id` ASC) VISIBLE,
  INDEX `key12` (`agenda_point_id` ASC) VISIBLE,
  CONSTRAINT `key12`
    FOREIGN KEY (`agenda_point_id`)
    REFERENCES `eventmanagement`.`agenda_point` (`id`),
  CONSTRAINT `key13`
    FOREIGN KEY (`presenters_id`)
    REFERENCES `eventmanagement`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`event_organisators`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`event_organisators` (
  `event_id` BIGINT(20) NOT NULL,
  `organisators_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`event_id`, `organisators_id`),
  INDEX `key11` (`organisators_id` ASC) VISIBLE,
  CONSTRAINT `key10`
    FOREIGN KEY (`event_id`)
    REFERENCES `eventmanagement`.`event` (`id`),
  CONSTRAINT `key11`
    FOREIGN KEY (`organisators_id`)
    REFERENCES `eventmanagement`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`event_sponsors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`event_sponsors` (
  `event_id` BIGINT(20) NOT NULL,
  `sponsors_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`event_id`, `sponsors_id`),
  INDEX `key20` (`sponsors_id` ASC) VISIBLE,
  CONSTRAINT `key19`
    FOREIGN KEY (`event_id`)
    REFERENCES `eventmanagement`.`event` (`id`),
  CONSTRAINT `key20`
    FOREIGN KEY (`sponsors_id`)
    REFERENCES `eventmanagement`.`organization` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`feedback` (
  `id` BIGINT(20) NOT NULL,
  `feedback` VARCHAR(255) NULL DEFAULT NULL,
  `rating` INT(11) NOT NULL,
  `event_id` BIGINT(20) NULL DEFAULT NULL,
  `user_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `event_ID` (`event_id` ASC) VISIBLE,
  INDEX `user_ID` (`user_id` ASC) VISIBLE,
  CONSTRAINT `key6`
    FOREIGN KEY (`user_id`)
    REFERENCES `eventmanagement`.`user` (`id`),
  CONSTRAINT `key7`
    FOREIGN KEY (`event_id`)
    REFERENCES `eventmanagement`.`event` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`ticket` (
  `id` BIGINT(20) NOT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `event_id` BIGINT(20) NULL DEFAULT NULL,
  `user_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `eventID` (`event_id` ASC) VISIBLE,
  INDEX `userID` (`user_id` ASC) VISIBLE,
  CONSTRAINT `key4`
    FOREIGN KEY (`user_id`)
    REFERENCES `eventmanagement`.`user` (`id`),
  CONSTRAINT `key5`
    FOREIGN KEY (`event_id`)
    REFERENCES `eventmanagement`.`event` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`user_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`user_details` (
  `id` BIGINT(20) NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `role` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `user_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `userId` (`user_id` ASC) VISIBLE,
  CONSTRAINT `key1`
    FOREIGN KEY (`user_id`)
    REFERENCES `eventmanagement`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `eventmanagement`.`user_favourite_events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eventmanagement`.`user_favourite_events` (
  `user_id` BIGINT(20) NOT NULL,
  `favourite_events_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`user_id`, `favourite_events_id`),
  INDEX `key9` (`favourite_events_id` ASC) INVISIBLE,
  CONSTRAINT `key8`
    FOREIGN KEY (`user_id`)
    REFERENCES `eventmanagement`.`user` (`id`),
  CONSTRAINT `key9`
    FOREIGN KEY (`favourite_events_id`)
    REFERENCES `eventmanagement`.`event` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
