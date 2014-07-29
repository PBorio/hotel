CREATE TABLE `hotel`.`pagamentos_reservas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reserva_id` INT NOT NULL,
  `pagamentos_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pg_reserva_idx` (`reserva_id` ASC),
  INDEX `fk_pg_pagamentos_idx` (`pagamentos_id` ASC),
  CONSTRAINT `fk_pg_reserva`
    FOREIGN KEY (`reserva_id`)
    REFERENCES `hotel`.`reservas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pg_pagamentos`
    FOREIGN KEY (`pagamentos_id`)
    REFERENCES `hotel`.`pagamentos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    
