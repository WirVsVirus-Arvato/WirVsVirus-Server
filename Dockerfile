FROM openjdk:11-jdk-slim

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} corona-app.jar

ENTRYPOINT ["java","-jar","/corona-app.jar"]