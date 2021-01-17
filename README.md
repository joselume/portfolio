# Portfolio

This project is for managing user portafolios and includes an integration with twitter for showing the lastes 5 tweets of each user. The application is composed of three views:

* View for searching a portfolio record given its ID
* View for showing the portfolio information
* View for updating the portfolio information.

#### Endpoints

The application include two endpoints:
* `[GET] /portfolio/{id}` - Get a portfolio given its ID
* `[PUT] /portfolio/{id}` - Updates a portfolio

#### Software Prerequisites
 - JDK 11
 - JAVA_HOME set to JDK 11
 - Port 8082 available or modify it in application.properties

#### Steps for running the application
 - Download the `portfolio` project from this repository
 - Compile it with the following maven command:
```sh
$ mvn spring-boot:run
```

#### Technologies Used
The following is a list of the technologies used for developing the application:

* `[Java 11]` - Language for develping the backend application
* `[Spring Boot]` - Framework for enhancing the development
* `[spring-social-twitter]` - Spring Utility for integration with twitter
* `[spring JPA]` - Spring utility for accesing the data base
* `[SpringBootTest]` - Spring utility for integration testing
* `[Maven]` - Dependency managment tool
* `[Html]` - Language for structuring the UI content
* `[Javascript]` - Language for managing the event in the UI
* `[Bootstrap]` - Framework for styling the UI 

#### Total time in hours:
The time spent writing this application: 12 hours.
