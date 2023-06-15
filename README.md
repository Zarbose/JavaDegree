# JavaDegree

Projet de Master 1 Informatique, UE Programmation Événementielle.  
Le but est de réaliser, via Docker, Java, Prometheus et Grafana, une application multi-theadé qui récupère (fictivement) des températures. Ces températures, par défaut, en degré celsius doivent être envoyées dans Kafka dans un topic dénommé "Température_Celsius". Ensuite, l'application doit récupérer ces températures et les envoyer dans un autre topic "Température_Fahrenheit", comme son nom l'indique en degré fahrenheit. Les températures doivent pouvoir être visualisées sur un ou plusieurs graphiques, et insérées dans une TSDB au préalable.

## Build/Run Java image only

```bash
docker build -t javadegree-java .
docker run --rm javadegree-java
```

## Build/Run all project

```bash
# En construisant l'image en local
docker compose  up  -d

# En utilisant l'image distante
docker compose  --file docker-compose-remote.yml  up  -d
```

On peut rebuild les images si les fichiers on été modifiés en local avec l'option --build. Pour le remote il faudra faire un compose pull, et avoir delete les containers au préalable avec docker down.

## Tests

### Kafka

#### Install local Kafka utils

Afin de tester le fonctionnement de Kafka, il est nécessaire d'installer les utilitaires de Kafka :

Récupérez la dernière version de [Kafka](https://dlcdn.apache.org/kafka/).

```bash
KAFKA_VERSION="3.4.0"
wget "https://dlcdn.apache.org/kafka/${KAFKA_VERSION}/kafka_2.13-${KAFKA_VERSION}.tgz"
tar -zxf "kafka_2.13-${KAFKA_VERSION}.tgz" && mv "kafka_2.13-${KAFKA_VERSION}" "kafka-bin" && rm -rf "kafka_2.13-${KAFKA_VERSION}.tgz"
```

#### Creation d'un topic

`kafka-bin/bin/kafka-topics.sh --create --topic test --bootstrap-server 127.0.0.1:9094`

#### Produire des messages

`kafka-bin/bin/kafka-console-producer.sh --bootstrap-server 127.0.0.1:9094 --topic test`

#### Consommer des messages

`kafka-bin/bin/kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9094 --topic test --from-beginning`

### Grafana

Variation manuelle des valeurs de température : 

```bash
docker exec -it java bash -c 'TEMP_CELSIUS=10; TEMP_FAHR=100; for i in {0..6000}; do echo -e "temperature_celsius $TEMP_CELSIUS\ntemperature_fahrenheit $TEMP_FAHR\n" > /tmp/temperatures.prom; sleep 0.01; done'
```

Cette commande permet d'envoyer les valeurs "TEMP_CELSIUS" et "TEMP_FARH" choisies dans le fichier "/tmp/temperatures.prom" afin de faire varier les courbes de températures sur Grafana.

## Grafana

Pour les dashboards : <https://grafana.com/grafana/dashboards/>.

## Java

Documentation : <https://zarbose.github.io/JavaDegree/>
