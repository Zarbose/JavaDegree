FROM bitnami/kafka:3.4

RUN cd /bitnami/kafka/ && curl -LO https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.18.0/jmx_prometheus_javaagent-0.18.0.jar

COPY ./config.yaml /bitnami/kafka/config/
