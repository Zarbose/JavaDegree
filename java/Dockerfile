# syntax=docker/dockerfile:1

FROM maven:3.9-amazoncorretto-17 AS builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM gcr.io/distroless/java17:latest
COPY --from=builder /usr/src/app/target/JavaDegree.jar /usr/app/JavaDegree.jar
ENTRYPOINT ["java", "-jar", "/usr/app/JavaDegree.jar"]