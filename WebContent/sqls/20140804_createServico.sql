CREATE TABLE `hotel`.`servicos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NOT NULL,
  `valor_sugerido` DECIMAL(18,2) NOT NULL,
  `observacao` VARCHAR(4000) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `descricao_UNIQUE` (`descricao` ASC));