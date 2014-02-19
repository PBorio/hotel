ALTER TABLE `reservas` 
ADD COLUMN `quarto_id` INT NOT NULL AFTER `hospede_id`,
ADD INDEX `fk_reserva_quarto_idx` (`quarto_id` ASC);
ALTER TABLE `hotel`.`reservas` 
ADD CONSTRAINT `fk_reserva_quarto`
  FOREIGN KEY (`quarto_id`)
  REFERENCES `hotel`.`quartos` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
