# DizVoter App
Test task for https://dizplai.com/ company.

This project contains two parts: Spring backend application and React frontend.

## Preparation

Before you start you need to have next tools installed:
1. Java 17 JDK 
2. Maven build tool
3. Docker

## Building the project

1. Open your terminal and go to project root folder.
2. Execute `mvn clean install` command and wait for it to finish successfully.
This will build application, execute all tests and create Docker images.

## How to start application locally

To start application simply run `cd component-tests && mvn docker:start` command - this will start database and application containers in Docker environment.
After that our application is available on http://localhost:8080

Now you are ready to create your first polls :) To do that execute **Create poll** HTTP request.
To view created polls on UI go to http://localhost:8080/ui/polls page.

All example requests for available HTTP endpoints are present in attached Postman collection: [DizVoter.postman_collection.json](DizVoter.postman_collection.json)

## Comments

Obviously this is not a production-ready system and lacks many things like test coverage and some user identity functionality.
Currently, user is just a hard-coded random value on backend and there is no validation or verification of user identity. Also, there are no restrictions to view voting results without making a vote.
