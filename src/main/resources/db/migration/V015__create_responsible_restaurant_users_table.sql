CREATE TABLE responsible_restaurant_users (
    restaurant_id bigint NOT NULL,
    user_id bigint NOT NULL,
    primary key (restaurant_id, user_id)
);

ALTER TABLE IF EXISTS public.responsible_restaurant_users ADD CONSTRAINT fk_responsible_restaurant_users_restaurant FOREIGN KEY (restaurant_id) REFERENCES public.restaurants (id);
ALTER TABLE IF EXISTS public.responsible_restaurant_users ADD CONSTRAINT fk_responsible_restaurant_users_users FOREIGN KEY (user_id) REFERENCES public.users (id);

COMMENT ON TABLE public.responsible_restaurant_users IS 'Responsible restaurant users table';
COMMENT ON COLUMN public.responsible_restaurant_users.restaurant_id IS 'Restaurant identifier';
COMMENT ON COLUMN public.responsible_restaurant_users.user_id IS 'User identifier';

ALTER TABLE IF EXISTS public.responsible_restaurant_users OWNER to postgres;