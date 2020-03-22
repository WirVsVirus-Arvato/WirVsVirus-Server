FROM openjdk:11-jdk-slim

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /corona-app.jar

COPY scripts/wait-for-postgres /wait-for-postgres
RUN chmod +x /wait-for-postgres

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/corona-app.jar"]