DELETE FROM public.users_groups;
DELETE FROM public.groups_permissions;
DELETE FROM public.restaurants_payment_methods;
DELETE FROM public.responsible_restaurant_users;
DELETE FROM public.order_items;
DELETE FROM public.orders;
DELETE FROM public.products;
DELETE FROM public.restaurants;
DELETE FROM public.cities;
DELETE FROM public.kitchens;
DELETE FROM public.states;
DELETE FROM public.payment_methods;
DELETE FROM public.groups;
DELETE FROM public.permissions;
DELETE FROM public.users;

SELECT setval('seq_cities', 1, FALSE);
SELECT setval('seq_states', 1, FALSE);
SELECT setval('seq_kitchens', 1, FALSE);
SELECT setval('seq_restaurants', 1, FALSE);
SELECT setval('seq_orders', 1, FALSE);
SELECT setval('seq_order_items', 1, FALSE);
SELECT setval('seq_payment_methods', 1, FALSE);
SELECT setval('seq_permissions', 1, FALSE);
SELECT setval('seq_products', 1, FALSE);
SELECT setval('seq_groups', 1, FALSE);
SELECT setval('seq_users', 1, FALSE);

INSERT INTO public.states(id, name) VALUES (nextval('seq_states'), 'Minas Gerais');
INSERT INTO public.states(id, name) VALUES (nextval('seq_states'), 'São Paulo');
INSERT INTO public.states(id, name) VALUES (nextval('seq_states'), 'Rio Grande do Sul');

INSERT INTO public.cities(id, name, state_id) VALUES (nextval('seq_cities'), 'Porto Alegre', 3);
INSERT INTO public.cities(id, name, state_id) VALUES (nextval('seq_cities'), 'Torres', 3);
INSERT INTO public.cities(id, name, state_id) VALUES (nextval('seq_cities'), 'Arroio do Sal', 3);
INSERT INTO public.cities(id, name, state_id) VALUES (nextval('seq_cities'), 'Campinas', 2);
INSERT INTO public.cities(id, name, state_id) VALUES (nextval('seq_cities'), 'Santos', 2);
INSERT INTO public.cities(id, name, state_id) VALUES (nextval('seq_cities'), 'Belo Horizonte', 1);
INSERT INTO public.cities(id, name, state_id) VALUES (nextval('seq_cities'), 'Uberlândia', 1);

INSERT INTO public.kitchens(id, name) VALUES (nextval('seq_kitchens'), 'Tailandesa');
INSERT INTO public.kitchens(id, name) VALUES (nextval('seq_kitchens'), 'Indiana');
INSERT INTO public.kitchens(id, name) VALUES (nextval('seq_kitchens'), 'Chinesa');
INSERT INTO public.kitchens(id, name) VALUES (nextval('seq_kitchens'), 'Japonesa');
INSERT INTO public.kitchens(id, name) VALUES (nextval('seq_kitchens'), 'Italiana');
INSERT INTO public.kitchens(id, name) VALUES (nextval('seq_kitchens'), 'Francesa');
INSERT INTO public.kitchens(id, name) VALUES (nextval('seq_kitchens'), 'Brasileira');
INSERT INTO public.kitchens(id, name) VALUES (nextval('seq_kitchens'), 'Portuguesa');

