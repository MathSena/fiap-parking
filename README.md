# FIAPparking

O sistema de parquímetro modernizado atende ao crescente fluxo de estacionamento urbano, introduzindo funcionalidades avançadas para a gestão de registros de motoristas e veículos, monitoramento do tempo de estacionamento, múltiplas opções de pagamento e emissão automática de recibos.

**Tarifário:** R$ 10,00 por hora de estacionamento.

## Tecnologias e Ferramentas:

- **Spring Initializr**: Facilitador para projetos Spring Boot ([Link](https://start.spring.io/))
- **Spring Boot**: Framework para aplicações web ([Link](https://spring.io/projects/spring-boot))
- **Maven**: Gerenciador de dependências ([Link](https://mvnrepository.com/))
- **Lombok**: Biblioteca para redução de código boilerplate
- **Postman**: Ambiente de desenvolvimento API ([Link](https://www.postman.com/))
- **Swagger (OpenAPI)**: Interface para documentação e testes de APIs
- **MySQL**: Sistema de banco de dados relacional ([Link](https://www.mysql.com/))
- **Docker**: Criação de ambientes isolados via containers ([Link](https://www.docker.com/))
- **Docker Compose**: Orquestração de containers Docker ([Link](https://docs.docker.com/compose/))

## Instalação e Configuração:

Pré-requisitos para execução do projeto:

- Java 17: [Download](https://www.oracle.com/br/java/technologies/javase-jdk17-downloads.html)
- Maven: [Download](https://maven.apache.org/download.cgi)
- MySQL: [Download](https://www.mysql.com/downloads/)
- Docker: [Download](https://docs.docker.com/get-docker/)
- Docker Compose: [Download](https://docs.docker.com/compose/install/)
- Postman: [Download](https://www.postman.com/downloads/)

## APIs Desenvolvidas

- API de Motoristas
- API de Veículos
- API de Estacionamento
- API de Formas de Pagamento


## Documentação e Testes com Swagger:

Para visualizar e testar as APIs, acesse a interface do Swagger:

- http://localhost:8080/swagger-ui.html

## Execução com Docker:

Para iniciar os serviços, utilize o seguinte comando no terminal:

```shell
docker-compose up -d
```

Para parar os serviços, utilize o seguinte comando no terminal:

```shell
docker-compose down
```

## Execução com Maven:

Para executar o projeto, utilize o seguinte comando no terminal:

```shell   
mvn spring-boot:run
```

## Execução com IDE:

Para executar o projeto, basta executar a classe `FiapParkingApplication.java` em sua IDE.

## Testes com Postman:

Para testar as APIs, importe o arquivo `FiapParking.postman_collection.json` no Postman.

## Banco de Dados:

Para acessar o banco de dados, utilize o seguinte comando no terminal:

```shell
CREATE DATABASE parquimetro_db;
```

## Diagrama de Classes:

![Diagrama de Classes](

## Diagrama de Entidade e Relacionamento:

![Diagrama de Entidade e Relacionamento](

## Diagrama de Sequência:

![Diagrama de Sequência](

## Diagrama de Componentes:

![Diagrama de Componentes](

## Diagrama de Implantação:

![Diagrama de Implantação](

## Autores:


- **Diogo Valente**: RM 348497
- **Matheus Sena**: RM 430025
- **Willian Kaminski**: RM 430025

## Licença:

Este projeto está licenciado sob a licença MIT - consulte o arquivo [LICENSE.md](LICENSE.md) para obter detalhes.

## Referências:

- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Web](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-developing-web-applications)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-jpa-and-spring-data)
- [Spring Data JDBC](https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/)
- [Spring Data JDBC - Reference Documentation](https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/)




