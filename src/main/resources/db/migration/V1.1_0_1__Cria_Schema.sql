-- ADICIONAL

CREATE TABLE adicional (
    id bigint NOT NULL,
    atualizado_em timestamp without time zone,
    criado_em timestamp without time zone,
    ativo boolean DEFAULT false,
    nome character varying(255) NOT NULL,
    preco numeric(19,2) DEFAULT 0.00 NOT NULL,
    tempo_preparo bigint DEFAULT 0,
    categoria_id bigint NOT NULL
);

ALTER TABLE ONLY adicional
    ADD CONSTRAINT adicional_pkey PRIMARY KEY (id);

CREATE SEQUENCE adicional_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE adicional_id_seq OWNED BY adicional.id;

ALTER TABLE ONLY adicional ALTER COLUMN id SET DEFAULT nextval('adicional_id_seq'::regclass);

SELECT pg_catalog.setval('adicional_id_seq', 1, false);

--- ADICIONAL CATEGORIA

CREATE TABLE adicional_categoria (
    id bigint NOT NULL,
    atualizado_em timestamp without time zone,
    criado_em timestamp without time zone,
    ativo boolean DEFAULT false,
    maximo integer DEFAULT 100,
    minimo integer DEFAULT 0,
    nome character varying(255) NOT NULL,
    produto_id bigint NOT NULL
);

ALTER TABLE ONLY adicional_categoria
    ADD CONSTRAINT adicional_categoria_pkey PRIMARY KEY (id);

CREATE SEQUENCE adicional_categoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE adicional_categoria_id_seq OWNED BY adicional_categoria.id;

ALTER TABLE ONLY adicional_categoria ALTER COLUMN id SET DEFAULT nextval('adicional_categoria_id_seq'::regclass);

SELECT pg_catalog.setval('adicional_categoria_id_seq', 1, false);

--- ITEM

CREATE TABLE item (
    id bigint NOT NULL,
    atualizado_em timestamp without time zone,
    criado_em timestamp without time zone,
    quantidade bigint DEFAULT 0 NOT NULL,
    pedido_id bigint NOT NULL,
    produto_id bigint NOT NULL
);

ALTER TABLE ONLY item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);

CREATE SEQUENCE item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE item_id_seq OWNED BY item.id;

ALTER TABLE ONLY item ALTER COLUMN id SET DEFAULT nextval('item_id_seq'::regclass);

SELECT pg_catalog.setval('item_id_seq', 1, false);

-- ITEM ADICIONAL

CREATE TABLE item_adicionais (
    item_id bigint NOT NULL,
    adicionais_id bigint NOT NULL
);


ALTER TABLE ONLY item_adicionais
    ADD CONSTRAINT item_adicionais_pkey PRIMARY KEY (item_id, adicionais_id);

-- PEDIDO

CREATE TABLE pedido (
    id bigint NOT NULL,
    atualizado_em timestamp without time zone,
    criado_em timestamp without time zone
);

ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (id);

CREATE SEQUENCE pedido_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE pedido_id_seq OWNED BY pedido.id;

ALTER TABLE ONLY pedido ALTER COLUMN id SET DEFAULT nextval('pedido_id_seq'::regclass);

SELECT pg_catalog.setval('pedido_id_seq', 1, false);

--- PRODUTO

CREATE TABLE produto (
    id bigint NOT NULL,
    atualizado_em timestamp without time zone,
    criado_em timestamp without time zone,
    ativo boolean DEFAULT false,
    nome character varying(255) NOT NULL,
    preco numeric(19,2) DEFAULT 0.00 NOT NULL,
    tempo_preparo bigint DEFAULT 0
);

ALTER TABLE ONLY produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);

CREATE SEQUENCE produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE produto_id_seq OWNED BY produto.id;

ALTER TABLE ONLY produto ALTER COLUMN id SET DEFAULT nextval('produto_id_seq'::regclass);

SELECT pg_catalog.setval('produto_id_seq', 1, false);


ALTER TABLE ONLY adicional
    ADD CONSTRAINT fk_adicional_categoria FOREIGN KEY (categoria_id) REFERENCES adicional_categoria(id);


ALTER TABLE ONLY adicional_categoria
    ADD CONSTRAINT fk_produto_adicional_categoria FOREIGN KEY (produto_id) REFERENCES produto(id);

ALTER TABLE ONLY item
    ADD CONSTRAINT fk_pedido_item FOREIGN KEY (pedido_id) REFERENCES pedido(id);

ALTER TABLE ONLY item_adicionais
    ADD CONSTRAINT fk_item_adicionais_adicional FOREIGN KEY (adicionais_id) REFERENCES adicional(id);

ALTER TABLE ONLY item_adicionais
    ADD CONSTRAINT fk_item_adicionais_item FOREIGN KEY (item_id) REFERENCES item(id);

ALTER TABLE ONLY item
    ADD CONSTRAINT fk_item_produto FOREIGN KEY (produto_id) REFERENCES produto(id);