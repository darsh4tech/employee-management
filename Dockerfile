FROM openjdk:latest

COPY target/emp-*.jar employee-service.jar

EXPOSE 8090

ENTRYPOINT [ "java", "-jar", "employee-service.jar" ]
