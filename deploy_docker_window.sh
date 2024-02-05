# compilation des differents projets en .jar
cd API_GATEWAY
./mvnw clean package
cd ../Boutique
./mvnw clean package
cd ../Cave
./mvnw clean package
cd ../Coffre
./mvnw clean package
cd ../Combat
./mvnw clean package
cd ../Joueur
./mvnw clean package
cd ../Log
./mvnw clean package
cd ../Monstre
./mvnw clean package

# on up tout les docker (arriere plan)
docker-compose up -d --build