DROP TABLE IF EXISTS INVENTAIRE;

CREATE TABLE INVENTAIRE (
    nameObject VARCHAR(63) PRIMARY KEY,
    quantity BIGINT NOT NULL
);

DROP TABLE IF EXISTS EQUIPE;

CREATE TABLE EQUIPE (
    idMonstre BIGINT PRIMARY KEY,
    nameMonstre VARCHAR(63) NOT NULL
);