INSERT INTO public.restaurants(id, name, delivery_fee, kitchen_id, created_at, updated_at, active, open, address_city_id, address_cep, address_street, address_number, address_district) VALUES (1, 'Thai Gourmet', 10, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO public.restaurants(id, name, delivery_fee, kitchen_id, created_at, updated_at, active, open, address_city_id, address_cep, address_street, address_number, address_district) VALUES (2, 'Thai Delivery', 9.50, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, true, 2, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO public.restaurants(id, name, delivery_fee, kitchen_id, created_at, updated_at, active, open, address_city_id, address_cep, address_street, address_number, address_district) VALUES (3, 'Tuk Tuk Comida Indiana', 15, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, true, 3, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO public.restaurants(id, name, delivery_fee, kitchen_id, created_at, updated_at, active, open, address_city_id, address_cep, address_street, address_number, address_district) VALUES (4, 'Java Steakhouse', 12, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, true, 4, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO public.restaurants(id, name, delivery_fee, kitchen_id, created_at, updated_at, active, open, address_city_id, address_cep, address_street, address_number, address_district) VALUES (5, 'Lanchonete do Tio Sam', 11, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, true, 5, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO public.restaurants(id, name, delivery_fee, kitchen_id, created_at, updated_at, active, open, address_city_id, address_cep, address_street, address_number, address_district) VALUES (6, 'Bar da Maria', 6, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');

INSERT INTO public.payment_methods (id, description) VALUES (1, 'Cartão de crédito');
INSERT INTO public.payment_methods (id, description) VALUES (2, 'Cartão de débito');
INSERT INTO public.payment_methods (id, description) VALUES (3, 'Dinheiro');

INSERT INTO public.permissions(id, name, description) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO public.permissions(id, name, description) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT INTO public.restaurants_payment_methods(restaurant_id, payment_method_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

INSERT INTO public.products(id, name, description, price, active, restaurant_id) VALUES (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, true, 1);
INSERT INTO public.products(id, name, description, price, active, restaurant_id) VALUES (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110, true, 1);

INSERT INTO public.products(id, name, description, price, active, restaurant_id) VALUES (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, true, 2);

INSERT INTO public.products(id, name, description, price, active, restaurant_id) VALUES (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, true, 3);
INSERT INTO public.products(id, name, description, price, active, restaurant_id) VALUES (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, true, 3);

INSERT INTO public.products(id, name, description, price, active, restaurant_id) VALUES (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, true, 4);
INSERT INTO public.products(id, name, description, price, active, restaurant_id) VALUES (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, true, 4);

INSERT INTO public.products(id, name, description, price, active, restaurant_id) VALUES (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, true, 5);

INSERT INTO public.products(id, name, description, price, active, restaurant_id) VALUES (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, true, 6);

INSERT INTO public.groups(id, name) VALUES (1, 'Gerente');
INSERT INTO public.groups(id, name) VALUES (2, 'Vendedor');
INSERT INTO public.groups(id, name) VALUES (3, 'Secretária');
INSERT INTO public.groups(id, name) VALUES (4, 'Cadastrador');

INSERT INTO public.groups_permissions(group_id, permission_id) VALUES (1, 1);
INSERT INTO public.groups_permissions(group_id, permission_id) VALUES (1, 2);
INSERT INTO public.groups_permissions(group_id, permission_id) VALUES (2, 1);
INSERT INTO public.groups_permissions(group_id, permission_id) VALUES (2, 2);
INSERT INTO public.groups_permissions(group_id, permission_id) VALUES (3, 1);

INSERT INTO public.users(id, name, email, password, created_at) VALUES (1, 'João da Silva', 'joao.silva@algafood.com.br', '123', CURRENT_TIMESTAMP);
INSERT INTO public.users(id, name, email, password, created_at) VALUES (2, 'José Matos', 'jose.matos@algafood.com.br', '321', CURRENT_TIMESTAMP);

INSERT INTO public.users_groups(user_id, group_id) VALUES (1, 1);
INSERT INTO public.users_groups(user_id, group_id) VALUES (1, 2);
INSERT INTO public.users_groups(user_id, group_id) VALUES (2, 2);

INSERT INTO public.responsible_restaurant_users (restaurant_id, user_id) VALUES (1, 1), (3, 2);

INSERT INTO public.orders(id, code, restaurant_id, user_client_id, payment_method_id, address_city_id, address_cep, address_street, address_number, address_complement, address_district, status, created_at, subtotal, delivery_fee, total) VALUES (1, gen_random_uuid(), 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil', 'CRIADO', CURRENT_TIMESTAMP, 298.90, 10, 308.90);
INSERT INTO public.order_items (id, order_id, product_id, quantity, unit_price, total_price, note) VALUES (1, 1, 1, 1, 78.9, 78.9, null);
INSERT INTO public.order_items (id, order_id, product_id, quantity, unit_price, total_price, note) VALUES (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');

INSERT INTO public.orders(id, code, restaurant_id, user_client_id, payment_method_id, address_city_id, address_cep, address_street, address_number, address_complement, address_district, status, created_at, subtotal, delivery_fee, total) VALUES (2, gen_random_uuid(), 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro', 'CRIADO', CURRENT_TIMESTAMP, 79, 0, 79);
INSERT INTO public.order_items (id, order_id, product_id, quantity, unit_price, total_price, note) VALUES (3, 2, 6, 1, 79, 79, 'Ao ponto');
