ALTER TABLE `hotel`.`pagamentos` 
DROP FOREIGN KEY `fk_pagamentoreserva`;
ALTER TABLE `hotel`.`pagamentos` 
DROP COLUMN `reserva_id`,
DROP INDEX `fk_pagamentoreserva_idx` ;
