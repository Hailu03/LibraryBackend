# Stage 1: Build the Spring Boot application with Maven
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the Docker image
WORKDIR /app

# Copy the Maven configuration files
COPY pom.xml .
COPY src ./src

# Build the application with Maven
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight container with the JAR file
FROM openjdk:17.0.1-jdk-slim

# Set the working directory in the Docker image
WORKDIR /app

# Copy the JAR file from the build stage to the Docker image
COPY --from=build /app/target/*.jar app.jar

# Expose the port that the Spring Boot application uses (default is 8080)
EXPOSE 8080

# Define the command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]