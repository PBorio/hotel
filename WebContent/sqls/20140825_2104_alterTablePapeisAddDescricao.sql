ALTER TABLE `hotel`.`papeis` 
CHANGE COLUMN `role` `role` VARCHAR(255) NOT NULL ,
ADD COLUMN `descricao` VARCHAR(255) NOT NULL AFTER `role`;

insert into comercial.papeis (id, descricao, role)
values (1, 'Usuario', 'ROLE_USER');

insert into comercial.papeis (id, descricao, role)
values (2, 'Gerente', 'ROLE_GERENTE');
