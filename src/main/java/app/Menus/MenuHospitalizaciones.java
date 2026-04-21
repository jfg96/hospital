package app.Menus;

import java.util.Scanner;

/**
 * Clase MenuHospitalizaciones - Gestión del módulo de Hospitalizaciones.
 * 
 * Esta clase proporciona un menú interactivo para la administración de
 * hospitalizaciones de pacientes, incluyendo registro, consulta, alta y eliminación.
 * 
 * @author Antonio Manuel
 * @version 1.0
 * @since 21/04/2026
 * Clase que implementa el menú de gestión de hospitalizaciones.
 * Permite realizar operaciones de registro de hospitalizaciones,
 * consulta de registros activos/históricos y registro de altas.
 */
public class MenuHospitalizaciones extends MenuBase {
    /** Scanner estático para leer entrada del usuario desde la consola */
    static Scanner input = new Scanner(System.in);

    /**
     * Muestra el menú de opciones para la gestión de hospitalizaciones.
     * Las opciones disponibles son:
     * 1. Registrar Hospitalización
     * 2. Consultar Hospitalización
     * 3. Registrar Alta
     * 4. Eliminar Registro
     * 5. Salir
     */
   protected void mostrarMenu()  {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║   MENÚ HOSPITALIZACIONES     ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  1. Registrar Hospitalización║");
        System.out.println("║  2. Consultar Hospitalizacion║");
        System.out.println("║  3. Registrar Alta           ║");
        System.out.println("║  4. Eliminar Registro        ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  5. Salir                    ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("   Elige una opción: ");
    }

    @Override
    protected void ejecutarOpcion(String opcion) {

    }

    /**
     * Ejecuta el menú de gestión de hospitalizaciones.
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
                case 1 -> ejecutarOpcion("Registrar Hospitalización");
                case 2 -> ejecutarOpcion("Consultar Hospitalizaciones (activas/históricas, por enfermo o habitación)");
                case 3 -> ejecutarOpcion("Registrar Alta (actualizar fecha de alta)");
                case 4 -> ejecutarOpcion("Eliminar/Cancelar Registro de Hospitalización");
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
