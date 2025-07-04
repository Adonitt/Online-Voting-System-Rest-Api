# Base image me Java 17
FROM eclipse-temurin:17-jre-alpine

# Vendos folderin e punës brenda containerit
WORKDIR /app

# Kopjo pom.xml dhe kodin burimor për të bërë build më shpejt (cache)
COPY pom.xml .
COPY src ./src

# Ndërto jar-in me Maven Wrapper (mvnw)
RUN ./mvnw clean package -DskipTests

# Kopjo jar-in e krijuar nga target folder
COPY target/kqz-0.0.1-SNAPSHOT.jar app.jar

# Ekspozoni portin ku Spring Boot do të dëgjojë (zakonisht 8080)
EXPOSE 8080

# Start komandë për të nisur aplikacionin
ENTRYPOINT ["java", "-jar", "app.jar"]
