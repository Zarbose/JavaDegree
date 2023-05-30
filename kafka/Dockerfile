FROM bitnami/kafka:3.4

COPY . .

RUN kafka-topics.sh --create --topic Température_Celsius --bootstrap-server localhost:9092
RUN kafka-topics.sh --create --topic Température_Fahrenheit --bootstrap-server localhost:9092

EXPOSE 9092
