/**
 * Clase MenuMedicos - Gestión del módulo de Médicos del Hospital.
 *
 * Esta clase proporciona un menú interactivo para la administración de médicos
 * del hospital, incluyendo operaciones de registro, consulta, modificación y eliminación.
 *
 * @author Antonio Manuel
 * @version 1.0
 * @since 21/04/2026
 */
package app.Menus;

/**
 * Clase que implementa el menú de gestión de médicos hospitalarios.
 * Extiende MenuBase para reutilizar la lógica común de menús.
 * Permite realizar operaciones CRUD sobre los registros de médicos.
 */
public class MenuMedicos extends MenuBase {

    /**
     * Muestra el menú de opciones para la gestión de médicos.
     * Las opciones disponibles son:
     * 1. Registrar Médico
     * 2. Consultar Médicos
     * 3. Modificar Médico
     * 4. Eliminar Médico
     * 5. Salir
     */
    @Override
    protected void mostrarMenu() {
        System.out.println("╔══════════════════════════╗");
        System.out.println("║       MENÚ MÉDICOS       ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║  1. Registrar Médico     ║");
        System.out.println("║  2. Consultar Médicos    ║");
        System.out.println("║  3. Modificar Médico     ║");
        System.out.println("║  4. Eliminar Médico      ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║  5. Salir                ║");
        System.out.println("╚══════════════════════════╝");
        System.out.print("   Elige una opción: ");
    }

    /**
     * Procesa la opción seleccionada por el usuario.
     *
     * @param opcion El número de opción seleccionada (1-5)
     * @return true para continuar el menú, false para salir
     */
    @Override
    protected boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> ejecutarOpcion("Registrar Médico");
            case 2 -> ejecutarOpcion("Consultar Médicos (filtrar por nombre, especialidad o DNI)");
            case 3 -> ejecutarOpcion("Modificar Médico");
            case 4 -> ejecutarOpcion("Eliminar Médico");
            case 5 -> {
                System.out.println("Saliendo...");
                return false;
            }
            default -> System.out.println("❌ Opción no válida. Por favor, elige una opción entre 1 y 5.");
        }
        return true;
    }

    /**
     * Retorna la opción de salida del menú de médicos.
     *
     * @return 5 (opción para salir)
     */
    @Override
    protected int getOpcionSalida() {
        return 5;
    }

    /**
     * Ejecuta la operación seleccionada por el usuario.
     * 
     * @param opcion La descripción de la operación a ejecutar
     */
    @Override
    protected void ejecutarOpcion(String opcion) {
        try {
            System.out.println("✓ Ejecutando: " + opcion);
        } catch (Exception e) {
            System.out.println("❌ Error al ejecutar la opción: " + e.getMessage());
        }
    }
}
