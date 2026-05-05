package app;

import app.Menus.*;
import conexion.ConexionBD;
import dao.impl.*;

/**
 * Punto de entrada de la aplicación de gestión hospitalaria.
 * @author Antonio Manuel Rodriguez Palenzuela
 */
public class Main {

    /**
     * Método principal. Inicializa la base de datos, instancia los submenús
     * y lanza el bucle del menú principal.
     * @param args argumentos de la línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        // ── Inicializar tablas en la BD ──────────────────
        try {
            inicializarBaseDatos();
        } catch (Exception e) {
            System.err.println("[ERROR CRÍTICO] No se pudo inicializar la base de datos: " + e.getMessage());
            return;
        }

        // ── Instanciar los submenús ──────────────────────
        MenuEnfermos           menuEnfermos          = new MenuEnfermos();
        MenuMedicos            menuMedicos           = new MenuMedicos();
        MenuAuxiliares         menuAuxiliares        = new MenuAuxiliares();
        MenuPlantas            menuPlantas           = new MenuPlantas();
        MenuHabitaciones       menuHabitaciones      = new MenuHabitaciones();
        MenuHospitalizaciones  menuHospitalizaciones = new MenuHospitalizaciones();
        MenuDiagnosticos       menuDiagnosticos      = new MenuDiagnosticos();

        // ── Bucle del menú principal ─────────────────────
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = Utilidades.leerInt("Selecciona una opción: ");

            try {
                switch (opcion) {
                    case 1 -> menuEnfermos.mostrar();
                    case 2 -> menuMedicos.mostrar();
                    case 3 -> menuAuxiliares.mostrar();
                    case 4 -> menuPlantas.mostrar();
                    case 5 -> menuHabitaciones.mostrar();
                    case 6 -> menuHospitalizaciones.mostrar();
                    case 7 -> menuDiagnosticos.mostrar();
                    case 0 -> System.out.println("\n¡Hasta luego! Cerrando la aplicación...");
                    default -> System.out.println("  [!] Opción no válida. Inténtalo de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("  [ERROR] Ha ocurrido un error inesperado: " + e.getMessage());
            }
        } while (opcion != 0);

        // ── Cerrar conexión al salir ─────────────────────
        try {
            ConexionBD.cerrarConexion();
        } catch (Exception e) {
            System.err.println("[ERROR] No se pudo cerrar la conexión: " + e.getMessage());
        }
    }

    /**
     * Crea las tablas de la base de datos llamando a {@code crearTabla()} en
     * cada implementación DAO. Si ya existen, la operación no tiene efecto.
     */
    private static void inicializarBaseDatos() {
        System.out.println("Inicializando base de datos...");
        new PlantaDAOImpl().crearTabla();
        new HabitacionDAOImpl().crearTabla();
        new EnfermoDAOImpl().crearTabla();
        new MedicoDAOImpl().crearTabla();
        new AuxiliarDAOImpl().crearTabla();
        new HospitalizacionDAOImpl().crearTabla();
        new DiagnosticoDAOImpl().crearTabla();
        System.out.println("Base de datos lista.\n");
    }

    /**
     * Imprime por consola el menú principal con todas las opciones disponibles.
     */
    private static void mostrarMenuPrincipal() {
        Utilidades.titulo("SISTEMA DE GESTIÓN HOSPITALARIA");
        System.out.println("  1. Gestión de Enfermos");
        System.out.println("  2. Gestión de Médicos");
        System.out.println("  3. Gestión de Auxiliares");
        System.out.println("  4. Gestión de Plantas");
        System.out.println("  5. Gestión de Habitaciones");
        System.out.println("  6. Gestión de Hospitalizaciones");
        System.out.println("  7. Gestión de Diagnósticos");
        System.out.println("  0. Salir");
        Utilidades.separador();
    }
}