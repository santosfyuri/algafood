insert into kitchens (id, name) values (nextval('seq_kitchens'), 'Tailandesa');
insert into kitchens (id, name) values (nextval('seq_kitchens'), 'Indiana');
insert into kitchens (id, name) values (nextval('seq_kitchens'), 'Chinesa');
insert into kitchens (id, name) values (nextval('seq_kitchens'), 'Japonesa');
insert into kitchens (id, name) values (nextval('seq_kitchens'), 'Italiana');
insert into kitchens (id, name) values (nextval('seq_kitchens'), 'Francesa');
insert into kitchens (id, name) values (nextval('seq_kitchens'), 'Brasileira');
insert into kitchens (id, name) values (nextval('seq_kitchens'), 'Portuguesa');

insert into restaurants (id, name, delivery_fee, kitchen_id) values (nextval('seq_restaurants'), 'Thai Gourmet', 10, 1);
insert into restaurants (id, name, delivery_fee, kitchen_id) values (nextval('seq_restaurants'), 'Thai Delivery', 9.50, 1);
insert into restaurants (id, name, delivery_fee, kitchen_id) values (nextval('seq_restaurants'), 'Tuk Tuk Comida Indiana', 15, 2);

insert into states (id, name) values (1, 'Minas Gerais');
insert into states (id, name) values (2, 'São Paulo');
insert into states (id, name) values (3, 'Ceará');

insert into cities (id, name, state_id) values (1, 'Uberlândia', 1);
insert into cities (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into cities (id, name, state_id) values (3, 'São Paulo', 2);
insert into cities (id, name, state_id) values (4, 'Campinas', 2);
insert into cities (id, name, state_id) values (5, 'Fortaleza', 3);

insert into payment_methods (id, description) values (1, 'Cartão de crédito');
insert into payment_methods (id, description) values (2, 'Cartão de débito');
insert into payment_methods (id, description) values (3, 'Dinheiro');

insert into permissions (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissions (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');