FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/proyectoZoo-3.4.4.jar
COPY ${JAR_FILE} appZoo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "appZoo.jar"]