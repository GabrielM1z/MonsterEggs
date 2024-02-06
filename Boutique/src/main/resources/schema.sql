-- on suppr la table si elle existe
DROP TABLE IF EXISTS BOUTIQUE;

-- creation du sequence pour l'attribution des id
CREATE SEQUENCE "BOUTIQUE_SEQ"
    MINVALUE 1
    MAXVALUE 99999
    INCREMENT BY 1
    START WITH 1
    NOCACHE
    NOCYCLE;

-- creation de la table BOUTIQUE
CREATE TABLE BOUTIQUE (
     nom VARCHAR(64) NOT NULL PRIMARY KEY,
     price INT NOT NULL,
     quantity INT NOT NULL,
);