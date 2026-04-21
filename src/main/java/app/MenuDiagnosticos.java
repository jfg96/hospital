package app;

import java.util.Scanner;

public class MenuDiagnosticos {
    static Scanner input = new Scanner(System.in);

    private void mostrarMenu() {
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

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = input.nextInt();
            switch (opcion) {
                case 1 -> ejecutarOpcion("Registrar Diagnóstico");
                case 2 -> ejecutarOpcion("Consultar Historial de diagnósticos (filtrar por médico o fecha)");
                case 3 -> ejecutarOpcion("Modificar Diagnóstico");
                case 4 -> ejecutarOpcion("Eliminar Diagnóstico");
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 5);
    }

    private void ejecutarOpcion(String opcion) {
        // lógica de cada opción
    }
}
