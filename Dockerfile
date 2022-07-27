FROM openjdk:17-alpine
ARG JAR_FILE=backend/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]