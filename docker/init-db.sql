CREATE TABLE IF NOT EXISTS salary
(
    id
    SERIAL
    PRIMARY
    KEY,
    username
    varchar
(
    255
),
    amount int
    );

CREATE TABLE IF NOT EXISTS document
(
    id
    SERIAL
    PRIMARY
    KEY,
    content
    varchar
(
    255
),
    owner varchar
(
    255
)
    );

-- salaries
INSERT INTO salary (username, amount)
VALUES ('alice', 1000);
INSERT INTO salary (username, amount)
VALUES ('bob', 800);
INSERT INTO salary (username, amount)
VALUES ('carol', 600);
INSERT INTO salary (username, amount)
VALUES ('david', 500);
INSERT INTO salary (username, amount)
VALUES ('john', 900);

-- documents
INSERT INTO document (content, owner)
VALUES ('Alice Document 1', 'alice');
INSERT INTO document (content, owner)
VALUES ('Bob Document 1', 'bob');
INSERT INTO document (content, owner)
VALUES ('Bob Document 2', 'bob');
INSERT INTO document (content, owner)
VALUES ('David Document 1', 'david');
INSERT INTO document (content, owner)
VALUES ('David Document 2', 'david');
INSERT INTO document (content, owner)
VALUES ('Carol Document 1', 'carol');
INSERT INTO document (content, owner)
VALUES ('John Document 1', 'john');
