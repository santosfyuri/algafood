CREATE TABLE public.payment_methods (
	id bigint NOT NULL,
	description varchar(60) NOT NULL,
	PRIMARY KEY (id)
);

COMMENT ON TABLE public.payment_methods IS 'Payment methods table';
COMMENT ON COLUMN public.payment_methods.id IS 'Payment method table identifier';
COMMENT ON COLUMN public.payment_methods.description IS 'Payment method description';

CREATE SEQUENCE public.seq_payment_methods;
ALTER TABLE IF EXISTS public.payment_methods ALTER id SET DEFAULT NEXTVAL('seq_payment_methods');

ALTER TABLE IF EXISTS public.payment_methods OWNER to postgres;