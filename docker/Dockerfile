# Use official OpenJDK 17 image as the base image
FROM eclipse-temurin:17-jdk-focal

# Set working directory in the container
WORKDIR /app

# Copy the JAR file from target directory
COPY target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8089

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]