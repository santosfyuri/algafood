CREATE TABLE public.kitchens
(
    id bigint NOT NULL,
    name varchar(60) NOT NULL,
    PRIMARY KEY (id)
);

COMMENT ON TABLE public.kitchens IS 'Kitchens table';
COMMENT ON COLUMN public.kitchens.id IS 'Kitchen table identifier';
COMMENT ON COLUMN public.kitchens.name IS 'Kitchen name';

CREATE SEQUENCE public.seq_kitchens;
ALTER TABLE IF EXISTS public.kitchens ALTER id SET DEFAULT NEXTVAL('seq_kitchens');

ALTER TABLE IF EXISTS public.kitchens OWNER to postgres;