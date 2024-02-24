CREATE TABLE IF NOT EXISTS beer
(
    id  uuid NOT NULL,
    brewery varchar(255) NOT NULL,
    "name"  varchar(255) NOT NULL,
    alcohol float NOT NULL,
    CONSTRAINT beer_pkey PRIMARY KEY (id)
);
