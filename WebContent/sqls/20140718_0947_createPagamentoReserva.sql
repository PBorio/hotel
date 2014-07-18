CREATE TABLE `hotel`.`pagamentos_reservas` (
  `id` INT NOT NULL,
  `data_pagamento` DATETIME NOT NULL,
  `valor` VARCHAR(45) NOT NULL,
  `reserva_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pagamentoreserva_idx` (`reserva_id` ASC),
  CONSTRAINT `fk_pagamentoreserva`
    FOREIGN KEY (`reserva_id`)
    REFERENCES `hotel`.`reservas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
