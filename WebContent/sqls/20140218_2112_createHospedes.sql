CREATE TABLE `hospedes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `cidade` VARCHAR(255) NULL,
  `estado` VARCHAR(2) NULL,
  `email` VARCHAR(128) NOT NULL,
  `telefone` VARCHAR(32) NOT NULL,
  `celular` VARCHAR(32) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));