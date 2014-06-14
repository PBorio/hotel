ALTER TABLE `hotel`.`reservas` 
DROP FOREIGN KEY `fk_reserva_quarto`;
ALTER TABLE `hotel`.`reservas` 
DROP COLUMN `quarto_id`,
DROP INDEX `fk_reserva_quarto_idx` ;