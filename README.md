# API Spring Orchestrator Provider Extranet

### Reference Documentation

For further reference, please consider the following sections:

* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web.reactive)

### Guides

The following guides illustrate how to use some features concretely:

- Project configuration in Intellij
- Docker Compose
- Swagger Documentation

### Project configuration

Configuration in Intellij:
- During the development process, it will be necessary to add the 'dev' profile and the environment variables ['DB_USER=root;DB_PASSWORD=pass']() in the Intellij IDE configuration, as shown in the following illustration.

![intellij-configuration-img.png](doc/intellij-configuration-img.png)

### Docker Compose

MongoDb application can be started from the 'docker-compose.yml' file or by opening a terminal in the root folder where the file is located. In this case, use the ['docker compose up']() command.

![docker-compose-img.png](doc/docker-compose-img.png)

### Swagger Documentation
- Swagger documentation can be accessed at: http://localhost:8080/swagger-ui/

![swagger-documentation-img.png](doc/swagger-documentation-img.png)