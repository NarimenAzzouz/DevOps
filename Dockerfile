FROM openjdk:11.0
VOLUME /tmp
ARG JAR_FILE
EXPOSE 8070
COPY target/DevOps_Project-1.0.jar DevOps_Project-1.0.jar
ENTRYPOINT ["java","-jar","DevOps_Project-1.0.jar"]