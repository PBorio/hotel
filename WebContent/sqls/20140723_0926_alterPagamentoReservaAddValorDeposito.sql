ALTER TABLE `hotel`.`pagamentos_reservas` 
CHANGE COLUMN `valor` `valor` DECIMAL(18,2) NULL ,
ADD COLUMN `valor_deposito` DECIMAL(18,2) NULL DEFAULT NULL AFTER `data_deposito`;
