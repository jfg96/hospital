package app;

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

    /**
     * Lee un texto no vacío por consola. Repite la solicitud si el usuario
     * introduce una cadena vacía.
     * @param mensaje texto que se muestra como prompt
     * @return la cadena introducida por el usuario (nunca vacía)
     */
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

    /**
     * Lee un texto por consola permitiendo que esté vacío.
     * @param mensaje texto que se muestra como prompt
     * @return la cadena introducida por el usuario (puede estar vacía)
     */
    public static String leerStringOpcional(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    /**
     * Lee un número entero por consola. Repite la solicitud si la entrada
     * no es un entero válido.
     * @param mensaje texto que se muestra como prompt
     * @return el entero introducido por el usuario
     */
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

    /**
     * Lee un número entero estrictamente positivo (mayor que 0). Repite la
     * solicitud si el valor introducido no cumple la condición.
     * @param mensaje texto que se muestra como prompt
     * @return el entero positivo introducido por el usuario
     */
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

    /**
     * Lee un número decimal por consola. Repite la solicitud si la entrada
     * no es un número válido.
     * @param mensaje texto que se muestra como prompt
     * @return el número decimal introducido por el usuario
     */
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

    /**
     * Lee un número decimal estrictamente positivo (mayor que 0). Repite la
     * solicitud si el valor introducido no cumple la condición.
     * @param mensaje texto que se muestra como prompt
     * @return el número decimal positivo introducido por el usuario
     */
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

    /**
     * Lee y valida un DNI español (8 dígitos + 1 letra mayúscula). Repite la
     * solicitud si el formato no es correcto.
     * @param mensaje texto que se muestra como prompt
     * @return el DNI válido introducido por el usuario (en mayúsculas)
     */
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

    /**
     * Lee y valida un número de teléfono de 9 dígitos. Repite la solicitud
     * si el formato no es correcto.
     * @param mensaje texto que se muestra como prompt
     * @return el teléfono válido introducido por el usuario
     */
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

    /**
     * Lee y valida una fecha en formato {@code dd/MM/yyyy}. Repite la
     * solicitud si el formato no es correcto.
     * @param mensaje texto que se muestra como prompt (sin el formato)
     * @return la fecha introducida como {@link LocalDate}
     */
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

    /**
     * Lee una fecha en formato {@code dd/MM/yyyy} y la devuelve como
     * cadena en formato ISO ({@code yyyy-MM-dd}).
     * @param mensaje texto que se muestra como prompt
     * @return la fecha introducida en formato {@code yyyy-MM-dd}
     */
    public static String leerFechaComoString(String mensaje) {
        LocalDate fecha = leerFecha(mensaje);
        return fecha.toString();
    }

    /**
     * Lee una fecha en formato {@code dd/MM/yyyy} de forma opcional. Si el
     * usuario deja el campo vacío o introduce un formato incorrecto, devuelve
     * {@code null}.
     * @param mensaje texto que se muestra como prompt (sin el formato)
     * @return la fecha introducida como {@link LocalDate}, o {@code null} si se omite
     */
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

    /**
     * Imprime una línea separadora decorativa por consola.
     */
    public static void separador() {
        System.out.println("═══════════════════════════════════════════════════");
    }

    /**
     * Imprime un título formateado con separadores decorativos por consola.
     * @param texto texto del título a mostrar
     */
    public static void titulo(String texto) {
        System.out.println();
        separador();
        System.out.println("  " + texto);
        separador();
    }

    /**
     * Pausa la ejecución hasta que el usuario pulse Enter.
     */
    public static void pausar() {
        System.out.print("\nPulsa Enter para continuar...");
        sc.nextLine();
    }

    /**
     * Solicita confirmación al usuario mediante una pregunta de sí/no.
     * @param mensaje texto de la pregunta que se muestra al usuario
     * @return {@code true} si el usuario responde afirmativamente ({@code s}, {@code si} o {@code sí});
     *         {@code false} en cualquier otro caso
     */
    public static boolean confirmar(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        String resp = sc.nextLine().trim().toLowerCase();
        return resp.equals("s") || resp.equals("si") || resp.equals("sí");
    }
}
