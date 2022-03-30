CREATE TABLE public.permissions
(
    id bigint NOT NULL,
    name varchar(30) NOT NULL,
    description varchar(200) NOT NULL,
    PRIMARY KEY (id)
);

COMMENT ON TABLE public.permissions IS 'Permissions table';
COMMENT ON COLUMN public.permissions.id IS 'Permission table identifier';
COMMENT ON COLUMN public.permissions.name IS 'Permission name';
COMMENT ON COLUMN public.permissions.description IS 'Permission description';

CREATE SEQUENCE public.seq_permissions;
ALTER TABLE IF EXISTS public.permissions ALTER id SET DEFAULT NEXTVAL('seq_permissions');

ALTER TABLE IF EXISTS public.permissions OWNER to postgres;