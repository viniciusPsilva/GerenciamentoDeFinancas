FROM openjdk:11
MAINTAINER vinicius Pereira da Silva
ENV DATA_BASE_HOST_NAME=DB_GerenciamentoDeFinancas
RUN mkdir /home/app
COPY target/GerenciamentoDeFinancas-0.0.1-SNAPSHOT.jar /home/app
WORKDIR /home/app