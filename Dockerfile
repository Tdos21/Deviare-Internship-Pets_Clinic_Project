FROM openjdk:17
WORKDIR /app
COPY target/Pets_Clinic01-0.0.1.jar /app/Pets_Clinic01-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/app/Pets_Clinic01-0.0.1.jar"]
