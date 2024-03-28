FROM openjdk:17
# Install Docker Compose
RUN apt-get update && \
    apt-get install -y docker-compose
EXPOSE 8087
ADD http://localhost:8081/tn/esprit/DevOps_Project/1.0/DevOps_Project-1.0.jar DevOps_Project-1.0.jar
#ADD target/DevOps_Project-1.0.jar DevOps_Project-1.0.jar
ENTRYPOINT ["java", "-jar", "/DevOps_Project-1.0.jar"]