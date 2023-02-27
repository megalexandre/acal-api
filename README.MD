./gradlew bootjar
docker build -t alexandrequeiroz/acal-api:0.0.1 .
docker push alexandrequeiroz/acal-api:0.0.1 