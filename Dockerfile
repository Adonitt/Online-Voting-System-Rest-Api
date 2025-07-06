# 🏗️ Stage 1: Build the app
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# 🚀 Stage 2: Run the app
FROM eclipse-temurin:21-jre

WORKDIR /app

# copy built jar
COPY --from=build /app/target/kqz-0.0.1-SNAPSHOT.jar ./kqz-api.jar

# expose port (optional, Render handles it)
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "kqz-api.jar"]
