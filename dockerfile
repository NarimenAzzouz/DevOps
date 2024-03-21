FROM openjdk:11
ADD target/DevOps_Project-1.0.jar project
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "project.jar"]