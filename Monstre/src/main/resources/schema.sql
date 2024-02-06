-- on suppr la table si elle existe
DROP TABLE IF EXISTS MONSTRE;

-- creation du sequence pour l'attribution des id
CREATE SEQUENCE "MONSTRE_SEQ"
    MINVALUE 1
    MAXVALUE 99999
    INCREMENT BY 1
    START WITH 1
    NOCACHE
    NOCYCLE;

-- creation de la table MONSTRE
CREATE TABLE MONSTRE (
      id INT NOT NULL DEFAULT nextval('MONSTRE_SEQ') PRIMARY KEY,
      nom VARCHAR(6) NOT NULL,
      level INT NOT NULL,
      attaque INT NOT NULL,
      xp INT NOT NULL
);