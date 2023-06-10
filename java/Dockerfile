# syntax=docker/dockerfile:1

FROM maven:3.9-amazoncorretto-17 AS builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM amazoncorretto:17-al2023
# If you don't have to use JMX, use gcr.io/distroless/java17:latest
COPY --from=builder /usr/src/app/target/JavaDegree.jar /usr/src/JavaDegree.jar
RUN curl -L https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.18.0/jmx_prometheus_javaagent-0.18.0.jar \
    -o /usr/src/jmx_prometheus_javaagent.jar
COPY config.yaml /usr/src/config/config.yaml

ARG EXPOSE_METRICS_PORT=12345
ENV EXPOSE_METRICS_PORT=$EXPOSE_METRICS_PORT
ENTRYPOINT java -javaagent:/usr/src/jmx_prometheus_javaagent.jar=${EXPOSE_METRICS_PORT}:/usr/src/config/config.yaml -jar /usr/src/JavaDegree.jar