ALTER TABLE `hotel`.`reservas` 
ADD COLUMN `valor_reserva` DECIMAL(18,2) NULL DEFAULT NULL AFTER `quarto_id`;
