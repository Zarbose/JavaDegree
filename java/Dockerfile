# syntax=docker/dockerfile:1

FROM maven:3.9-amazoncorretto-17 AS builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM amazoncorretto:17-al2023

RUN curl -L https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.18.0/jmx_prometheus_javaagent-0.18.0.jar \
    -o /usr/src/jmx_prometheus_javaagent.jar
COPY config.yaml /usr/src/config/config.yaml

COPY --from=builder /usr/src/app/target/JavaDegree.jar /usr/src/JavaDegree.jar
ENTRYPOINT ["java", "-javaagent:/usr/src/jmx_prometheus_javaagent.jar=12345:/usr/src/config/config.yaml", "-jar", "/usr/src/JavaDegree.jar"]