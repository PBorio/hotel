ALTER TABLE `hotel`.`hospedes` 
ADD COLUMN `endereco` VARCHAR(255) NULL AFTER `celular`,
ADD COLUMN `cpf` INT(11) NULL AFTER `endereco`,
ADD COLUMN `rg` INT(11) NULL AFTER `cpf`;