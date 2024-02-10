CREATE TABLE IF NOT EXISTS beer
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
    float,
    amount
    int
    );

-- beer
INSERT INTO beer (brewery, name, alcohol, amount)
VALUES ('Brauhaus Tegernsee', 'Tegernseer Spezial', 5.6, 25000);
INSERT INTO beer (brewery, name, alcohol, amount)
VALUES ('Flötzinger', 'Flötzinger Hell', 5.2, 19500);
INSERT INTO beer (brewery, name, alcohol, amount)
VALUES ('Augustiner', 'Edelstoff', 5.6, 26300);

