FROM maven:3.9.8-eclipse-temurin-21-alpine AS BUILD

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install -DskipTests

FROM mosipid/openjdk-21-jre:latest
COPY --from=build /app/target/organizas.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]