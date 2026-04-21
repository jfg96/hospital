package app.Menus;

import java.util.Scanner;

public class MenuEnfermos {
    static Scanner input = new Scanner(System.in);
    // Muestra las opciones por pantalla
    private void mostrarMenu() {
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

    // Lógica del menú
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
    private void ejecutarOpcion(String opcion) {
        // lógica de cada opción
    }
}