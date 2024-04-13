# spring-crud-api

### Setting up local DB

The service relies on MySQL DB, so to run locally you need to setup and run an instance of that.  The easiest
way to do that is through docker:

1 Run the following command to start the database:
```shell
 docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=user_management -p 3306:3306 mysql:latest
```
This will start the db in background and allow your service to connect to it

### Running locally

1. To run the service in local machine
```shell
./gradlew check
./gradlew build
./gradlew bootrun
```

### Environments

* local:  http://localhost:8080
* swagger-ui: http://localhost:8080/swagger-ui/index.html