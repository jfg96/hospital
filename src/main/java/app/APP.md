# Paquete `app`

## Responsabilidad
Punto de entrada de la aplicación e interfaz de usuario. Es lo que el usuario ve y con lo que interactúa.

## Qué hay que hacer

- Crear la clase `Main.java` con el método `main` que arranca la aplicación.
- Implementar los menús de navegación (menú principal, submenús por entidad).
- Recoger la entrada del usuario por consola y llamar a los métodos del paquete `dao`.
- Mostrar los resultados de las consultas por pantalla.

## Entidades a cubrir
Menú para cada una de estas entidades: Enfermos, Médicos, Auxiliares, Plantas, Habitaciones, Hospitalizaciones y Diagnósticos.

## Notas
- No debe contener lógica de negocio ni SQL, solo llamadas a `dao` y presentación de datos.
- Cada entidad puede tener su propia clase de menú para no sobrecargar `Main`.
