package app.Menus;

/**
 *
 * @Author: Antonio Manuel Rodriguez Palenzuela
 * @Description: Clase que implementa el menú de gestión de diagnósticos médicos.
 * Extiende MenuBase para reutilizar la lógica común de menús.
 * Permite realizar operaciones CRUD sobre los registros de diagnósticos.
 * Las opciones disponibles son:
 * 1. Registrar Diagnóstico
 * 2. Consultar Historial (filtrar por médico o fecha)
 * 3. Modificar Diagnóstico
 * 4. Eliminar Diagnóstico
 * 5. Salir
 * @Version: 1.0
 * @Since: 21/04/2026
 */

public class MenuDiagnosticos extends MenuBase {  // 1. extends MenuBase

    @Override
    protected void mostrarMenu() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║      MENÚ DIAGNÓSTICOS       ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  1. Registrar Diagnóstico    ║");
        System.out.println("║  2. Consultar Historial      ║");
        System.out.println("║  3. Modificar Diagnóstico    ║");
        System.out.println("║  4. Eliminar Diagnóstico     ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  5. Salir                    ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("   Elige una opción: ");
    }

    @Override
    protected boolean procesarOpcion(int opcion) {  // 2. switch aquí, NO en ejecutar()
        switch (opcion) {
            case 1 -> ejecutarOpcion("Registrar Diagnóstico");
            case 2 -> ejecutarOpcion("Consultar Historial (por médico o fecha)");
            case 3 -> ejecutarOpcion("Modificar Diagnóstico");
            case 4 -> ejecutarOpcion("Eliminar Diagnóstico");
            case 5 -> { return false; }  // señal de salida
            default -> System.out.println("❌ Opción no válida.");
        }
        return true;
    }

    @Override
    protected int getOpcionSalida() { return 5; }  // 3. opción de salida

    @Override
    protected void ejecutarOpcion(String opcion) {
        System.out.println("✓ Ejecutando: " + opcion);
        // aquí irá la lógica real
    }
    // 4. NO redefinir ejecutar(), NO tener Scanner propio
}
