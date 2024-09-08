FROM amazoncorretto:11-alpine
MAINTAINER vinicius Pereira da Silva
ENV DATA_BASE_HOST_NAME=127.0.0.1
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]