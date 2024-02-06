-- on suppr la table si elle existe
DROP TABLE IF EXISTS INVENTAIRE;

-- creation de la table INVENTAIRE
CREATE TABLE INVENTAIRE (
    type VARCHAR(63) PRIMARY KEY,
    quantity BIGINT NOT NULL
);

-- on suppr la table si elle existe
DROP TABLE IF EXISTS EQUIPE;

-- creation de la table EQUIPE
CREATE TABLE EQUIPE (
    id BIGINT PRIMARY KEY,
    nom VARCHAR(63) NOT NULL
);