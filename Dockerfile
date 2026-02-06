FROM maven:3.9.6-eclipse-temurin-17-alpine

WORKDIR /app

# Copiar solo el JAR generado desde la etapa de compilación
COPY ./loanapi/target/*.jar app.jar

# Comando de ejecución con parámetros optimizados para contenedores
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
