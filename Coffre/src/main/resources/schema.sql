-- on suppr la table si elle existe
DROP TABLE IF EXISTS MONSTRECOFFRE;

-- creation de la table MONSTRECOFFRE
CREATE TABLE MONSTRECOFFRE (
    id INT NOT NULL PRIMARY KEY,
    nom VARCHAR(6) NOT NULL,
);