CREATE TABLE public.states
(
    id bigint NOT NULL,
    name varchar(60) NOT NULL,
    PRIMARY KEY (id)
);

COMMENT ON TABLE public.states IS 'States table';
COMMENT ON COLUMN public.states.id IS 'State table identifier';
COMMENT ON COLUMN public.states.name IS 'State name';

CREATE SEQUENCE public.seq_states;
ALTER TABLE IF EXISTS public.states ALTER id SET DEFAULT NEXTVAL('seq_states');

ALTER TABLE IF EXISTS public.cities ADD COLUMN state_id bigint NOT NULL;
ALTER TABLE IF EXISTS public.cities ADD CONSTRAINT fk_cities_states FOREIGN KEY (state_id) REFERENCES public.states (id);
ALTER TABLE IF EXISTS public.cities DROP COLUMN state_name;
ALTER TABLE IF EXISTS public.cities RENAME COLUMN city_name TO name;

ALTER TABLE IF EXISTS public.cities OWNER to postgres;
ALTER TABLE IF EXISTS public.states OWNER to postgres;
