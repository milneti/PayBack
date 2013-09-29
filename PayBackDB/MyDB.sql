SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Account` (
  `AccountID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `Email` TINYTEXT NOT NULL,
  `Password` VARCHAR(16) NOT NULL,
  `Fname` VARCHAR(16) NOT NULL,
  `Lname` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`AccountID`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC),
  UNIQUE INDEX `AccountID_UNIQUE` (`AccountID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Friend_Of`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Friend_Of` (
  `UserID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `FriendID` INT(8) UNSIGNED ZEROFILL NULL,
  PRIMARY KEY (`UserID`),
  INDEX `AccountID_idx` (`UserID` ASC, `FriendID` ASC),
  CONSTRAINT `AccountID`
    FOREIGN KEY (`UserID` , `FriendID`)
    REFERENCES `mydb`.`Account` (`AccountID` , `AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Admin` (
  `AdminID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `Password` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`AdminID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Transaction` (
  `TransID` INT(16) UNSIGNED ZEROFILL NOT NULL,
  `LenderID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `BorrowerID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `Amount` DECIMAL(8,2) NOT NULL,
  `Description` TINYTEXT NULL,
  `TransDate` DATETIME NOT NULL,
  PRIMARY KEY (`TransID`),
  INDEX `LendOrder` (`LenderID` ASC, `BorrowerID` ASC, `TransDate` ASC),
  INDEX `BorrowerID_idx` (`BorrowerID` ASC),
  CONSTRAINT `LenderID`
    FOREIGN KEY (`LenderID`)
    REFERENCES `mydb`.`Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `BorrowerID`
    FOREIGN KEY (`BorrowerID`)
    REFERENCES `mydb`.`Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Notification` (
  `NoteID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `SendID` INT(16) UNSIGNED ZEROFILL NOT NULL,
  `ReceiveID` INT(16) UNSIGNED ZEROFILL NOT NULL,
  `TransID` INT(16) UNSIGNED ZEROFILL NOT NULL,
  `SendInfo` TINYTEXT NOT NULL,
  `NoteDate` DATETIME NOT NULL,
  PRIMARY KEY (`NoteID`),
  INDEX `NoteOrder` (`SendID` ASC, `ReceiveID` ASC, `NoteDate` ASC),
  INDEX `ReceiveID_idx` (`ReceiveID` ASC),
  INDEX `TransID_idx` (`TransID` ASC),
  CONSTRAINT `SendID`
    FOREIGN KEY (`SendID`)
    REFERENCES `mydb`.`Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `ReceiveID`
    FOREIGN KEY (`ReceiveID`)
    REFERENCES `mydb`.`Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `TransID`
    FOREIGN KEY (`TransID`)
    REFERENCES `mydb`.`Transaction` (`TransID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`NetStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`NetStatus` (
  `LogID` INT(9) UNSIGNED ZEROFILL NOT NULL,
  `LogIP` VARCHAR(45) NOT NULL,
  `LogDate` TIMESTAMP NOT NULL,
  `UserID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `MAC` VARCHAR(12) NULL,
  PRIMARY KEY (`LogID`),
  UNIQUE INDEX `UserID_UNIQUE` (`UserID` ASC),
  UNIQUE INDEX `MAC_UNIQUE` (`MAC` ASC),
  CONSTRAINT `UserID`
    FOREIGN KEY (`UserID`)
    REFERENCES `mydb`.`Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`table1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`table1` (
  `UserID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `MemberID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `GroupName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UserID`),
  INDEX `MemberID_idx` (`MemberID` ASC),
  CONSTRAINT `UserID`
    FOREIGN KEY (`UserID`)
    REFERENCES `mydb`.`Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `MemberID`
    FOREIGN KEY (`MemberID`)
    REFERENCES `mydb`.`Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
