# parking

A simple application for parking management, created using Spring Boot and MySQL. Solves the GCP Global (Mexico) challenge.

### To run the project:
* Clone this repository, or download the code;
* In the root folder, run `mvn spring-boot:run`
* Then, access `http://localhost:8080/app/v1` in any browser

## Features
* Register an employee;
* Register a car;
* Set a type to a car (resident, non resident or official);
* Register car's entrances and exits

## Package organization
* ### entity: 
* contains the domain classes;
* ### config:
* contains the class that allows the use of the front-end engine;
* ### controller:
* contains the classes responsible for managing requests and solving endpoints
* ### repository:
* contains the classes that manage the database connections and queries;
* ### main/resources/templates
* contains the views used to provide a simple user interface

### Other files
* ### Dockerfile
* a small script to create a Docker image for this project;
* ### pom.xml
* contains some project configurations and the dependencies used.

## Development
This app was developed using the version 16 of OpenJDK, in the 20.04 and 21.10 versions
of Ubuntu OS, and containerized in a Docker image.

## Dificulties and possible improvements
The main difficulty faced in this project was the performance of unit tests, given the need to implement specific tests for the application.

Possible improvements can be suggested, such as the implementation of the service to obtain parking fees at the end of the month for all vehicles, in addition to improvements in the user interface.
