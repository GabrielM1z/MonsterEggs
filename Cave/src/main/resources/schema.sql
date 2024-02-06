-- on suppr la table si elle existe
DROP TABLE IF EXISTS INCUBATEUR;

-- creation du sequence pour l'attribution des id
CREATE SEQUENCE "INCUBATEUR_SEQ"
    MINVALUE 1
    MAXVALUE 99999
    INCREMENT BY 1
    START WITH 1
    NOCACHE
    NOCYCLE;

-- creation de la table INCUBATEUR
CREATE TABLE INCUBATEUR (
     id INT NOT NULL DEFAULT nextval('INCUBATEUR_SEQ') PRIMARY KEY,
     oeuf BOOLEAN NOT NULL,
     dateEclosion DATETIME NOT NULL
);