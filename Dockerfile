FROM openjdk:11-jre-slim
COPY target/categories-service-app.jar categories-service-app.jar
ENTRYPOINT ["java", "-jar", "categories-service-app.jar"]
