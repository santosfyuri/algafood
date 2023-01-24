ALTER TABLE IF EXISTS public.payment_methods ADD COLUMN updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP;

COMMENT ON COLUMN public.payment_methods.updated_at IS 'Updated at';

UPDATE public.payment_methods SET updated_at = CURRENT_TIMESTAMP;

ALTER TABLE IF EXISTS public.payment_methods OWNER to postgres;