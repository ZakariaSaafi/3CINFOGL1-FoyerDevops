# Use an official OpenJDK image as the base image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and the pom.xml file into the container
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download and cache the dependencies (this helps speed up subsequent builds)
RUN ./mvnw dependency:go-offline -B

# Copy the entire project into the container
COPY . .

# Package the application
RUN ./mvnw package -DskipTests

# Expose the default Spring Boot port
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "target/Foyer-0.0.1-RELEASE.jar"]
