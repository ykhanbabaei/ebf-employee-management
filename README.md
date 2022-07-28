# yousef-khanbabaei


## Environment Variables
There are some variables which should be provided as environment variables:

> **EM_PORT** Server running port default value 8080

## Description
A simple web application for demo purpose. It shows a list of companies. For each company, there are a list of employees.
It is possible to add a new employee or update or delete existing one.
For persisting data H2 (In Memory) database used.
Frontend developed by react and backend with spring boot.
After running the application, ui is accessible via http://localhost:8080 in browser. 
For accessing APIs check the address http://localhost:8080/swagger-ui/index.html.

## Build and run
For building project, run maven command in project home directory:

    mvn clean package

and then run jar file in backend/target directory.

## Installation in Docker

For building JDK 11 and maven is required. In project root folder run the following command:

    mvn clean package

After successful build, docker image via Dockerfile should be created. In project directory following command should be executed:

    docker build -t employee-management:latest .

Then run docker:

    docker-compose -f docker-compose.yml up --detach

Before running docker, docker-compose.yml file should be modified. Port numbers for mongo and application, database name should be modified according to provided values for environment variables.
