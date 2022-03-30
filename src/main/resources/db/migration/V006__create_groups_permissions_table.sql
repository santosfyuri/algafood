CREATE TABLE public.groups_permissions (
	group_id bigint NOT NULL,
	permission_id bigint NOT NULL,
	PRIMARY KEY (group_id, permission_id)
);

COMMENT ON TABLE public.groups_permissions IS 'Group/Permissions table';
COMMENT ON COLUMN public.groups_permissions.group_id IS 'Group identifier';
COMMENT ON COLUMN public.groups_permissions.permission_id IS 'Permission identifier';

ALTER TABLE public.groups_permissions ADD CONSTRAINT fk_groups_permissions_permission
FOREIGN KEY (permission_id) REFERENCES public.permissions (id);

ALTER TABLE public.groups_permissions ADD CONSTRAINT fk_groups_permissions_group
FOREIGN KEY (group_id) REFERENCES public.groups (id);

ALTER TABLE IF EXISTS public.groups_permissions OWNER to postgres;