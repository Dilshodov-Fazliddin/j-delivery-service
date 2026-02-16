FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar j-delivery-service-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "j-delivery-service-0.0.1.jar"]
