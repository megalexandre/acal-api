./gradlew build -x test   
./gradlew bootjar
docker build -t alexandrequeiroz/api:1.0.1 .
docker push alexandrequeiroz/api:1.0.1 