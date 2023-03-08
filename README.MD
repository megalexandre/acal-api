./gradlew build -x test   
./gradlew bootjar
docker build -t alexandrequeiroz/acalapi:0.0.2 .
docker push alexandrequeiroz/acalapi:0.0.2 