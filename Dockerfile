# Use official OpenJDK image as a build environment
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies (cache this step)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the jar
COPY src ./src
RUN mvn clean package -DskipTests

# Use a lightweight Java runtime for the final image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/kqz-api.jar ./kqz-api.jar

# Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "kqz-api.jar"]
