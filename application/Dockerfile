FROM openjdk:17-alpine

ARG JAR_FILE=/target/application-*.jar
ADD ${JAR_FILE} application.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/application.jar"]