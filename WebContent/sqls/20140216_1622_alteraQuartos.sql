ALTER TABLE `hotel`.`quartos` 
CHANGE COLUMN `observacao` `observacao` VARCHAR(2000) NULL DEFAULT NULL ,
ADD UNIQUE INDEX `numero_UNIQUE` (`numero` ASC);