CREATE TABLE `politica_precos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(128) NOT NULL,
  `categoria_id` INT NOT NULL,
  `inicio` DATETIME NOT NULL,
  `fim` DATETIME NOT NULL,
  `valor_diaria` DECIMAL(18,2) NOT NULL,
  `padrao` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_politica_categoria_idx` (`categoria_id` ASC),
  CONSTRAINT `fk_politica_categoria`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `hotel`.`categorias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
