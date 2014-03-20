CREATE TABLE `hotel`.`hospedes_estadias` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `hospede_id` INT(11) NOT NULL,
  `estadia_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_he_hospede_idx` (`hospede_id` ASC),
  INDEX `fk_he_estadia_idx` (`estadia_id` ASC),
  CONSTRAINT `fk_he_hospede`
    FOREIGN KEY (`hospede_id`)
    REFERENCES `hotel`.`hospedes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_he_estadia`
    FOREIGN KEY (`estadia_id`)
    REFERENCES `hotel`.`estadias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

