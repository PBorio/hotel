ALTER TABLE `hotel`.`hospedes` 
ADD COLUMN `passaporte` VARCHAR(255) NULL AFTER `sobrenome`,
ADD COLUMN `rne` VARCHAR(255) NULL AFTER `passaporte`;