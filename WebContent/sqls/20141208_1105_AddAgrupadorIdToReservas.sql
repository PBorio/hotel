ALTER TABLE `hotel`.`reservas` 
ADD COLUMN `agrupador_reservas_id` INT NULL AFTER `valor_reserva`,
ADD INDEX `fk_agrupador_idx` (`agrupador_reservas_id` ASC);
ALTER TABLE `hotel`.`reservas` 
ADD CONSTRAINT `fk_agrupador`
  FOREIGN KEY (`agrupador_reservas_id`)
  REFERENCES `hotel`.`agrupador_reservas` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;