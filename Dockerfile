# Use official OpenJDK 17 slim image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY build/libs/spring-crud-api-0.0.1-SNAPSHOT.jar /app/spring-crud-api-0.0.1-SNAPSHOT.jar
COPY build/libs/spring-crud-api-0.0.1-SNAPSHOT-plain.jar /app/spring-crud-api-0.0.1-SNAPSHOT-plain.jar

# Expose the port that your application runs on
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "spring-crud-api-0.0.1-SNAPSHOT.jar"]