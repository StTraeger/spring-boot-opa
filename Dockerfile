FROM openjdk:21-jdk-alpine

ENTRYPOINT ["java", "-jar", "/app.jar"]
