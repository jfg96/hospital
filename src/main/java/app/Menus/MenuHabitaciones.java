/**
 * Clase MenuHabitaciones - Gestión del módulo de Habitaciones del Hospital.
 *
 * Esta clase proporciona un menú interactivo para la administración de habitaciones
 * del hospital, incluyendo operaciones de alta, consulta, modificación y eliminación.
 *
 * @author Antonio Manuel
 * @version 1.0
 * @since 21/04/2026
 */
package app.Menus;

/**
 * Clase que implementa el menú de gestión de habitaciones hospitalarias.
 * Extiende MenuBase para reutilizar la lógica común de menús.
 * Permite realizar operaciones CRUD sobre los registros de habitaciones.
 */
public class MenuHabitaciones extends MenuBase {

    /**
     * Muestra el menú de opciones para la gestión de habitaciones.
     * Las opciones disponibles son:
     * 1. Alta de Habitación
     * 2. Consultar Habitaciones
     * 3. Modificar Habitación
     * 4. Eliminar Habitación
     * 5. Salir
     */
    @Override
    protected void mostrarMenu() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║     MENÚ HABITACIONES      ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║  1. Alta de Habitación     ║");
        System.out.println("║  2. Consultar Habitaciones ║");
        System.out.println("║  3. Modificar Habitación   ║");
        System.out.println("║  4. Eliminar Habitación    ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║  5. Salir                  ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print("   Elige una opción: ");
    }

    @Override
    protected boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> ejecutarOpcion("Alta de Habitación");
            case 2 -> ejecutarOpcion("Consultar Habitaciones de una planta y su ocupación");
            case 3 -> ejecutarOpcion("Modificar Habitación");
            case 4 -> ejecutarOpcion("Eliminar Habitación");
            case 5 -> {
                System.out.println("Saliendo...");
                return false;
            }
            default -> System.out.println("❌ Opción no válida. Por favor, elige una opción entre 1 y 5.");
        }
        return true;
    }

    @Override
    protected int getOpcionSalida() {
        return 5;
    }

    @Override
    protected void ejecutarOpcion(String opcion) {
        try {
            System.out.println("✓ Ejecutando: " + opcion);
        } catch (Exception e) {
            System.out.println("❌ Error al ejecutar la opción: " + e.getMessage());
        }
    }
}
