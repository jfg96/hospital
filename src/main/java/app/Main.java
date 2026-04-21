/**
 *
 * @author: Antonio Manuel Rodriguez Palenzuela
 * @since 20/04/2026
 * @Description:
 */

package app;
import java.util.Scanner;
import static java.awt.Color.GREEN;
import static jdk.internal.org.jline.utils.AttributedStyle.WHITE;
import static jdk.javadoc.internal.html.HtmlAttr.InputType.RESET;

public class Main {
    static Scanner input = new Scanner(System.in);
    static void main() {
        int opcion;

        do {
            MenuPrincipal();
            opcion = input.nextInt();
            switch (opcion) {
                case 1 -> ejecutarOpcion();
                case 2 -> ejecutarOpcion();
                case 3 -> ejecutarOpcion();
                case 4 -> ejecutarOpcion();
                case 5 -> ejecutarOpcion();
                case 6 -> ejecutarOpcion();
                case 7 -> ejecutarOpcion();
                case 8 -> System.exit(0);
            }
        } while (opcion == 7);
        }
    }


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

    private static void ejecutarOpcion() {
        
}