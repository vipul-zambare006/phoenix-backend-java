
DROP TABLE IF EXISTS `phoenix`.`program-slot` ;
CREATE  TABLE IF NOT EXISTS `phoenix`.`program-slot` (
  `duration` INT NOT NULL ,
  `dateOfProgram` date NOT NULL ,
  `startTime` time NOT NULL ,
  `endTime` time NOT NULL ,
  `program-name` VARCHAR(45) NOT NULL ,
  `presenterId` VARCHAR(45) NOT NULL,
  `producerId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`startTime`, `dateOfProgram`) ,
  CONSTRAINT `name`
    FOREIGN KEY (`program-name` )
    REFERENCES `phoenix`.`radio-program` (`name` ),
     FOREIGN KEY (`presenterId` )
    REFERENCES `phoenix`.`user` (`id`),
     FOREIGN KEY (`producerId` )
    REFERENCES `phoenix`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE INDEX `name_program_slot` ON `phoenix`.`program-slot` (`program-name` ASC) ;
