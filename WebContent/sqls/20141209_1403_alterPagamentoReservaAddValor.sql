ALTER TABLE `hotel`.`pagamentos_reservas` 
ADD COLUMN `valor` DECIMAL(18,2) NULL AFTER `pagamento_id`;