# Todo App with Spring Boot, GraphQL, and Angular 7
This is a simple Todo app, the main features are to create `Todo`, list all `Todo`s and change the status.

#

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You should have Nodejs and Npm installed on you machine.

You only need to clone the project.
`
  git clone https://github.com/rgrbev/grapql-todo-app.git
`

### Development
A step by step series of examples that tell you how to get a development env running.

#### Spring Boot
To start the backend application in the dev profile, simply run:
```
  gradlew bootRun
```
#### Angular
First you need to go to the ng-client folder, after that you must to install the required packages with: 
```
  npm install
```
and then you can simply start the front end application with:
```
  npm start
```
After this you can open  the application on:
```
  localhost:4200
```
Also you have documentation for the GraphQL (`localhost:8080/graphiql`) queries that you can make from the Angular application or directly from the documentation.

