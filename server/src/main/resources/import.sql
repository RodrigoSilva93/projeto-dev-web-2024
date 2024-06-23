insert into tb_category (name, type) values ('Partes Externas', 'OUTSIDE_VEHICLE');
insert into tb_category (name, type) values ('Partes Internas', 'INSIDE_VEHICLE');
insert into tb_category (name, type) values ('Diversas', 'MISCELLANEOUS');
insert into tb_category (name, type) values ('Partes Mecanicas', 'MECHANICAL_PARTS');
insert into tb_category (name, type) values ('Tunagem', 'TUNING_PARTS');

insert into tb_product (name, description, price, discount, image, category_id) values ('Produto 1','Descrição do produto 1', 270.0, 0.5, '', 1);
insert into tb_product (name, description, price, discount, image, category_id) values ('Produto 2','Descrição do produto 2', 100.0, 0.3, '', 2);
insert into tb_product (name, description, price, discount, image, category_id) values ('Produto 3','Descrição do produto 3', 320.0, 0.2, '', 3);
insert into tb_product (name, description, price, discount, image, category_id) values ('Produto 4','Descrição do produto 4', 599.9, 0, '', 4);
insert into tb_product (name, description, price, discount, image, category_id) values ('Produto 5','Descrição do produto 5', 1500.2, 0.8, '', 5);

-- INSERT INTO tb_user(display_name, username, password) VALUES ('Administrador', 'admin','$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');
