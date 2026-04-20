package app;

import java.util.Scanner;

public class MenuAuxiliares {
    static Scanner input = new Scanner(System.in);

    private void mostrarMenu() {
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

    private void ejecutarOpcion(String opcion) {
        // lógica de cada opción
    }
}
