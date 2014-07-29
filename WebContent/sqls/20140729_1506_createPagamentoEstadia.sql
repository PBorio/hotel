CREATE TABLE `hotel`.`pagamentos_estadias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `estadia_id` INT NOT NULL,
  `pagamento_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pg_estadias_idx` (`estadia_id` ASC),
  INDEX `fk_pge_pagamentos_idx` (`pagamento_id` ASC),
  CONSTRAINT `fk_pg_estadias`
    FOREIGN KEY (`estadia_id`)
    REFERENCES `hotel`.`estadias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pge_pagamentos`
    FOREIGN KEY (`pagamento_id`)
    REFERENCES `hotel`.`pagamentos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
