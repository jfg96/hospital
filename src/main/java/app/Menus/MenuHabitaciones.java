package app.Menus;

import app.Utilidades;
import dao.HabitacionDAO;
import dao.impl.HabitacionDAOImpl;
import modelo.Habitacion;

import java.util.List;

/**
 * Submenú de consola para la gestión de habitaciones.
 * @author Antonio Manuel Rodriguez Palenzuela
 */
public class MenuHabitaciones {

    private final HabitacionDAO dao = new HabitacionDAOImpl();

    /**
     * Muestra el submenú de habitaciones y gestiona la opción seleccionada
     * por el usuario hasta que éste elija volver al menú principal.
     */
    public void mostrar() {
        int opcion;
        do {
            Utilidades.titulo("GESTIÓN DE HABITACIONES");
            System.out.println("  1. Añadir habitación");
            System.out.println("  2. Listar todas las habitaciones");
            System.out.println("  3. Buscar habitación");
            System.out.println("  4. Modificar habitación");
            System.out.println("  5. Eliminar habitación");
            System.out.println("  0. Volver al menú principal");
            Utilidades.separador();
            opcion = Utilidades.leerInt("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> añadir();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> modificar();
                case 5 -> eliminar();
                case 0 -> System.out.println("  Volviendo al menú principal...");
                default -> System.out.println("  [!] Opción no válida.");
            }
        } while (opcion != 0);
    }

    /**
     * Solicita los datos de una nueva habitación por consola y la inserta
     * en la base de datos.
     */
    private void añadir() {
        Utilidades.titulo("NUEVA HABITACIÓN");
        try {
            int numHab    = Utilidades.leerIntPositivo("Número de habitación: ");
            int numPlanta = Utilidades.leerIntPositivo("Número de planta: ");
            int camas     = Utilidades.leerIntPositivo("Cantidad de camas: ");
            String obs    = Utilidades.leerStringOpcional("Observaciones (opcional): ");

            Habitacion hab = new Habitacion(numHab, numPlanta, camas, obs.isEmpty() ? null : obs);
            dao.insertar(hab);
            System.out.println("  [OK] Habitación registrada correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo registrar la habitación: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Muestra el listado de habitaciones, permitiendo filtrar por planta
     * o listar todas.
     */
    private void listar() {
        Utilidades.titulo("LISTADO DE HABITACIONES");
        System.out.println("  1. Todas las habitaciones");
        System.out.println("  2. Por planta");
        int modo = Utilidades.leerInt("Selecciona: ");

        try {
            List<Habitacion> lista;
            if (modo == 2) {
                int planta = Utilidades.leerIntPositivo("Número de planta: ");
                lista = dao.buscarPorPlanta(planta);
            } else {
                lista = dao.listar();
            }

            if (lista.isEmpty()) {
                System.out.println("  No hay habitaciones registradas.");
            } else {
                for (Habitacion h : lista) {
                    System.out.println("  " + h);
                }
                System.out.println("\n  Total: " + lista.size() + " habitación(es).");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo obtener el listado: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Busca una habitación por su número y muestra sus datos por consola.
     */
    private void buscar() {
        Utilidades.titulo("BUSCAR HABITACIÓN");
        try {
            int numHab = Utilidades.leerIntPositivo("Número de habitación: ");
            Habitacion h = dao.buscarPorId(numHab);
            if (h != null) {
                System.out.println("  " + h);
            } else {
                System.out.println("  No se encontró la habitación " + numHab);
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] Error durante la búsqueda: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Busca una habitación por su número, muestra sus datos actuales y permite
     * modificar los campos que el usuario desee.
     */
    private void modificar() {
        Utilidades.titulo("MODIFICAR HABITACIÓN");
        try {
            int numHab = Utilidades.leerIntPositivo("Número de la habitación a modificar: ");
            Habitacion h = dao.buscarPorId(numHab);

            if (h == null) {
                System.out.println("  [!] No se encontró la habitación " + numHab);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Datos actuales: " + h);
            System.out.println("  (Deja el campo vacío para mantener el valor actual)\n");

            String camasStr = Utilidades.leerStringOpcional("Nueva cantidad de camas [" + h.getCantidadCamas() + "]: ");
            if (!camasStr.isEmpty()) {
                try {
                    int camas = Integer.parseInt(camasStr);
                    if (camas > 0) h.setCantidadCamas(camas);
                    else System.out.println("  [!] Valor no válido, se mantiene el anterior.");
                } catch (NumberFormatException ex) {
                    System.out.println("  [!] Formato no válido, se mantiene el valor anterior.");
                }
            }

            String obs = Utilidades.leerStringOpcional("Nuevas observaciones [" + h.getObservaciones() + "]: ");
            if (!obs.isEmpty()) h.setObservaciones(obs);

            dao.actualizar(h);
            System.out.println("  [OK] Habitación actualizada correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo modificar la habitación: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Busca una habitación por su número y, tras confirmación, la elimina
     * de la base de datos.
     */
    private void eliminar() {
        Utilidades.titulo("ELIMINAR HABITACIÓN");
        try {
            int numHab = Utilidades.leerIntPositivo("Número de la habitación a eliminar: ");
            Habitacion h = dao.buscarPorId(numHab);

            if (h == null) {
                System.out.println("  [!] No se encontró la habitación " + numHab);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Se eliminará: " + h);
            if (Utilidades.confirmar("¿Estás seguro?")) {
                dao.eliminar(numHab);
                System.out.println("  [OK] Habitación eliminada.");
            } else {
                System.out.println("  Operación cancelada.");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo eliminar la habitación: " + e.getMessage());
        }
        Utilidades.pausar();
    }
}
