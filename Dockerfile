FROM amazoncorretto:11-alpine
MAINTAINER vinicius Pereira da Silva
ENV DATA_BASE_HOST_NAME=127.0.0.1
WORKDIR /app
EXPOSE 8080
ARG JAR_FILE=target/app.jar
COPY ${JAR_FILE} app.jar
CMD ["java", "-jar", "app.jar"]