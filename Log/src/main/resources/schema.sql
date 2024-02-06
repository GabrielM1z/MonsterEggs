-- on suppr la table si elle existe
DROP TABLE IF EXISTS LOG;

-- creation du sequence pour l'attribution des id
CREATE SEQUENCE "LOG_SEQ"
    MINVALUE 1
    MAXVALUE 999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE
    NOCYCLE;

-- creation de la table BOUTIQUE
CREATE TABLE LOG (
    id INT NOT NULL DEFAULT nextval('LOG_SEQ') PRIMARY KEY,
    description VARCHAR(1000) NOT NULL
);