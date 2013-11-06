SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `Account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Account` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Account` (
  `AccountID` INT(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(254) NOT NULL,
  `Password` VARCHAR(16) NOT NULL,
  `Fname` VARCHAR(16) NOT NULL,
  `Lname` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`AccountID`),
  UNIQUE (`Email`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `Email_UNIQUE` ON `Account` (`Email` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `AccountID_UNIQUE` ON `Account` (`AccountID` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Friend_Of`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Friend_Of` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Friend_Of` (
  `UserID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `FriendID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  CONSTRAINT `UserID`
    FOREIGN KEY (`UserID`)
    REFERENCES `Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FriendID`
    FOREIGN KEY (`FriendID`)
    REFERENCES `Account` (`AccountID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `FriendID_idx` ON `Friend_Of` (`FriendID` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Admin` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Admin` (
  `AdminID` INT(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `Password` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`AdminID`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Transaction` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Transaction` (
  `TransID` INT(16) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `LenderID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `BorrowerID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `Amount` DECIMAL(8,2) NOT NULL,
  `Description` VARCHAR(254) NULL,
  `TransDate` DATETIME NOT NULL,
  PRIMARY KEY (`TransID`),
  CONSTRAINT `LenderID`
    FOREIGN KEY (`LenderID`)
    REFERENCES `Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `BorrowerID`
    FOREIGN KEY (`BorrowerID`)
    REFERENCES `Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `LendOrder` ON `Transaction` (`LenderID` ASC, `BorrowerID` ASC, `TransDate` ASC);

SHOW WARNINGS;
CREATE INDEX `BorrowerID_idx` ON `Transaction` (`BorrowerID` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Notification` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Notification` (
  `NoteID` INT(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `SendID` INT(16) UNSIGNED ZEROFILL NOT NULL,
  `ReceiveID` INT(16) UNSIGNED ZEROFILL NOT NULL,
  `TransID` INT(16) UNSIGNED ZEROFILL NOT NULL,
  `SendInfo` VARCHAR(254) NOT NULL,
  `NoteDate` DATETIME NOT NULL,
  PRIMARY KEY (`NoteID`),
  CONSTRAINT `SendID`
    FOREIGN KEY (`SendID`)
    REFERENCES `Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `ReceiveID`
    FOREIGN KEY (`ReceiveID`)
    REFERENCES `Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `TransID`
    FOREIGN KEY (`TransID`)
    REFERENCES `Transaction` (`TransID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `NoteOrder` ON `Notification` (`SendID` ASC, `ReceiveID` ASC, `NoteDate` ASC);

SHOW WARNINGS;
CREATE INDEX `ReceiveID_idx` ON `Notification` (`ReceiveID` ASC);

SHOW WARNINGS;
CREATE INDEX `TransID_idx` ON `Notification` (`TransID` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `NetStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `NetStatus` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `NetStatus` (
  `LogID` INT(9) UNSIGNED ZEROFILL NOT NULL,
  `LogIP` VARCHAR(45) NOT NULL,
  `LogDate` TIMESTAMP NOT NULL,
  `AcctID` INT(8) UNSIGNED ZEROFILL NOT NULL,
  `MAC` VARCHAR(12) NULL,
  PRIMARY KEY (`LogID`),
  CONSTRAINT `AcctID`
    FOREIGN KEY (`AcctID`)
    REFERENCES `Account` (`AccountID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `UserID_UNIQUE` ON `NetStatus` (`AcctID` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `MAC_UNIQUE` ON `NetStatus` (`MAC` ASC);

SHOW WARNINGS;
SET SQL_MODE = '';
GRANT USAGE ON *.* TO Admin;
 DROP USER Admin;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
SHOW WARNINGS;
CREATE USER 'Admin' IDENTIFIED BY 'AdminPassword1';

GRANT ALL ON `mydb`.* TO 'Admin';
GRANT SELECT, INSERT, TRIGGER ON TABLE `mydb`.* TO 'Admin';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `mydb`.* TO 'Admin';
-- GRANT EXECUTE ON ROUTINE `mydb`.* TO 'Admin';
SHOW WARNINGS;
SET SQL_MODE = '';
GRANT USAGE ON *.* TO CommonUser;
 DROP USER CommonUser;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
SHOW WARNINGS;
CREATE USER 'CommonUser' IDENTIFIED BY 'CommonUser12345';

GRANT SELECT, INSERT, TRIGGER ON TABLE `mydb`.* TO 'CommonUser';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `mydb`.* TO 'CommonUser';
SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
