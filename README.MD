./gradlew build -x test   
./gradlew bootjar
docker build -t alexandrequeiroz/api:0.0.2 .
docker push alexandrequeiroz/api:0.0.2 