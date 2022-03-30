CREATE TABLE public.users_groups (
	user_id bigint not null,
	group_id bigint not null,
	PRIMARY KEY (user_id, group_id)
);

COMMENT ON TABLE public.users_groups IS 'Users/Groups table';
COMMENT ON COLUMN public.users_groups.user_id IS 'User identifier';
COMMENT ON COLUMN public.users_groups.group_id IS 'Group identifier';

ALTER TABLE public.users_groups ADD CONSTRAINT fk_users_groups_user
FOREIGN KEY (user_id) REFERENCES public.users (id);

ALTER TABLE public.users_groups ADD CONSTRAINT fk_users_groups_group
FOREIGN KEY (group_id) REFERENCES public.groups (id);

ALTER TABLE IF EXISTS public.users_groups OWNER to postgres;