# docker build --build-arg JAR_FILE=target/*.jar -t myorg/myapp .
# Eviroment: PROFILE JDBC_UNAME JDBC_PASSWD

FROM openjdk:17-jdk-slim-buster

ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]