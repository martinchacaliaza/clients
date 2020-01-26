FROM openjdk:8
VOLUME /tmp
EXPOSE 8001
ADD ./target/microservicios.mongodb.banco.participantes-0.0.1-SNAPSHOT.jar clientes.jar
ENTRYPOINT ["java","-jar","/clientes.jar"]