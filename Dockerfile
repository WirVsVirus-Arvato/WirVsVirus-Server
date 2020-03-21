FROM openjdk:11-jdk-slim

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} corona-app.jar

ENTRYPOINT ["java","-jar","/corona-app.jar"]