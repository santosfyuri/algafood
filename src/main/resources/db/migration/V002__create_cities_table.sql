CREATE TABLE public.cities
(
    id bigint NOT NULL,
    city_name varchar(80) NOT NULL,
    state_name varchar(80) NOT NULL,
    PRIMARY KEY (id)
);

COMMENT ON TABLE public.cities IS 'Cities table';
COMMENT ON COLUMN public.cities.id IS 'City table identifier';
COMMENT ON COLUMN public.cities.city_name IS 'City name';
COMMENT ON COLUMN public.cities.state_name IS 'State name';

CREATE SEQUENCE public.seq_cities;
ALTER TABLE IF EXISTS public.cities ALTER id SET DEFAULT NEXTVAL('seq_cities');

ALTER TABLE IF EXISTS public.cities OWNER to postgres;