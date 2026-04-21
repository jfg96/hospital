package app.Menus;

import java.util.Scanner;

public class MenuHabitaciones {
    static Scanner input = new Scanner(System.in);

    private void mostrarMenu() {
        System.out.println("╔════════════════════════════╗");
        System.out.println("║     MENÚ HABITACIONES      ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║  1. Alta de Habitación     ║");
        System.out.println("║  2. Consultar Habitaciones ║");
        System.out.println("║  3. Modificar Habitación   ║");
        System.out.println("║  4. Eliminar Habitación    ║");
        System.out.println("╠════════════════════════════╣");
        System.out.println("║  5. Salir                  ║");
        System.out.println("╚════════════════════════════╝");
        System.out.print("   Elige una opción: ");
    }

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = input.nextInt();
            switch (opcion) {
                case 1 -> ejecutarOpcion("Alta de Habitación");
                case 2 -> ejecutarOpcion("Consultar Habitaciones de una planta y su ocupación");
                case 3 -> ejecutarOpcion("Modificar Habitación");
                case 4 -> ejecutarOpcion("Eliminar Habitación");
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 5);
    }

    private void ejecutarOpcion(String opcion) {
        // lógica de cada opción
    }
}
