CREATE TABLE public.products (
	id bigint NOT NULL,
	restaurant_id bigint NOT NULL,
	name varchar(80) NOT NULL,
	description text NOT NULL,
	price decimal(10,2) NOT NULL,
	active boolean NOT NULL,
	PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.products ADD CONSTRAINT fk_product_restaurant FOREIGN KEY (restaurant_id) REFERENCES public.restaurants (id);

COMMENT ON TABLE public.products IS 'Products table';
COMMENT ON COLUMN public.products.id IS 'Product table identifier';
COMMENT ON COLUMN public.products.restaurant_id IS 'Restaurant identifier on product';
COMMENT ON COLUMN public.products.name IS 'Product name';
COMMENT ON COLUMN public.products.price IS 'Product price';
COMMENT ON COLUMN public.products.active IS 'Product is active?';

CREATE SEQUENCE public.seq_products;
ALTER TABLE IF EXISTS public.products ALTER id SET DEFAULT NEXTVAL('seq_products');

ALTER TABLE IF EXISTS public.products OWNER to postgres;