ALTER TABLE `hotel`.`quartos` 
ADD COLUMN `categoria_id` INT(11) NULL AFTER `observacao`,
ADD INDEX `FK_QUARTO_CATEGORIA_idx` (`categoria_id` ASC);
ALTER TABLE `hotel`.`quartos` 
ADD CONSTRAINT `FK_QUARTO_CATEGORIA`
  FOREIGN KEY (`categoria_id`)
  REFERENCES `hotel`.`categorias` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;