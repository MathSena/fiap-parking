# Usando uma imagem base de Java 11
FROM openjdk:11-jre-slim

# Criando um diretório para nosso aplicativo
WORKDIR /app

# Copiando o arquivo JAR do nosso microserviço para o diretório do contêiner
COPY ./target/user-ms-*.jar /app/user-ms.jar

# Expondo a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar nosso microserviço
CMD ["java", "-jar", "/app/user-ms.jar"]
