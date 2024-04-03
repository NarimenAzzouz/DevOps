FROM openjdk:11
ADD target/DevOps_Project-1.1-SNAPSHOT.jar devops.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "devops.jar"]