FROM openjdk:21-jdk
COPY target/categories-service-app.jar categories-service-app.jar
ENTRYPOINT ["java", "-jar", "categories-service-app.jar"]
