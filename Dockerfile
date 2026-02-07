FROM amazoncorretto:17-alpine-jdk

EXPOSE 8080

WORKDIR /app

COPY ./loanapi/src/main/resources/application.properties application.properties
COPY ./loanapi/target/*-jar-with-dependencies.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
