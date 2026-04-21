package app;

import java.util.Scanner;

public class MenuPlantas {
    static Scanner input = new Scanner(System.in);

    private void mostrarMenu() {
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

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = input.nextInt();
            switch (opcion) {
                case 1 -> ejecutarOpcion("Alta de Planta");
                case 2 -> ejecutarOpcion("Consultar Plantas (filtrar por especialidad)");
                case 3 -> ejecutarOpcion("Modificar Planta");
                case 4 -> ejecutarOpcion("Eliminar Planta");
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 5);
    }

    private void ejecutarOpcion(String opcion) {
        // lógica de cada opción
    }
}
