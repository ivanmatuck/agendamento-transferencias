# Etapa 1: Build da aplicação
FROM maven:3.8.5-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM openjdk:11-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8060
ENTRYPOINT ["java", "-jar", "app.jar"]
