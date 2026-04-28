# 🏥 Hospital Management System

> Aplicación de escritorio en Java para la gestión integral de un hospital.  
> Permite administrar enfermos, médicos, auxiliares, plantas, habitaciones, hospitalizaciones y diagnósticos mediante operaciones CRUD completas por consola.

---

## 📋 Descripción del proyecto

**Hospital Management System** es una aplicación Java conectada a una base de datos SQLite que simula el sistema de información de un hospital. Implementa el patrón DAO (Data Access Object) para separar la lógica de negocio del acceso a datos, y gestiona la jerarquía de trabajadores (médicos y auxiliares) mediante herencia orientada a objetos.

La base de datos se crea y configura **automáticamente** al arrancar la aplicación: no es necesario ejecutar ningún script SQL ni instalar ningún servidor de base de datos.

### Funcionalidades principales

- Gestión completa de **enfermos** (alta, baja, modificación, búsqueda por nombre/DNI)
- Gestión de **médicos** y **auxiliares** con filtrado por especialidad y planta
- Administración de **plantas** y **habitaciones** con control de ocupación
- Registro de **hospitalizaciones** (ingreso y alta)
- Historial de **diagnósticos** por enfermo y médico

---

## ⚙️ Requisitos previos

Asegúrate de tener instalado lo siguiente antes de ejecutar el proyecto:

| Software | Versión mínima | Enlace de descarga |
|---|---|---|
| Java JDK | 22 | https://adoptium.net |
| Apache Maven | 3.8+ | https://maven.apache.org |
| Git | cualquier versión reciente | https://git-scm.com |

> **No se necesita instalar ningún servidor de base de datos.** SQLite está embebido como dependencia Maven y la base de datos (`hospital.db`) se genera automáticamente en el directorio raíz al primer arranque.

---

## 🗄️ Configuración de la base de datos

**No se requiere configuración manual.** Al ejecutar la aplicación por primera vez:

1. Se crea automáticamente el archivo `hospital.db` en el directorio raíz del proyecto.
2. Cada clase `DAOImpl` llama a `crearTabla()` al inicio, generando las tablas si no existen.
3. La aplicación queda lista para usar de inmediato.

Si quieres cargar datos de prueba iniciales, puedes consultar el script de referencia:

```
src/main/java/resources/data.sql
```

> ⚠️ Este script está escrito en sintaxis MySQL (utilizado durante el diseño). Para cargarlo en SQLite deberás adaptar la sintaxis o introducir los datos directamente desde el menú de la aplicación.

---

## 📥 Clonar el repositorio

```bash
git clone https://github.com/jfg96/hospital.git
cd hospital
```

---

## 🔨 Compilación y ejecución

### Opción 1 — Maven (recomendado)

```bash
# Compilar el proyecto
mvn compile

# Ejecutar la aplicación
mvn exec:java -Dexec.mainClass="app.Main"
```

### Opción 2 — Generar JAR ejecutable

```bash
mvn package
java -cp target/hospital-1.0-SNAPSHOT.jar:$(mvn dependency:build-classpath -q -Dmdep.outputFile=/dev/stdout) app.Main
```

### Opción 3 — Desde IntelliJ IDEA

1. Abre el proyecto como proyecto Maven (`File → Open → selecciona la carpeta hospital`).
2. Espera a que Maven descargue las dependencias automáticamente.
3. Localiza `src/main/java/app/Main.java` y pulsa el botón **▶ Run**.

---

## 📁 Estructura del proyecto

```
hospital/
├── pom.xml                          ← Configuración Maven (Java 22, sqlite-jdbc 3.45.1.0)
├── .gitignore                       ← Excluye target/, *.class, archivos de IDE locales
├── hospital.db                      ← Base de datos SQLite (generada automáticamente)
└── src/main/java/
    ├── app/
    │   ├── Main.java                ← Punto de entrada · inicializa BD y menú principal
    │   ├── Utilidades.java          ← Métodos de E/S de consola (leerInt, leerString…)
    │   └── Menus/
    │       ├── MenuEnfermos.java
    │       ├── MenuMedicos.java
    │       ├── MenuAuxiliares.java
    │       ├── MenuPlantas.java
    │       ├── MenuHabitaciones.java
    │       ├── MenuHospitalizaciones.java
    │       └── MenuDiagnosticos.java
    ├── conexion/
    │   └── ConexionBD.java          ← Patrón Singleton · gestiona la conexión JDBC
    ├── dao/
    │   ├── EnfermoDAO.java          ← Interfaz CRUD de cada entidad (×7)
    │   └── impl/
    │       └── EnfermoDAOImpl.java  ← Implementación SQLite de cada interfaz (×7)
    ├── modelo/
    │   ├── Trabajador.java          ← Clase abstracta padre
    │   ├── Medico.java              ← extends Trabajador
    │   ├── Auxiliar.java            ← extends Trabajador
    │   ├── Enfermo.java
    │   ├── Planta.java
    │   ├── Habitacion.java
    │   ├── Hospitalizacion.java
    │   └── Diagnostico.java
    └── resources/
        ├── Diagrama E-R.png         ← Diagrama conceptual del modelo de datos
        ├── DiagramaHospital.mwb     ← Modelo MySQL Workbench
        ├── schema.sql               ← Script DDL de referencia (sintaxis MySQL)
        └── data.sql                 ← Datos de prueba iniciales
```

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 22 | Lenguaje principal · lógica de negocio y modelo |
| SQLite | 3 (embebido) | Base de datos · archivo `hospital.db` |
| sqlite-jdbc (Xerial) | 3.45.1.0 | Driver JDBC para SQLite |
| Apache Maven | 3.8+ | Gestión de dependencias y compilación |
| IntelliJ IDEA | cualquier versión reciente | IDE de desarrollo |

---

## 👥 Equipo

| Nombre | Rol |
|---|---|
| Javier Fernández | Jefe de Proyecto |
| Héctor Rodríguez | Diseñador de Base de Datos |
| Carlos Fernández | Desarrollador Backend |
| Antonio Manuel Rodríguez | Desarrollador de Interfaz |
| Rafa Navarro | Responsable de Calidad y Documentación |

---

*I.E.S. Delgado Hernández · Bollullos Par del Condado · DAM 2025–2026*
