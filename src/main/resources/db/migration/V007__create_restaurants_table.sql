CREATE TABLE public.restaurants
(
	id bigint NOT NULL,
	kitchen_id bigint NOT NULL,
	name varchar(80) NOT NULL,
	delivery_fee numeric(10,2) NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL,
    address_city_id bigint NOT NULL,
    address_cep varchar(9) NOT NULL,
    address_street varchar(100) NOT NULL,
    address_number varchar(20) NOT NULL,
    address_complement varchar(60) null,
    address_district varchar(60) NOT NULL,
    active boolean NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.restaurants ADD CONSTRAINT fk_restaurant_kitchen FOREIGN KEY (kitchen_id) REFERENCES public.kitchens (id);
ALTER TABLE IF EXISTS public.restaurants ADD CONSTRAINT fk_restaurant_address_city FOREIGN KEY (address_city_id) REFERENCES public.cities (id);

COMMENT ON TABLE public.restaurants IS 'Restaurants table';
COMMENT ON COLUMN public.restaurants.id IS 'Restaurant table identifier';
COMMENT ON COLUMN public.restaurants.kitchen_id IS 'Kitchen identifier on Restaurant';
COMMENT ON COLUMN public.restaurants.name IS 'Restaurant name';
COMMENT ON COLUMN public.restaurants.delivery_fee IS 'Restaurant delevery fee';
COMMENT ON COLUMN public.restaurants.created_at IS 'Creation date';
COMMENT ON COLUMN public.restaurants.updated_at IS 'Update date';
COMMENT ON COLUMN public.restaurants.address_city_id IS 'Address identifier on order ';
COMMENT ON COLUMN public.restaurants.address_cep IS 'Address CEP';
COMMENT ON COLUMN public.restaurants.address_street IS 'Address street ';
COMMENT ON COLUMN public.restaurants.address_number IS 'Address number';
COMMENT ON COLUMN public.restaurants.address_complement IS 'Address complement';
COMMENT ON COLUMN public.restaurants.address_district IS 'Address district';
COMMENT ON COLUMN public.restaurants.active IS 'Restaurant is active?';

CREATE SEQUENCE public.seq_restaurants;
ALTER TABLE IF EXISTS public.restaurants ALTER id SET DEFAULT NEXTVAL('seq_restaurants');

ALTER TABLE IF EXISTS public.restaurants OWNER to postgres;