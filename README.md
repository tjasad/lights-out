# Lights out - studentska naloga

This project creates new players, 
problems and solutions for the game lights out. <br/>

The lights out game is represented as a matrix of 0 (light out)
and 1(light on). The objective in this implemetation of the
game is to get all the lights to be on.

##Create a new player
To create a new player you need to post the age and username
of the player.
##Create a new problem
To create a new problem you need to post players ID and 
the definition of the problem as a string.<br/>
####Definition format 
For the problem:<br/>
```
0 0 1 1 
0 1 1 1 
1 1 1 1 
1 1 1 1
```
The corresponding <b>definition</b> would be:<br/>
```
"001101111111111"
```

When the problem is submitted, it is checked for solveability.
##Creating a new solution
To create a new solution you need to post
the player ID, problem ID to which the solution
reefers to and the steps needed to solve the problem.
####Steps format 
A step is represented as a number from 1 to n x n, where
n is the size of the matrix.

Given a 3x3 matrix step 3 corresponds to the (1,3) element.

Matrix 3x3 with steps:
```
1 2 3
4 5 6
7 8 9
```

##Starting the project

###1. Database
Run:
```
docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine
```
In application.properties override quarkus.datasource.url to the
docker container url. Depending on if you use docker toolbox or 
docker on the local machine.
   
###2. Application

```
.\mvnw package
java -jar target/studentska-naloga-1.0.0-SNAPSHOT-runner.jar
```
<i>Note: starting the database first is necessary.</i>

Open: http://localhost:8080/swagger-ui