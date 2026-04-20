# Paquete `dao`

## Responsabilidad
Acceso a la base de datos. Contiene todas las operaciones CRUD para cada entidad.

## Qué hay que hacer

Crear una clase DAO por cada entidad del sistema:

- `EnfermoDAO.java`
- `MedicoDAO.java`
- `AuxiliarDAO.java`
- `PlantaDAO.java`
- `HabitacionDAO.java`
- `HospitalizacionDAO.java`
- `DiagnosticoDAO.java`

Cada clase debe implementar los métodos:
- `insertar(...)` — INSERT
- `listar()` — SELECT todos
- `buscarPorId(...)` — SELECT por clave primaria
- `actualizar(...)` — UPDATE
- `eliminar(...)` — DELETE (con comprobaciones de integridad donde aplique)

## Notas
- Usar `PreparedStatement` siempre, nunca concatenar SQL con datos del usuario.
- Obtener la conexión desde el paquete `conexion`.
- Los métodos devuelven objetos del paquete `modelo`.
