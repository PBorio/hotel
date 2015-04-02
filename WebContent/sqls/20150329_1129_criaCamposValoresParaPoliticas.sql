ALTER TABLE `hotel`.`politica_precos` 
ADD COLUMN `valorACada2CriancasDe0a5` DECIMAL(18,2) NULL AFTER `valor_diaria`,
ADD COLUMN `valorCadaCrianca0a16` DECIMAL(18,2) NULL AFTER `valorACada2CriancasDe0a5`,
ADD COLUMN `valorCadaAdultoExtra` DECIMAL(18,2) NULL AFTER `valorCadaCrianca0a16`;

ALTER TABLE `hotel`.`politica_precos` 
ADD COLUMN `valorParaUmAdulto` DECIMAL(18,2) NULL AFTER `valorCadaAdultoExtra`;

ALTER TABLE `hotel`.`categorias` 
ADD COLUMN `valorACada2CriancasDe0a5` DECIMAL(18,2) NULL AFTER `valor`,
ADD COLUMN `valorCadaCrianca0a16` DECIMAL(18,2) NULL AFTER `valorACada2CriancasDe0a5`,
ADD COLUMN `valorCadaAdultoExtra` DECIMAL(18,2) NULL AFTER `valorCadaCrianca0a16`;

ALTER TABLE `hotel`.`categorias` 
ADD COLUMN `valorParaUmAdulto` DECIMAL(18,2) NULL AFTER `valorCadaAdultoExtra`;
