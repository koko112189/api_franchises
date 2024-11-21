# *Sistema de Gestión de Franquicias*
Este proyecto es una API REST como prueba ténica para Accenture desarrollada en Spring Boot para gestionar franquicias, sucursales y productos. Permite realizar operaciones CRUD y consultas específicas, utilizando una arquitectura Clean Code y persistencia en MongoDB.

## *Características principales*
Agregar y eliminar franquicias, sucursales y productos.
Modificar el stock de productos.
Consultar el producto con mayor stock por sucursal en una franquicia específica.
Opciones avanzadas como la actualización de  franquicias, sucursales y productos.
Desarrollado con arquitectura limpia y empaquetado con Docker.

## *Requisitos previos:*
Java 17 o superior.
Maven para gestión de dependencias.
Docker y Docker Compose (opcional para ejecutar la aplicación en contenedores).
MongoDB instalado localmente o en un servicio en la nube.

## *Configuración del entorno*
1. Clonar el repositorio
Desde su línea de comandos
'''bash
$ git clone https://github.com/koko112189/api_franchises.git
$ cd api_franchises

3. Configurar MongoDB
 Servicio MongoDB con puerto disponible 27017 inicializado (local o en la nube).

Actualizar las credenciales en el archivo application.properties

3. Compilar y ejecutar la aplicación
Ejecutar en un entorno local
Desde su línea de comandos
'''bash
$ mvn clean install
$ mvn spring-boot:run

## *Ejecutar con Docker*
Construir la imagen:

Desde su línea de comandos
'''bash
$ docker build -t /api_franchises .


## *Ejecutar el contenedor*

Desde su línea de comandos
'''bash
$ docker run -p 8080:8080 -e SPRING_DATA_MONGODB_URI=mongodb://<usuario>:<contraseña>@<host>:<puerto>/<nombre_base_datos> api_franchises


Alternativamente, usar Docker Compose:
Actualizar el archivo docker-compose.yml con las credenciales de MongoDB.

Desde su línea de comandos
'''bash
$ docker-compose up --build

## **Módulos del proyecto**
-controller:
Contiene los controladores REST que exponen los endpoints de la API para interactuar con el sistema. Aquí se gestionan las solicitudes HTTP y las respuestas hacia los usuarios.
-entity
  -DTO:
  Define las clases que se utilizan para transferir datos entre la API y los clientes. Estas clases contienen solo los campos necesarios para la comunicación.
  -mapper:
  Implementa la lógica para convertir entre las clases de dominio (model) y los DTOs, asegurando que los datos se transformen correctamente entre los diferentes niveles de la aplicación.
  -model:
  Define las entidades principales del dominio que representan las franquicias, sucursales y productos. Estas clases reflejan la estructura de los datos almacenados en la base de datos.
  -repository:
  Contiene las interfaces que gestionan la persistencia de datos. Aquí se definen los métodos para interactuar con la base de datos utilizando Spring Data MongoDB.
-exception:
Define las clases personalizadas para manejar errores y excepciones en la aplicación. También incluye lógica para capturar y formatear las respuestas de error de manera consistente.
-service:

  -implementation:
  Implementa las interfaces de los servicios, conteniendo la lógica de negocio principal de la aplicación. Aquí se integran los repositorios y se procesan las reglas necesarias.
  -interfaces:
  Define las interfaces que representan la lógica de negocio de la aplicación. Estas interfaces sirven como contrato para la implementación de los servicios.
-utils
Incluye clases de utilidad o helpers que proporcionan funciones comunes para ser reutilizadas en diferentes partes del proyecto, como respuestas del servicio.


## *Documentación de la API*
La API incluye una colección de endpoints para gestionar franquicias, sucursales y productos. Puedes explorar los endpoints utilizando herramientas como Postman.

Endpoints principales

Franquicias
Obtener todos: GET /franchise/all
Obtener por id: GET /franchise/{franchiseId}
Crear: POST /franchise/create
Eliminar: DELETE /franchise/delete/{franchiseId}
Actualizar: PUT /franchise/update/{franchiseId}

Sucursales
Obtener todos: GET /branch/all
Obtener por id: GET /branch/{branchId}
Crear: POST /branch/create
Eliminar: DELETE /branch/delete/{branchId}/{franchiseId}
Actualizar: PUT /branch/update/{branchId}

Productos
Obtener todos: GET /product/all
Obtener por id: GET /product/{branchId}/{productId}
Crear: POST /product/create
Eliminar: DELETE /product/delete/{franchiseId}/{branchId}/{productId}
Actualizar: PUT /product/update/{franchiseId}/{branchId}/{productId}
Actualizar stock: PUT /product/{franchiseId}/{branchId}/{productoId}/stock
Obtener productos con mayor stock por franquicia de una sucursal específica: GET /product/{franchiseId}/max-stock

## *Autor*
John Edison Upegui Acevedo
