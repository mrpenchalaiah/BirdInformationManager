# BirdInformationManager
This will manager all the birds information 

# Description
This project is completely build on spring boot microservice. Currently it supports only 4 operation.
1) Storing bird informaiton
2) Retriving bird informaiton
3) Deleting bird informaiton
4) Get all the bird information

# Requirements to run the projects
Maven 3 and above
Mongo instance
Need to configure the mongo instance details in application.properties file.
spring.data.mongodb.database=birddata
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
   
Need to run below command in comman line on root folder 
  mvn spring-boot:run
  
Note: It has embedded tomcat which runs on 8080 port.

#Running test cases

mvn clean test
