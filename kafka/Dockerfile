FROM bitnami/kafka:3.4

RUN curl -L https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.18.0/jmx_prometheus_javaagent-0.18.0.jar \
    -o /bitnami/kafka/jmx_prometheus_javaagent.jar
COPY config.yaml /bitnami/kafka/config/config.yaml

ARG EXPOSE_METRICS_PORT=12345
ENV KAFKA_OPTS=-javaagent:/bitnami/kafka/jmx_prometheus_javaagent.jar=${EXPOSE_METRICS_PORT}:/bitnami/kafka/config/config.yaml