# ==========
# 1. Build Stage
# ==========
FROM gradle:8.12.0-jdk21 AS build
WORKDIR /app

# Copy project files
COPY . /app

# This will download dependencies and build the application
# The JAR file will be located in /app/build/libs
# RUN gradle bootJar
RUN gradle clean bootJar --stacktrace


# ==========
# 2. Run Stage
# ==========
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Pass environment variables if needed (optional, can also do via Docker Compose)
# ENV SPRING_DATASOURCE_URL=...
# ENV SPRING_DATASOURCE_USERNAME=...
# ENV SPRING_DATASOURCE_PASSWORD=...

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
