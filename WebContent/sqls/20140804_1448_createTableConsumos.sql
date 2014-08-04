CREATE TABLE `hotel`.`consumos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantidade` INT NOT NULL,
  `preco` DECIMAL(18,2) NOT NULL,
  `produto_id` INT NOT NULL,
  `estadia_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_consumo_produto_idx` (`produto_id` ASC),
  INDEX `fk_consumo_estadia_idx` (`estadia_id` ASC),
  CONSTRAINT `fk_consumo_produto`
    FOREIGN KEY (`produto_id`)
    REFERENCES `hotel`.`produtos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_consumo_estadia`
    FOREIGN KEY (`estadia_id`)
    REFERENCES `hotel`.`estadias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
