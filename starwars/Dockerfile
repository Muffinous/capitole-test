# Start from a lightweight OpenJDK image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file (make sure to build it before!)
COPY build/libs/*.jar app.jar

# Expose port 6969
EXPOSE 6969

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=6969"]
