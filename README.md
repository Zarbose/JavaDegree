# JavaDegree

## Build/Run Java image only
```bash
docker build -t javadegree-java .
docker run --rm javadegree-java
```

## Build/Run all project
```bash
docker compose up -d
```

## Install local Kafka utils
Récupérez la dernière version de [Kafka](https://dlcdn.apache.org/kafka/).

```bash
KAFKA_VERSION="3.4.0"
wget "https://dlcdn.apache.org/kafka/${KAFKA_VERSION}/kafka_2.13-${KAFKA_VERSION}.tgz"
tar -zxf "kafka_2.13-${KAFKA_VERSION}.tgz" && mv "kafka_2.13-${KAFKA_VERSION}" "kafka-bin" && rm -rf "kafka_2.13-${KAFKA_VERSION}.tgz"
```

## Tests
Kafka : `kafka-bin/bin/kafka-topics.sh --create --topic test --bootstrap-server 127.0.0.1:9094`

## Grafana
Pour les dashboards : https://grafana.com/grafana/dashboards/
