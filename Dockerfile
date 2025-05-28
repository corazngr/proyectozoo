FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/zoologico-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} zoologico.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "appZoo.jar"]
