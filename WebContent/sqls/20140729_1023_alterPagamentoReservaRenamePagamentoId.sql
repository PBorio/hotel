ALTER TABLE `hotel`.`pagamentos_reservas` 
DROP FOREIGN KEY `fk_pg_pagamentos`;
ALTER TABLE `hotel`.`pagamentos_reservas` 
CHANGE COLUMN `pagamentos_id` `pagamento_id` INT(11) NOT NULL ;
ALTER TABLE `hotel`.`pagamentos_reservas` 
ADD CONSTRAINT `fk_pg_pagamentos`
  FOREIGN KEY (`pagamento_id`)
  REFERENCES `hotel`.`pagamentos` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;