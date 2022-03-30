CREATE TABLE public.groups
(
    id bigint NOT NULL,
    name varchar(30) NOT NULL,
    PRIMARY KEY (id)
);

COMMENT ON TABLE public.groups IS 'Groups table';
COMMENT ON COLUMN public.groups.id IS 'Group table identifier';
COMMENT ON COLUMN public.groups.name IS 'Group name';

CREATE SEQUENCE public.seq_groups;
ALTER TABLE IF EXISTS public.groups ALTER id SET DEFAULT NEXTVAL('seq_groups');

ALTER TABLE IF EXISTS public.groups OWNER to postgres;
