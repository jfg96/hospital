package app;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * @author: Antonio Manuel Rodriguez Palenzuela
 * @since 20/04/2026
 * Clase Main - Punto de entrada del sistema de gestión hospitalaria.
 * Esta clase controla el menú principal y la navegación entre los diferentes
 * módulos del sistema (Plantas, Habitaciones, Médicos, etc.).
 *
 * @version 1.0
 */
public class Main {
    /** Scanner estático para leer entrada del usuario desde la consola */
    static Scanner input = new Scanner(System.in);

    /**
     * Método main - Ejecuta el menú principal del sistema.
     *
     * Controla un bucle interactivo que:
     * - Muestra el menú principal
     * - Lee la opción del usuario
     * - Ejecuta la opción seleccionada
     * - Maneja excepciones de entrada y ejecución
     * - Continúa hasta que el usuario seleccione salir (opción 8)
     *
     * Las excepciones capturadas:
     * - InputMismatchException: cuando el usuario ingresa datos que no son números
     * - Exception: para cualquier otro error inesperado
     */
    static void main() {
        int opcion;

        do {
            try {
                // Mostrar el menú principal
                MenuPrincipal();
                
                // Leer la opción seleccionada por el usuario
                opcion = input.nextInt();
                input.nextLine(); // Limpiar el salto de línea del buffer

                // Ejecutar la opción seleccionada
                switch (opcion) {
                    case 1 -> ejecutarOpcion(); // Menú Plantas
                    case 2 -> ejecutarOpcion(); // Menú Habitaciones
                    case 3 -> ejecutarOpcion(); // Menú Médicos
                    case 4 -> ejecutarOpcion(); // Menú Auxiliares
                    case 5 -> ejecutarOpcion(); // Menú Pacientes
                    case 6 -> ejecutarOpcion(); // Menú Citas
                    case 7 -> ejecutarOpcion(); // Menú Consultas
                    case 8 -> System.exit(0);   // Salir del sistema
                    default -> System.out.println(" Opción no válida. Por favor, elige una opción entre 1 y 8.");
                }
            } catch (InputMismatchException e) {
                // Captura el error cuando el usuario ingresa algo que no es un número
                System.out.println(" Error: Debes ingresar un número válido.");
                input.nextLine(); // Limpiar el buffer para evitar bucles infinitos
                opcion = 0;
            } catch (Exception e) {
                // Captura cualquier otro error inesperado
                System.out.println(" Error inesperado: " + e.getMessage());
                input.nextLine();
                opcion = 0;
            }
        } while (opcion == 7); // Continuar mientras la opción sea 7 (Menú Consultas)
    }

    /**
     * Método MenuPrincipal - Muestra el menú principal del sistema.
     *
     * Imprime una tabla con las opciones disponibles del sistema:
     * 1. Menú Plantas
     * 2. Menú Habitaciones
     * 3. Menú Médicos
     * 4. Menú Auxiliares
     * 5. Menú Pacientes
     * 6. Menú Citas
     * 7. Menú Consultas
     * 8. Salir del sistema
     */
    static void MenuPrincipal() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          MENÚ PRINCIPAL              ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Menú Plantas                     ║");
        System.out.println("║  2. Menú Habitaciones                ║");
        System.out.println("║  3. Menú Médicos                     ║");
        System.out.println("║  4. Menú Auxiliares                  ║");
        System.out.println("║  5. Menú Pacientes                   ║");
        System.out.println("║  6. Menú Citas                       ║");
        System.out.println("║  7. Menú Consultas                   ║");
        System.out.println("║  8. Salir                            ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("   Elige una opción: ");
    }

    /**
     * Método ejecutarOpcion - Ejecuta la lógica de la opción seleccionada.
     *
     * Este es un método genérico que se debe expandir para manejar
     * las diferentes opciones del menú principal. Incluye un try-catch
     * para manejar excepciones durante la ejecución.
     *
     * @throws Exception sí ocurre algún error durante la ejecución
     */
    private static void ejecutarOpcion() {
        try {
            // Aquí irá la lógica específica de cada opción seleccionada
            System.out.println("Ejecutando opción...");
        } catch (Exception e) {
            // Captura y reporta errores durante la ejecución
            System.out.println("Error al ejecutar la opción: " + e.getMessage());
        }
    }
}