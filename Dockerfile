FROM maven as build 
WORKDIR /app 
COPY . . 
RUN mvn install


FROM openjdk:11
WORKDIR /app
COPY --from=build /app/target/DevOps_Project-1.0.jar /app/
EXPOSE 9090
CMD ["java","-jar","DevOps_Project-1.0.jar"]


