# Paquete `modelo`

## Responsabilidad
Clases Java que representan las entidades del sistema (POJOs). No contienen lógica de negocio ni SQL.

## Qué hay que hacer

Crear una clase por cada entidad:

- `Enfermo.java` — código, nombre, dirección, DNI, fecha de nacimiento, teléfono, edad
- `Medico.java` — nombre, dirección, DNI, sueldo, teléfono, especialidad
- `Auxiliar.java` — nombre, dirección, DNI, sueldo, teléfono, planta asignada
- `Planta.java` — número, cantidad de habitaciones, especialidad
- `Habitacion.java` — número, camas, observaciones, planta
- `Hospitalizacion.java` — enfermo, habitación, fecha de ingreso, fecha de alta
- `Diagnostico.java` — código, fecha, informe, enfermo, médico

Cada clase debe tener:
- Atributos privados
- Constructor con todos los atributos
- Getters y setters
- `toString()` para mostrar los datos por pantalla

## Notas
- `Medico` y `Auxiliar` comparten atributos comunes — se puede crear una clase padre `Trabajador.java` de la que ambos hereden.
