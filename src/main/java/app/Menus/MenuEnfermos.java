package app.Menus;
import java.util.Scanner;

/**
 * Clase MenuEnfermos - Gestión del módulo de Enfermos/Pacientes del Hospital.
 *
 * Esta clase proporciona un menú interactivo para la administración de pacientes
 * (enfermos) del hospital, incluyendo registro, listado, actualización y eliminación.
 *
 * @author Antonio Manuel
 * @version 1.0
 * @since 21/04/2026
 * Clase que implementa el menú de gestión de enfermos/pacientes hospitalarios.
 * Permite realizar operaciones CRUD sobre los registros de pacientes
 * del sistema hospitalario.
 */
public class MenuEnfermos extends MenuBase {
    /** Scanner estático para leer entrada del usuario desde la consola */
    static Scanner input = new Scanner(System.in);

    /**
     * Muestra el menú de opciones para la gestión de enfermos.
     * Las opciones disponibles son:
     * 1. Registrar Enfermo
     * 2. Listar Enfermos
     * 3. Actualizar Enfermo
     * 4. Eliminar Enfermo
     * 5. Salir
     */
    protected void mostrarMenu() {
        System.out.println("╔══════════════════════════╗");
        System.out.println("║      MENÚ ENFERMOS       ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║  1. Registrar Enfermo    ║");
        System.out.println("║  2. Listar Enfermos      ║");
        System.out.println("║  3. Actualizar Enfermo   ║");
        System.out.println("║  4. Eliminar Enfermo     ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║  5. Salir                ║");
        System.out.println("╚══════════════════════════╝");
        System.out.print("   Elige una opción: ");
    }

    @Override
    protected void ejecutarOpcion(String opcion) {
    }
    /**
     * Ejecuta el menú de gestión de enfermos.
     *
     * Muestra un bucle interactivo que permite al usuario seleccionar
     * una opción y ejecutarla hasta que decida salir (opción 5).
     * Controla todas las operaciones relacionadas con los pacientes del hospital.
     */
    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = input.nextInt();
            switch (opcion) {
                case 1 -> ejecutarOpcion("Registrar Enfermo");
                case 2 -> ejecutarOpcion("Consultar listado de enfermos");
                case 3 -> ejecutarOpcion("Actualizar Enfermo");
                case 4 -> ejecutarOpcion("Eliminar Enfermo");
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 5);
    }

    /**
     * Ejecuta la operación seleccionada por el usuario.
     */



    @Override
    protected int getOpcionSalida() {
        return 0;
    }

    @Override
    protected boolean procesarOpcion(int opcion) {
        return false;
    }
}