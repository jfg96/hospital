# Paquete `conexion`

## Responsabilidad
Gestionar la conexión JDBC con la base de datos SQLite.

## Qué hay que hacer

- Crear la clase `ConexionDB.java` que abra la conexión con SQLite.
- Si el archivo `.db` no existe, SQLite lo crea automáticamente al conectar.
- Crear el método `getConexion()` que devuelva un objeto `Connection`.
- Crear el script SQL de creación de tablas y ejecutarlo al arrancar si las tablas no existen.

## Ejemplo de conexión
```java
Connection con = DriverManager.getConnection("jdbc:sqlite:hospital.db");
```

## Notas
- La cadena de conexión puede externalizarse en un archivo `config.properties` dentro de `resources/`.
- Gestionar bien el cierre de la conexión para evitar fugas de recursos.
