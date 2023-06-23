FROM openjdk:17
MAINTAINER megalexandre@gmail.com
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]