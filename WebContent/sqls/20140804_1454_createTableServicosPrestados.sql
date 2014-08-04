CREATE TABLE `hotel`.`servicos_prestados` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `valor` DECIMAL(18,2) NOT NULL,
  `observacao` VARCHAR(4000) NULL,
  `estadia_id` INT NOT NULL,
  `servico_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_servico_estadia_idx` (`estadia_id` ASC),
  INDEX `fk_servico_servico_idx` (`servico_id` ASC),
  CONSTRAINT `fk_servico_estadia`
    FOREIGN KEY (`estadia_id`)
    REFERENCES `hotel`.`estadias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_servico_servico`
    FOREIGN KEY (`servico_id`)
    REFERENCES `hotel`.`servicos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
