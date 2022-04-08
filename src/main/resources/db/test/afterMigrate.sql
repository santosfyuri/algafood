DELETE FROM public.restaurants;
DELETE FROM public.cities;
DELETE FROM public.states;
DELETE FROM public.kitchens;

SELECT setval('seq_states', 1, FALSE);
SELECT setval('seq_cities', 1, FALSE);
SELECT setval('seq_kitchens', 1, FALSE);
SELECT setval('seq_restaurants', 1, FALSE);

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

INSERT INTO public.kitchens(id, name) values (nextval('seq_kitchens'), 'Tailandesa');
INSERT INTO public.kitchens(id, name) values (nextval('seq_kitchens'), 'Indiana');
INSERT INTO public.kitchens(id, name) values (nextval('seq_kitchens'), 'Chinesa');
INSERT INTO public.kitchens(id, name) values (nextval('seq_kitchens'), 'Japonesa');
INSERT INTO public.kitchens(id, name) values (nextval('seq_kitchens'), 'Italiana');
INSERT INTO public.kitchens(id, name) values (nextval('seq_kitchens'), 'Francesa');
INSERT INTO public.kitchens(id, name) values (nextval('seq_kitchens'), 'Brasileira');
INSERT INTO public.kitchens(id, name) values (nextval('seq_kitchens'), 'Portuguesa');