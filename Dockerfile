FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

COPY ./loanapi/target/*-jar-with-dependencies.jar.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
