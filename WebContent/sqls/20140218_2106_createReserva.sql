CREATE TABLE `reservas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `inicio` DATETIME NOT NULL,
  `fim` DATETIME NOT NULL,
  `numero_adultos` INT NOT NULL,
  `numero_criancas_05` INT NULL,
  `numero_criancas_6_16` INT NULL,
  `numero_criancas_17_18` INT NULL,
  `checkin` DATETIME NULL,
  `cancelamento` DATETIME NULL,
  PRIMARY KEY (`id`));