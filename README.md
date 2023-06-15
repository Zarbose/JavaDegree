# JavaDegree

Projet de Master 1 Informatique, UE Programmation événementielle.  
Le but est de réaliser, via Docker, avec Java, Prometheus et Grafana, une application multi-theadé qui récupère (fictivement) des températures. Les températures en celsius doivent être envoyées dans Kafka dans un topic T1, esuite, l'application doit récupérer ces températures et les envoyer dans un topic T2 en fahrenheit. Les températures doivent pouvoir etre visualisées sur un graphique, et insérées dans une TSDB au préalable.

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

## Install local Kafka utils

Récupérez la dernière version de [Kafka](https://dlcdn.apache.org/kafka/).

```bash
KAFKA_VERSION="3.4.0"
wget "https://dlcdn.apache.org/kafka/${KAFKA_VERSION}/kafka_2.13-${KAFKA_VERSION}.tgz"
tar -zxf "kafka_2.13-${KAFKA_VERSION}.tgz" && mv "kafka_2.13-${KAFKA_VERSION}" "kafka-bin" && rm -rf "kafka_2.13-${KAFKA_VERSION}.tgz"
```

## Tests

### Grafana

Variation manuelle des valeurs de temperature : 

```bash
docker exec -it java bash -c 'TEMP_CELSIUS=10; TEMP_FAHR=100; for i in {0..6000}; do echo -e "temperature_celsius $TEMP_CELSIUS\ntemperature_fahrenheit $TEMP_FAHR\n" > /tmp/temperatures.prom; sleep 0.01; done'
```

Cette commande permet d'envoyer les valeurs "TEMP_CELSIUS" et "TEMP_FARH" choisies dans le fichier "/tmp/temperatures.prom" afin de faire varier les courbes de températures sur Grafana.

## Grafana

Pour les dashboards : <https://grafana.com/grafana/dashboards/>.
