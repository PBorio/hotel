ALTER TABLE `reservas` 
ADD COLUMN `valor_diaria` DECIMAL(18,2) NOT NULL AFTER `quarto_id`;