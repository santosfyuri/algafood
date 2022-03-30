CREATE TABLE public.restaurants_payment_methods (
	restaurant_id bigint NOT NULL,
	payment_method_id bigint NOT NULL,
	PRIMARY KEY (restaurant_id, payment_method_id)
);

ALTER TABLE IF EXISTS public.restaurants_payment_methods ADD CONSTRAINT fk_restaurants_payment_methods_payment_method FOREIGN KEY (payment_method_id) REFERENCES public.payment_methods (id);
ALTER TABLE IF EXISTS public.restaurants_payment_methods ADD CONSTRAINT fk_restaurants_payment_methods_restaurant FOREIGN KEY (restaurant_id) REFERENCES public.restaurants (id);

COMMENT ON TABLE public.restaurants_payment_methods IS 'Restaurants/Payment Methods table';
COMMENT ON COLUMN public.restaurants_payment_methods.restaurant_id IS 'Restaurant identifier';
COMMENT ON COLUMN public.restaurants_payment_methods.payment_method_id IS 'Payment method identifier';

ALTER TABLE IF EXISTS public.groups_permissions OWNER to postgres;