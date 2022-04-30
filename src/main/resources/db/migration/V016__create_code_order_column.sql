ALTER TABLE IF EXISTS public.orders ADD COLUMN code varchar(36);

UPDATE public.orders SET code = gen_random_uuid();

ALTER TABLE IF EXISTS public.orders ALTER COLUMN code SET NOT NULL;

ALTER TABLE IF EXISTS public.order ADD CONSTRAINT uq_orders_code unique (code);

COMMENT ON COLUMN public.orders.code IS 'Order code';

ALTER TABLE IF EXISTS public.orders OWNER to postgres;