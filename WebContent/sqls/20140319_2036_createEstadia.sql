CREATE TABLE `hotel`.`estadias` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `data_checkin` DATETIME NOT NULL,
  `previsao_checkout` DATETIME NULL,
  `data_checkout` DATETIME NULL,
  `data_cancelamento` DATETIME NULL,
  `valor_diaria` DECIMAL(18,2) NOT NULL,
  `reserva_id` INT(11) NULL,
  `quarto_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_estadia_reserva_idx` (`reserva_id` ASC),
  INDEX `fk_estadia_quarto_idx` (`quarto_id` ASC),
  CONSTRAINT `fk_estadia_reserva`
    FOREIGN KEY (`reserva_id`)
    REFERENCES `hotel`.`reservas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_estadia_quarto`
    FOREIGN KEY (`quarto_id`)
    REFERENCES `hotel`.`quartos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    