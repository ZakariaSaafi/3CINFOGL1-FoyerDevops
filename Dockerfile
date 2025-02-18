# Use official OpenJDK 17 image as the base image
FROM eclipse-temurin:17-jdk-focal

# Set working directory in the container
WORKDIR /app

# Copy the JAR file from target directory
COPY target/Foyer-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]