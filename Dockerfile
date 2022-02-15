FROM openjdk:17-jdk-alpine

ENTRYPOINT ["java", "-jar", "/app.jar"]
