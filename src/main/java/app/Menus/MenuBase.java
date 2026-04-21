/**
 * Clase MenuBase - Clase abstracta base para todos los menús del sistema.
 *
 * Proporciona la estructura común y reutilizable para todos los menús,
 * evitando duplicación de código e implementando patrones de diseño.
 *
 * @author Antonio Manuel
 * @version 1.0
 * @since 21/04/2026
 */
package app.Menus;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Clase abstracta que define la estructura base para todos los menús.
 * Implementa el patrón Template Method para el control de menús.
 */
public abstract class MenuBase {
    /** Scanner compartido para leer entrada del usuario */
    protected static final Scanner input = new Scanner(System.in);

    /**
     * Muestra el menú específico de cada subclase.
     * Método abstracto que debe ser implementado por cada menú.
     */
    protected abstract void mostrarMenu();

    /**
     * Ejecuta la operación seleccionada.
     * Método abstracto que debe ser implementado por cada menú.
     *
     * @param opcion La descripción de la operación a ejecutar
     */
    protected abstract void ejecutarOpcion(String opcion);

    /**
     * Obtiene la opción de salida específica del menú.
     *
     * @return El número de opción que representa salir del menú
     */
    protected abstract int getOpcionSalida();

    /**
     * Procesa la opción seleccionada por el usuario.
     * Método abstracto que mapea números a operaciones específicas.
     *
     * @param opcion El número de opción seleccionada
     * @return true si se debe continuar el bucle, false si se debe salir
     */
    protected abstract boolean procesarOpcion(int opcion);

    /**
     * Ejecuta el menú con control de excepciones.
     *
     * Implementa un bucle interactivo seguro que:
     * - Captura InputMismatchException para entradas inválidas
     * - Limpia el buffer del Scanner automáticamente
     * - Continúa hasta que se seleccione la opción de salida
     */
    public void ejecutar() {
        int opcion = 0;

        do {
            try {
                mostrarMenu();
                opcion = input.nextInt();
                input.nextLine(); // Limpiar buffer

                if (!procesarOpcion(opcion)) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                input.nextLine(); // Limpiar buffer
                opcion = 0;
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                input.nextLine();
                opcion = 0;
            }
        } while (opcion != getOpcionSalida());
    }
}

