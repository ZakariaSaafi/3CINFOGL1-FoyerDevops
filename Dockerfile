# Stage 1: Build the application
FROM eclipse-temurin:17-jdk-alpine as build

WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B
COPY . .
RUN ./mvnw package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=build /app/target/Foyer-0.0.1-RELEASE.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
