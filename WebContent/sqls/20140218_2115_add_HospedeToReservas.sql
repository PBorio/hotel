ALTER TABLE `reservas` 
ADD COLUMN `hospede_id` INT NOT NULL AFTER `cancelamento`,
ADD INDEX `fk_reserva_hospede_idx` (`hospede_id` ASC);
ALTER TABLE `reservas` 
ADD CONSTRAINT `fk_reserva_hospede`
  FOREIGN KEY (`hospede_id`)
  REFERENCES `hospedes` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;