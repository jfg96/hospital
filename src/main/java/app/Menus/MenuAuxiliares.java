/**
 * Clase MenuAuxiliares - Gestión del módulo de Auxiliares de Enfermería.
 *
 * Esta clase proporciona un menú interactivo para la administración de auxiliares
 * de enfermería del hospital, incluyendo registro, consulta, modificación y eliminación.
 *
 * @author Antonio Manuel
 * @version 1.0
 * @since 21/04/2026
 */
package app.Menus;

/**
 * Clase que implementa el menú de gestión de auxiliares de enfermería.
 * Extiende MenuBase para reutilizar la lógica común de menús.
 */
public class MenuAuxiliares extends MenuBase {

    /**
     * Muestra el menú de opciones para la gestión de auxiliares.
     * Las opciones disponibles son:
     * 1. Registrar Auxiliar
     * 2. Consultar Auxiliares
     * 3. Modificar Auxiliar
     * 4. Eliminar Auxiliar
     * 5. Salir
     */
    @Override
    protected void mostrarMenu() {
        System.out.println("╔══════════════════════════╗");
        System.out.println("║     MENÚ AUXILIARES      ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║  1. Registrar Auxiliar   ║");
        System.out.println("║  2. Consultar Auxiliares ║");
        System.out.println("║  3. Modificar Auxiliar   ║");
        System.out.println("║  4. Eliminar Auxiliar    ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║  5. Salir                ║");
        System.out.println("╚══════════════════════════╝");
        System.out.print("   Elige una opción: ");
    }

    @Override
    protected void ejecutarOpcion(String opcion) {

    }

    /**
     * Ejecuta el menú de gestión de auxiliares.
     *
     * Muestra un bucle interactivo que permite al usuario seleccionar
     * una opción y ejecutarla hasta que decida salir (opción 5).
     */
    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = input.nextInt();
            switch (opcion) {
                case 1 -> ejecutarOpcion("Registrar Auxiliar");
                case 2 -> ejecutarOpcion("Consultar Auxiliares (filtrar por nombre, DNI o planta)");
                case 3 -> ejecutarOpcion("Modificar Auxiliar");
                case 4 -> ejecutarOpcion("Eliminar Auxiliar");
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 5);
    }
    @Override
    protected int getOpcionSalida() {
        return 0;
    }

    @Override
    protected boolean procesarOpcion(int opcion) {
        return false;
    }
}
