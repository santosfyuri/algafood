CREATE TABLE public.product_photos (
	product_id bigint NOT NULL,
	file_name varchar(150) NOT NULL,
	description varchar(150) NOT NULL,
	content_type varchar(80) NOT NULL,
	size int NOT NULL,
	PRIMARY KEY (product_id)
);

ALTER TABLE IF EXISTS public.product_photos ADD CONSTRAINT fk_product_photo_product FOREIGN KEY (product_id) REFERENCES public.products (id);

COMMENT ON TABLE public.product_photos IS 'Product photos table';
COMMENT ON COLUMN public.product_photos.product_id IS 'Product photo identifier';
COMMENT ON COLUMN public.product_photos.file_name IS 'File name';
COMMENT ON COLUMN public.product_photos.description IS 'Description';
COMMENT ON COLUMN public.product_photos.content_type IS 'Content type';
COMMENT ON COLUMN public.product_photos.size IS 'Size';

ALTER TABLE IF EXISTS public.product_photos OWNER to postgres;