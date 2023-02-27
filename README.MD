./gradlew bootjar
docker build -t alexandrequeiroz/acalapp:0.0.1 .
docker push alexandrequeiroz/acalapi:0.0.1 