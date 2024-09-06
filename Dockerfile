FROM openjdk:17-oracle
COPY target/*.jar tarefa.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","tarefa.jar"]
