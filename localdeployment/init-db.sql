CREATE TABLE IF NOT EXISTS beerBE
(
    id
    SERIAL
    PRIMARY
    KEY,
    brewery
    varchar(255),
    name
    varchar(255),
    alcohol
    float
    );

-- beerBE
INSERT INTO beerBE (brewery, name, alcohol)
VALUES ('Tegernseer', 'Tegernseer Spezial', 5.6);
INSERT INTO beerBE (brewery, name, alcohol)
VALUES ('Floetzinger', 'Flötzinger Hell', 5.2);
INSERT INTO beerBE (brewery, name, alcohol)
VALUES ('Augustiner', 'Edelstoff', 5.6);

