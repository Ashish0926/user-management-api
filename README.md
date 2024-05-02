# User Management API

### Setting up local MySQL database

The service relies on MySQL DB, so to run locally you need to setup and run an instance of that. The easiest
way to do that is through docker :

1. Run the following command to start the database:

```shell
docker-compose up -d mysql
```

This will start the db in background and allow your service to connect to it

### Setting up monitoring tools (Prometheus and Grafana)

1. The service uses Prometheus to collect application metrics, to set it up locally, run the following command from root
   directory of the project :

```shell
docker-compose up -d prometheus
```

2. To visualize these metrics, we're using Grafana dashboards, to set it up locally, run the following command from root
   directory of the project :
```shell
docker-compose up -d grafana
```

### Running locally

1. To run the service in local machine

```shell
./gradlew check
./gradlew build
./gradlew bootrun
```

### Urls

* crud-api:  http://localhost:8080
* swagger-ui: http://localhost:8080/swagger-ui/index.html
* prometheus-ui: http://localhost:9090
* grafana-ui: http://localhost:3000