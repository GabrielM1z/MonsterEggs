# compilation des differents projets en .jar
cd API_GATEWAY
mvn clean package
cd ../Boutique
mvn clean package
cd ../Cave
mvn clean package
cd ../Coffre
mvn clean package
cd ../Combat
mvn clean package
cd ../Joueur
mvn clean package
cd ../Log
mvn clean package
cd ../Monstre
mvn clean package

# on up tout les docker (arriere plan)
docker-compose up -d --build