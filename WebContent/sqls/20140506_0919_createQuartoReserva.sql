CREATE TABLE `hotel`.`quarto_reserva` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quarto_id` INT NOT NULL,
  `reserva_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `quarto_fk_idx` (`quarto_id` ASC),
  INDEX `reserva_fk_idx` (`reserva_id` ASC),
  CONSTRAINT `quarto_fk`
    FOREIGN KEY (`quarto_id`)
    REFERENCES `hotel`.`quartos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `reserva_fk`
    FOREIGN KEY (`reserva_id`)
    REFERENCES `hotel`.`reservas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
