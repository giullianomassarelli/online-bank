# Online Bank API
Project developed to register banking transcations from two type of users

### Pre-requisites
What do you need to install to run the project?
* [Docker](https://www.docker.com/)
* [Docker-Compose](https://docs.docker.com/compose/)
* [Gradle](https://gradle.org/)
* [JDK-17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [JRE-17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

### Installation
git clone git@github.com:giullianomassarelli/online-bank.git

### Docker Compose Build and Run with dev profile
```
sh docker-compose-dev.sh
```
### 
To access Swagger documentation:
```
http://localhost:8080/swagger-ui.html#/
```
### Technologies used

* [Gradle](https://gradle.org/) - From mobile apps to microservices, from small businesses to large enterprises, Gradle helps teams build, automate, and deliver better software, faster.
* [Spring Boot Web Starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web) - Starter for building web, including RESTful apps, using Spring MVC. Uses Tomcat as the default embedded container
* [Spring Boot Test Starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test) - Starter for testing Spring Boot applications with libraries including JUnit, Hamcrest and Mockito.
* [Lombok](https://projectlombok.org/) - Project Lombok is a java library that automatically plugs into your editor and builds tools, spicing up your java. Never write another getter or equals method again, with an annotation your class has a full-featured constructor, automate your registry variables, and more.
* [Swagger](https://swagger.io/) - Simplify API development for users, teams, and enterprises with the professional, open source Swagger toolset.
* [Commons Email](https://commons.apache.org) - Commons Email aims to provide a API for sending email. It is built on top of the Java Mail API, which it aims to simplify.
* [Power Mock](https://powermock.github.io/) - PowerMock is a framework that extends other mock libraries like EasyMock with more powerful features. PowerMock uses a custom class loader and bytecode handling to allow mocking static methods, constructors, final classes and methods, private methods, removing static initializers, and much more.
* [Spring Cloud Starter OpenFeign](https://cloud.spring.io/spring-cloud-openfeign/reference/html/) - This project provides OpenFeign integrations for Spring Boot applications through automatic configuration and binding to the Spring Environment and other expressions of the Spring programming model.
* [Model Mapper](http://modelmapper.org/) - Applications often consist of similar but different object models, where the data in two models may be similar, but the structure and concerns of the models are different. Object mapping makes it easy to convert from one model to another, allowing separate models to remain segregated.