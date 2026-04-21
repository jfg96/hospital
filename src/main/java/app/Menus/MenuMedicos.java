package app.Menus;

import java.util.Scanner;

public class MenuMedicos {
    static Scanner input = new Scanner(System.in);

    private void mostrarMenu() {
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

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = input.nextInt();
            switch (opcion) {
                case 1 -> ejecutarOpcion("Registrar Médico");
                case 2 -> ejecutarOpcion("Consultar Médicos (filtrar por nombre, especialidad o DNI)");
                case 3 -> ejecutarOpcion("Modificar Médico");
                case 4 -> ejecutarOpcion("Eliminar Médico");
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 5);
    }

    private void ejecutarOpcion(String opcion) {
        // lógica de cada opción
    }
}
