-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE SEQUENCE user_seq start 1 increment 1;

CREATE TABLE IF NOT EXISTS "public".users
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    bonus bigint NOT NULL,
    city character varying(255) COLLATE pg_catalog."default",
    created timestamp without time zone,
    date_of_birth date,
    delivery_address character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    phone_number character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    surname character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    basket_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "public".users
    OWNER to postgres;

    -- Table: public.product

    -- DROP TABLE IF EXISTS public.product;

CREATE SEQUENCE product_seq start 1 increment 1;

    CREATE TABLE IF NOT EXISTS "public".product
    (
        id character varying(255) COLLATE pg_catalog."default" NOT NULL,
        color character varying(255) COLLATE pg_catalog."default",
        count bigint NOT NULL,
        created timestamp without time zone,
        description character varying(255) COLLATE pg_catalog."default",
        img_url character varying(255) COLLATE pg_catalog."default",
        manufacturer character varying(255) COLLATE pg_catalog."default",
        pet_type character varying(255) COLLATE pg_catalog."default",
        price bigint NOT NULL,
        product_type character varying(255) COLLATE pg_catalog."default",
        title character varying(255) COLLATE pg_catalog."default",
        weight double precision NOT NULL,
        CONSTRAINT product_pkey PRIMARY KEY (id),
        CONSTRAINT product_count_check CHECK (count >= 0),
        CONSTRAINT product_price_check CHECK (price >= 10),
        CONSTRAINT product_weight_check CHECK (weight >= 0::double precision)
    )

    TABLESPACE pg_default;

    ALTER TABLE IF EXISTS "public".product
        OWNER to postgres;

-- Table: public.basket

-- DROP TABLE IF EXISTS public.basket;

CREATE SEQUENCE basket_seq start 1 increment 1;

CREATE TABLE IF NOT EXISTS "public".basket
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT basket_pkey PRIMARY KEY (id),
    CONSTRAINT fk810a8gq30miyp6j1eub97qm6k FOREIGN KEY (user_id)
        REFERENCES "public".users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "public".basket
    OWNER to postgres;

    -- Table: public.products

    -- DROP TABLE IF EXISTS public.products;

    CREATE TABLE IF NOT EXISTS "public".products
    (
        basket_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
        product_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT fkclexbljvejjlq1dl4kyc7vhbe FOREIGN KEY (product_id)
            REFERENCES "public".product (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
        CONSTRAINT fkh3uog71vgp5qi97gqtifm6ceg FOREIGN KEY (basket_id)
            REFERENCES "public".basket (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

    ALTER TABLE IF EXISTS "public".products
        OWNER to postgres;

-- Table: public.invoice

-- DROP TABLE IF EXISTS public.invoice;

CREATE SEQUENCE invoice_seq start 1 increment 1;

CREATE TABLE IF NOT EXISTS "public".invoice
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    address character varying(255) COLLATE pg_catalog."default",
    created timestamp without time zone,
    email character varying(255) COLLATE pg_catalog."default",
    phone_number character varying(255) COLLATE pg_catalog."default",
    recipient character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    sum bigint NOT NULL,
    user_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT invoice_pkey PRIMARY KEY (id),
    CONSTRAINT fkc8jotskr93810vgn75qlbusw2 FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "public".invoice
    OWNER to postgres;

-- Table: public.invoice_product

-- DROP TABLE IF EXISTS public.invoice_product;

CREATE TABLE IF NOT EXISTS "public".invoice_product
(
    inv_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    pr_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT fker8tdofktupaksj8qsv54kdau FOREIGN KEY (inv_id)
        REFERENCES "public".invoice (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkr1hargnongqkwuqt0rgdju8ap FOREIGN KEY (pr_id)
        REFERENCES "public".product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "public".invoice_product
    OWNER to postgres;

-- Table: public.pet

-- DROP TABLE IF EXISTS public.pet;

CREATE SEQUENCE pet_seq start 1 increment 1;

CREATE TABLE IF NOT EXISTS "public".pet
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    pet_type character varying(255) COLLATE pg_catalog."default",
    user_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT pet_pkey PRIMARY KEY (id),
    CONSTRAINT fkhg3enfwsufxjb6enqetxx2ku0 FOREIGN KEY (user_id)
        REFERENCES "public".users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "public".pet
    OWNER to postgres;
