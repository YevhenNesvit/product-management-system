FROM maven:3.8.2-jdk-11 AS build

WORKDIR /opt/app

COPY . ./

RUN mvn clean package

FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/goit-java-dev-hw-8-1.0-SNAPSHOT.jar

WORKDIR /opt/app

EXPOSE 8080

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]