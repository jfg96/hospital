package app.Menus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Clase de utilidad para leer y validar entradas del usuario por consola.
 * @author Antonio Manuel Rodriguez Palenzuela
 */
public class Utilidades {

    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // ──────────────────────────────────────────────
    //  Lectura de tipos básicos
    // ──────────────────────────────────────────────

    public static String leerString(String mensaje) {
        String valor;
        do {
            System.out.print(mensaje);
            valor = sc.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("  [!] Este campo es obligatorio. Inténtalo de nuevo.");
            }
        } while (valor.isEmpty());
        return valor;
    }

    public static String leerStringOpcional(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    public static int leerInt(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("  [!] Debes introducir un número entero válido.");
            }
        }
    }

    public static int leerIntPositivo(String mensaje) {
        int valor;
        do {
            valor = leerInt(mensaje);
            if (valor <= 0) {
                System.out.println("  [!] El valor debe ser mayor que 0.");
            }
        } while (valor <= 0);
        return valor;
    }

    public static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Double.parseDouble(linea);
            } catch (NumberFormatException e) {
                System.out.println("  [!] Debes introducir un número válido (usa . como separador decimal).");
            }
        }
    }

    public static double leerDoublePositivo(String mensaje) {
        double valor;
        do {
            valor = leerDouble(mensaje);
            if (valor <= 0) {
                System.out.println("  [!] El valor debe ser mayor que 0.");
            }
        } while (valor <= 0);
        return valor;
    }

    // ──────────────────────────────────────────────
    //  Validación de campos específicos
    // ──────────────────────────────────────────────

    public static String leerDni(String mensaje) {
        String dni;
        do {
            dni = leerString(mensaje).toUpperCase();
            if (!dni.matches("\\d{8}[A-Z]")) {
                System.out.println("  [!] Formato de DNI incorrecto. Debe ser 8 dígitos + 1 letra (ej: 12345678A).");
                dni = null;
            }
        } while (dni == null);
        return dni;
    }

    public static String leerTelefono(String mensaje) {
        String telefono;
        do {
            telefono = leerString(mensaje);
            if (!telefono.matches("\\d{9}")) {
                System.out.println("  [!] Formato de teléfono incorrecto. Debe tener 9 dígitos (ej: 612345678).");
                telefono = null;
            }
        } while (telefono == null);
        return telefono;
    }

    public static LocalDate leerFecha(String mensaje) {
        while (true) {
            String entrada = leerString(mensaje + " (dd/MM/yyyy): ");
            try {
                return LocalDate.parse(entrada, FORMATO_FECHA);
            } catch (DateTimeParseException e) {
                System.out.println("  [!] Formato de fecha incorrecto. Usa dd/MM/yyyy (ej: 15/03/1990).");
            }
        }
    }

    public static String leerFechaComoString(String mensaje) {
        LocalDate fecha = leerFecha(mensaje);
        return fecha.toString();
    }

    public static LocalDate leerFechaOpcional(String mensaje) {
        System.out.print(mensaje + " (dd/MM/yyyy, vacío para omitir): ");
        String entrada = sc.nextLine().trim();
        if (entrada.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(entrada, FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            System.out.println("  [!] Formato incorrecto, se omite el campo.");
            return null;
        }
    }

    // ──────────────────────────────────────────────
    //  Utilidades de presentación
    // ──────────────────────────────────────────────

    public static void separador() {
        System.out.println("═══════════════════════════════════════════════════");
    }

    public static void titulo(String texto) {
        System.out.println();
        separador();
        System.out.println("  " + texto);
        separador();
    }

    public static void pausar() {
        System.out.print("\nPulsa Enter para continuar...");
        sc.nextLine();
    }

    public static boolean confirmar(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        String resp = sc.nextLine().trim().toLowerCase();
        return resp.equals("s") || resp.equals("si") || resp.equals("sí");
    }
}
