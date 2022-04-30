ALTER TABLE IF EXISTS public.restaurants ADD COLUMN open boolean NOT NULL DEFAULT FALSE;

COMMENT ON COLUMN public.restaurants.open IS 'Is the restaurant open?';
COMMENT ON COLUMN public.restaurants.active IS 'Is the restaurant active?';

UPDATE public.restaurants SET open = false;

ALTER TABLE IF EXISTS public.restaurants OWNER to postgres;