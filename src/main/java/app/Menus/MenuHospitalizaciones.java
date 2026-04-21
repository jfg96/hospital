package app.Menus;

import java.util.Scanner;

public class MenuHospitalizaciones {
    static Scanner input = new Scanner(System.in);

    private void mostrarMenu() {
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

    private void ejecutarOpcion(String opcion) {
        // lógica de cada opción
    }
}
