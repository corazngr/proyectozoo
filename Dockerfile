FROM maven:3.8.6-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-slim
COPY --from=build /app/target/zoologico-*.jar zoologico.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "zoologico.jar"]
