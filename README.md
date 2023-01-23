<p align="center">
<img src="https://github.com/DanyloBorysenko/Taxi-service/blob/main/imageCopy.jpg" />
</p>
<h1 align="center">Taxi-service</h1>

## Project description:
```
A simple web-application that supports authentication, registration, read, update and delete operations.
```

## :zap:Features
+ registration like a driver;
+ authentication like a driver;
+ create/update/delete a manufacturer;
+ create/update/delete a car;
+ create/update/delete a driver;
+ display all manufacturers;
+ display all cars;
+ display all drivers;
+ display your cars;
+ add driver to a car;

## :computer:Technologies used
+ Mysql 8.0.31
+ Java Servlet API 4.0.1
+ JSTL 1.2
+ log4j
+ JUnit 5

## :eyes:How it looks like
[My-taxi-service](http://taxiservice-env.eba-gzayjkfc.us-east-1.elasticbeanstalk.com/login)

## :wrench:Installation
1. Install Tomcat 9.0.50. If you decide to install version 10 and above, you should use a different dependency for servlets and JSTL as well.
2. Make sure you have Mysql installed.
3. Check out a project from a remote host (clone).
4. In Edit configuration menu -> Add new configuration -> Tomcat server Local -> Configure -> choose directory with your Tomcat installed -> press fix button in the lower right corner -> choose war exploded -> ok
5. Use init_db.sql file for creating your local database.
6. Open ConnectionUtil class from src\main\java\taxi\util and write information about your database.
```java
private static final String URL = "jdbc:mysql://YOUR DATABASE URL/taxi";
    private static final String USERNAME = "YOUR USERNAME";
    private static final String PASSWORD = "YOUR PASSWORD";
```
7. Press debug button
