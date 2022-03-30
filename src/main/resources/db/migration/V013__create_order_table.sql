CREATE TABLE public.orders (
    id bigint NOT NULL,
    subtotal NUMERIC(10,2) NOT NULL,
    shipping_fee NUMERIC(10,2) NOT NULL,
    amount NUMERIC(10,2) NOT NULL,
    restaurant_id bigint NOT NULL,
    user_client_id bigint NOT NULL,
    payment_method_id bigint NOT NULL,
    address_city_id bigint NOT NULL,
    address_cep varchar(9) NOT NULL,
    address_street varchar(100) NOT NULL,
    address_number varchar(20) NOT NULL,
    address_complement varchar(60) null,
    address_district varchar(60) NOT NULL,
    status varchar(10) NOT NULL,
    created_at timestamp NOT NULL,
    confirmation_date timestamp,
    cancellation_date timestamp,
    delivery_date timestamp,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.orders ADD CONSTRAINT fk_order_address_city FOREIGN KEY (address_city_id) REFERENCES public.cities (id);
ALTER TABLE IF EXISTS public.orders ADD CONSTRAINT fk_order_restaurant FOREIGN KEY (restaurant_id) REFERENCES public.restaurants (id);
ALTER TABLE IF EXISTS public.orders ADD CONSTRAINT fk_order_user_client FOREIGN KEY (user_client_id) REFERENCES public.users (id);
ALTER TABLE IF EXISTS public.orders ADD CONSTRAINT fk_order_payment_method FOREIGN KEY (payment_method_id) REFERENCES public.payment_methods (id);

COMMENT ON TABLE public.orders IS 'Orders table';
COMMENT ON COLUMN public.orders.id IS 'Order table identifier';
COMMENT ON COLUMN public.orders.subtotal IS 'Order subtotal';
COMMENT ON COLUMN public.orders.shipping_fee IS 'Order shipping fee';
COMMENT ON COLUMN public.orders.amount IS 'Order amount';
COMMENT ON COLUMN public.orders.restaurant_id IS 'Restaurant identifier on order';
COMMENT ON COLUMN public.orders.user_client_id IS 'Customer identifier in the order ';
COMMENT ON COLUMN public.orders.payment_method_id IS 'Payment method identifier on order';
COMMENT ON COLUMN public.orders.address_city_id IS 'Address identifier on order ';
COMMENT ON COLUMN public.orders.address_cep IS 'Address CEP';
COMMENT ON COLUMN public.orders.address_street IS 'Address street ';
COMMENT ON COLUMN public.orders.address_number IS 'Address number';
COMMENT ON COLUMN public.orders.address_complement IS 'Address complement';
COMMENT ON COLUMN public.orders.address_district IS 'Address district';
COMMENT ON COLUMN public.orders.status IS 'Order status';
COMMENT ON COLUMN public.orders.created_at IS 'Order creation date ';
COMMENT ON COLUMN public.orders.confirmation_date IS 'Order confirmation date ';
COMMENT ON COLUMN public.orders.cancellation_date IS 'Order cancellation date ';
COMMENT ON COLUMN public.orders.delivery_date IS 'Order delivery date';

CREATE TABLE public.order_items (
    id bigint NOT NULL,
    quantity smallint NOT NULL,
    unit_price numeric(10,2) NOT NULL,
    total_price numeric(10,2) NOT NULL,
    note text,
    order_id bigint NOT NULL,
    product_id bigint NOT NULL,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX uk_order_item_product ON public.order_items (order_id, product_id);

ALTER TABLE IF EXISTS public.order_items ADD CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES public.orders (id);
ALTER TABLE IF EXISTS public.order_items ADD CONSTRAINT fk_order_item_product FOREIGN KEY (product_id) REFERENCES public.products (id);

COMMENT ON TABLE public.order_items IS 'Order items table';
COMMENT ON COLUMN public.order_items.id IS 'Order item table identifier';
COMMENT ON COLUMN public.order_items.quantity IS 'Order item quantity';
COMMENT ON COLUMN public.order_items.unit_price IS 'Order item unit price';
COMMENT ON COLUMN public.order_items.total_price IS 'Order item total price';
COMMENT ON COLUMN public.order_items.note IS 'Order item note';
COMMENT ON COLUMN public.order_items.order_id IS 'Order identifier';
COMMENT ON COLUMN public.order_items.product_id IS 'Product identifier';

ALTER TABLE IF EXISTS public.orders OWNER to postgres;
ALTER TABLE IF EXISTS public.order_items OWNER to postgres;

