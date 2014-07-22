ALTER TABLE `hotel`.`pagamentos_reservas` 
ADD COLUMN `data_previsao` DATETIME NULL AFTER `reserva_id`,
ADD COLUMN `tipoPagamento` INT NOT NULL AFTER `data_previsao`,
ADD COLUMN `banco` VARCHAR(255) NULL AFTER `tipoPagamento`;