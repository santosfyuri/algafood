CREATE TABLE public.users
(
    id bigint NOT NULL,
    name varchar(80) NOT NULL,
    email varchar(80) NOT NULL,
    password varchar(200) NOT NULL,
    created_at timestamp NOT NULL,
    PRIMARY KEY (id)
);

COMMENT ON TABLE public.users IS 'Users table';
COMMENT ON COLUMN public.users.id IS 'User table identifier';
COMMENT ON COLUMN public.users.name IS 'User name';
COMMENT ON COLUMN public.users.email IS 'User e-mail';
COMMENT ON COLUMN public.users.password IS 'User password';
COMMENT ON COLUMN public.users.created_at IS 'Creation date';

CREATE SEQUENCE public.seq_users;
ALTER TABLE IF EXISTS public.users ALTER id SET DEFAULT NEXTVAL('seq_users');

ALTER TABLE IF EXISTS public.users OWNER to postgres;