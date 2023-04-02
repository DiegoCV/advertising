
CREATE OR REPLACE FUNCTION set_default_state() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
	BEGIN
		update tbl_listing set state = 'draft' where id = NEW.id;
		return new;
	END;
$$;

CREATE TABLE IF NOT EXISTS tbl_dealer (
    id uuid NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    updated_at timestamp without time zone,
    name character varying(255),
    CONSTRAINT pk_dealer PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tbl_listing (
    id uuid NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone,
    vehicle character varying(255) NOT NULL,
    price double precision NOT NULL,
    state character varying(255) DEFAULT 'draft'::character varying,
    dealer_id uuid NOT NULL,
    CONSTRAINT pk_listing PRIMARY KEY (id),
    CONSTRAINT fk1_dealer FOREIGN KEY (dealer_id)
        REFERENCES public.tbl_dealer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS tbl_tier_limit (
    id uuid NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone,
    active boolean,
    tier integer NOT NULL,
    CONSTRAINT pk_tier_limit PRIMARY KEY (id)
);

DROP TRIGGER IF EXISTS set_default ON tbl_listing;

CREATE TRIGGER set_default AFTER INSERT ON tbl_listing FOR EACH ROW EXECUTE FUNCTION set_default_state();

