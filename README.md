# Task Manager API

## Descripción

Task Manager API es una aplicación desarrollada con **Spring Boot** y **WebFlux**, que permite gestionar tareas de
manera reactiva. Utiliza **Spring Data R2DBC** para la persistencia de datos en una base de datos **H2 en memoria**,
facilitando su prueba y desarrollo.

## Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3.4.4**
- **Spring WebFlux**
- **Spring Data R2DBC**
- **H2 Database**
- **Lombok**
- **Gradle**
- **JUnit 5**

## Requisitos previos

Para ejecutar este proyecto necesitas:

- **Java 21** instalado
- **Gradle** (opcional, ya que se usa el wrapper `./gradlew`)
- **IntelliJ IDEA**, VS Code u otro IDE con soporte para Spring Boot

## Instalación y Ejecución

1. Clonar este repositorio:
   ```sh
   git clone https://github.com/tu-usuario/task-manager.git
   cd task-manager
   ```

2. Construir el proyecto:
   ```sh
   ./gradlew build
   ```

3. Ejecutar la aplicación:
   ```sh
   ./gradlew bootRun
   ```

4. Acceder a la API en `http://localhost:8080`

## Configuración de la Base de Datos

El proyecto usa **H2 en memoria** con R2DBC. La configuración está en `application.yml`:

```yaml
spring:
  r2dbc:
    url: r2dbc:h2:mem:///testdb;DB_CLOSE_DELAY=-1;MODE=MYSQL
    username: sa
    password:
  h2:
    console:
      enabled: true
      path: /h2-console
  sql:
    init:
      mode: always
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

Para acceder a la **H2 Console**, visita:

```
http://localhost:8080/h2-console
```

Usa la URL JDBC: `jdbc:h2:mem:testdb` con usuario `sa` y sin contraseña.

## Endpoints disponibles

### Crear una nueva tarea

```http
POST /tasks
Content-Type: application/json

{
  "title": "Estudiar Spring WebFlux",
  "description": "Revisar documentación y hacer ejemplos",
  "dueDate": "2025-04-01T12:00:00"
}
```

### Obtener todas las tareas

```http
GET /tasks
```

### Obtener una tarea por ID

```http
GET /tasks/{id}
```

### Actualizar una tarea

```http
PUT /tasks/{id}
Content-Type: application/json

{
  "title": "Actualizar tarea",
  "description": "Revisar progreso",
  "status": "IN_PROGRESS",
  "dueDate": "2025-04-02T18:00:00"
}
```

### Eliminar una tarea

```http
DELETE /tasks/{id}
```

## Estructura del Proyecto

```
/src/main/java/com/example/taskmanager
├── application   # Lógica de negocio y servicios
├── domain        # Entidades y modelos
├── infrastructure # Configuraciones y acceso a datos
└── presentation  # Controladores y endpoints
```

## Pruebas

Ejecuta las pruebas con:

```sh
./gradlew test
```

Las pruebas están en `src/test/java/com/example/taskmanager/` e incluyen pruebas unitarias y de integración.

## Mejoras futuras

- Implementación de seguridad con **Spring Security**
- Autenticación y autorización con **JWT**
- Integración con **Docker y Kubernetes**
- Soporte para **PostgreSQL** en entornos productivos

## Contribuir

Si deseas mejorar este proyecto, sigue estos pasos:

1. Haz un fork del repositorio
2. Crea una rama nueva (`git checkout -b feature-nueva`)
3. Realiza tus cambios y confirma (`git commit -m "Nueva funcionalidad"`)
4. Sube los cambios a tu fork (`git push origin feature-nueva`)
5. Abre un Pull Request

## Licencia

Este proyecto está bajo la licencia **MIT**. Puedes usarlo libremente para fines educativos y comerciales.

---
Este README proporciona una documentación clara y completa para el proyecto, asegurando que cualquier desarrollador
pueda instalarlo, ejecutarlo y contribuir sin problemas.

