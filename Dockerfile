FROM openjdk:17
MAINTAINER alexandre queiroz
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]