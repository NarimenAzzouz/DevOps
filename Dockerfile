FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
EXPOSE 8082
COPY target/DevOps_Project-1.0.jar DevOps_Project-1.0.jar
ENTRYPOINT ["java","-jar","DevOps_Project-1.0.jar"]