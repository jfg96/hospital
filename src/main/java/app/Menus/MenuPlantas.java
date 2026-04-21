package app.Menus;
/**
 * Clase MenuPlantas - Gestión del módulo de Plantas del Hospital.
 *
 * Esta clase proporciona un menú interactivo para la administración de plantas
 * del hospital, incluyendo operaciones de alta, consulta, modificación y eliminación.
 *
 * @author Antonio Manuel
 * @version 1.0
 * @since 21/04/2026
 * Clase que implementa el menú de gestión de plantas hospitalarias.
 * Extiende MenuBase para reutilizar la lógica común de menús.
 * Permite realizar operaciones CRUD (Create, Read, Update, Delete) sobre
 * los registros de plantas del sistema.
 */
public class MenuPlantas extends MenuBase {

    /**
     * Muestra el menú de opciones para la gestión de plantas.
     * Las opciones disponibles son:
     * 1. Alta de Planta
     * 2. Consultar Plantas
     * 3. Modificar Planta
     * 4. Eliminar Planta
     * 5. Salir
     */
    @Override
    protected void mostrarMenu() {
        System.out.println("╔══════════════════════════╗");
        System.out.println("║       MENÚ PLANTAS       ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║  1. Alta de Planta       ║");
        System.out.println("║  2. Consultar Plantas    ║");
        System.out.println("║  3. Modificar Planta     ║");
        System.out.println("║  4. Eliminar Planta      ║");
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
            case 1 -> ejecutarOpcion("Alta de Planta");
            case 2 -> ejecutarOpcion("Consultar Plantas (filtrar por especialidad)");
            case 3 -> ejecutarOpcion("Modificar Planta");
            case 4 -> ejecutarOpcion("Eliminar Planta");
            case 5 -> {
                System.out.println("Saliendo...");
                return false;
            }
            default -> System.out.println("❌ Opción no válida. Por favor, elige una opción entre 1 y 5.");
        }
        return true;
    }

    /**
     * Retorna la opción de salida del menú de plantas.
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
     *               Puede ser: "Alta de Planta", "Consultar Plantas",
     *               "Modificar Planta" o "Eliminar Planta"
     */
    @Override
    protected void ejecutarOpcion(String opcion) {
        try {
            // Aquí irá la lógica específica de cada opción
            System.out.println("✓ Ejecutando: " + opcion);
        } catch (Exception e) {
            System.out.println("❌ Error al ejecutar la opción: " + e.getMessage());
        }
    }

    /**
     * Ejecuta el menú de gestión de plantas.
     * El método ejecutar() es heredado de MenuBase.
     */
    public static void main(String[] args) {
        new MenuPlantas().ejecutar();
    }
}
