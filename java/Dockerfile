FROM amazoncorretto:17 AS builder
WORKDIR /app
COPY . .
RUN javac -d target src/main/java/org/javadegree/Main.java \
        src/main/java/org/javadegree/consumer/*.java \
        src/main/java/org/javadegree/producer/*.java \
        src/main/java/org/javadegree/producerconsumer/*.java

FROM amazoncorretto:17
WORKDIR /app
COPY --from=builder /app/target .
CMD ["java", "org.javadegree.Main"]