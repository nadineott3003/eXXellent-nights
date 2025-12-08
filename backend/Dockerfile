# === Build Stage ===================================
FROM maven:3.9-amazoncorretto-25 AS builder

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B clean package -DskipTests

# === Run Stage =====================================
FROM amazoncorretto:25-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
