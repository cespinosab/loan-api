# loan-api
Esta es una API REST para gestionar solicitudes de préstamos personales.

## Instrucciones para instalación y ejecución

Para ejecutarla es necesario tener instalado:

* **Java 17** o superior
* **Maven 3.8.7**
* **Docker** y **Docker Compose**

### Levantar el entorno de ejecución

Toda la configuración de los contenedores está en el fichero docker-compose.yml.
Está compuesto por el contenedor de bases de datos PosgreSQL y la aplicación en Spring Boot.

Para levantar el entorno es necesario:

1. Generar el jar de la aplicación
```
mvn clean package
```

2. Levantar el entorno de ejecución

```
docker compose up --build
```

### Parar entorno de ejecución:

```
docker compose down -v
```

Existen dos formas de probar la API: usando Swagger o vía curl.

### 1. Swagger

La API cuenta con un Swagger al que se puede acceder a través la URL http://localhost:8080/swagger-ui.html.
Para ello, solo es necesario abrir un navegador web e introducir la URL indicada.

Aparecerá toda la documentación de la API y para hacer uso de cualquiera de ellos:
   1. Seleccionar el endpoint deseado
   2. Pulsar el botón que aparece a la derecha "Try out".
   3. Rellenar los campos si fuese necesario.
   4. Pulsar el botón Execute y obtendremos la respuesta de la API. 


### 2. Vía curl

Para hacer uso la API vía curl, hay que abrir el terminal y ejecutar uno de los siguientes comandos:

#### GET - Consultar el listado solicitudes de préstamos personales

```
curl -X GET http://localhost:8080/api/personalLoanApplications

```

#### GET - Consultar una solicitud de préstamos personal sabiendo su ID

```
curl -X GET http://localhost:8080/api/personalLoanApplications/1

```

#### POST - Dar de alta una nueva solicitud de préstamo personal

```
curl -X POST http://localhost:8080/api/personalLoanApplications \
   -H 'Content-type: application/json' \
   -d '{
         "firstName":"Cliente",
         "lastName":"Apellido",
         "personalId":"91234567-B",
         "amount":3000.0,
         "badge":"EUR"
       }'

```

#### PUT - Actualizar una solicitud existente
```
curl -X PUT http://localhost:8080/api/personalLoanApplications/3 \
   -H 'Content-type: application/json' \
   -d '{
        "firstName": "Cliente",
        "lastName": "Apellido",
        "personalId": "91234567-B",
        "amount": 3000,
        "badge": "EUR",
        "status": "APROBADA"
       }'
```

## Descripción de la Arquitectura

Se ha realizado usando microservicios, arquitectura hexagonal y DDD para garantizar que el sistema cumpla 
con las reglas del negocio, sea escalable, mantenible y sea fácil de entender entre los equipos técnicos y el negocio. 

1. Estructura de Capas:
La implementación se organiza en paquetes para proteger la lógica de negocio de la tecnología (Spring/Docker/Postgres):

   - domain: Contiene la lógica de negocio. Están las entidades (model) y las excepciones. Para evitar duplicidades, 
              en este modelo se han mapeado las entities de JPA.

   - application: Casos de uso. Se encuentran los servicios, los mappeadores y los DTO de entrada/salida de la API.
     
   - infrastructure: Implementación técnica. Aquí se define la API con sus controladores y los repositorios JPA.


## Mejoras

- Añadir paginación en la obtención del listado de solicitudes.
- Modificar los test IT para que realmente sean test de integración, actualmente con test E2E, para que sean IT hay mockear los endpoints
 para que no se ejecute el verdadero método. 
- Añadir un nuevo módulo testAT que use Cucumber para tener pruebas E2E completas usando Cucumber, 
- Hacer que le servicio esté securizado usando HTTPS
- Incluir validaciones relacionadas con la entrada de campos
- Mejorar la gestión de errores cuando no hay datos, no se puede actualizar una solicitud
- Añadir gestión de usuarios


