FROM openjdk:17
EXPOSE 8087
ADD http://localhost:8081/repository/demoapp-release/tn/esprit/DevOps_Project/1.0/DevOps_Project-1.0.jar DevOps_Project-1.0.jar
ENTRYPOINT ["java", "-jar", "DevOps_Project-1.0.jar"]

#FROM maven as build
#WORKDIR /app
#COPY . .
#RUN mvn install


#FROM openjdk:17
#WORKDIR /app
#COPY --from=build /app/target/Devop.jar /app/
#EXPOSE 9090
#CMD ["java","-jar","Devops.jar"]