FROM amazoncorretto:17

COPY . .

RUN javac -d target src/main/java/org/javadegree/Main.java \
        src/main/java/org/javadegree/consumer/*.java \
		src/main/java/org/javadegree/producer/*.java \
        src/main/java/org/javadegree/producerconsumer/*.java
        
CMD ["java", "-cp", "target", "org.javadegree.Main"] 
