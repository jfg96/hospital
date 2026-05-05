package app.Menus;

import app.Utilidades;
import dao.HospitalizacionDAO;
import dao.impl.HospitalizacionDAOImpl;
import modelo.Hospitalizacion;

import java.time.LocalDate;
import java.util.List;

/**
 * Submenú de consola para la gestión de hospitalizaciones.
 * @author Antonio Manuel Rodriguez Palenzuela
 */
public class MenuHospitalizaciones {

    private final HospitalizacionDAO dao = new HospitalizacionDAOImpl();

    /**
     * Muestra el submenú de hospitalizaciones y gestiona la opción seleccionada
     * por el usuario hasta que éste elija volver al menú principal.
     */
    public void mostrar() {
        int opcion;
        do {
            Utilidades.titulo("GESTIÓN DE HOSPITALIZACIONES");
            System.out.println("  1. Registrar hospitalización (ingreso)");
            System.out.println("  2. Listar todas las hospitalizaciones");
            System.out.println("  3. Listar hospitalizaciones activas");
            System.out.println("  4. Buscar hospitalizaciones");
            System.out.println("  5. Dar de alta (registrar fecha de alta)");
            System.out.println("  6. Modificar hospitalización");
            System.out.println("  7. Eliminar hospitalización");
            System.out.println("  0. Volver al menú principal");
            Utilidades.separador();
            opcion = Utilidades.leerInt("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> registrarIngreso();
                case 2 -> listarTodas();
                case 3 -> listarActivas();
                case 4 -> buscar();
                case 5 -> darAlta();
                case 6 -> modificar();
                case 7 -> eliminar();
                case 0 -> System.out.println("  Volviendo al menú principal...");
                default -> System.out.println("  [!] Opción no válida.");
            }
        } while (opcion != 0);
    }

    /**
     * Solicita los datos de ingreso de un enfermo y registra una nueva
     * hospitalización en la base de datos.
     */
    private void registrarIngreso() {
        Utilidades.titulo("REGISTRAR HOSPITALIZACIÓN");
        try {
            int idEnfermo     = Utilidades.leerIntPositivo("ID del enfermo: ");
            int numHabitacion = Utilidades.leerIntPositivo("Número de habitación: ");
            int numPlanta     = Utilidades.leerIntPositivo("Número de planta: ");
            LocalDate fechaIng = Utilidades.leerFecha("Fecha de ingreso");

            Hospitalizacion h = new Hospitalizacion(idEnfermo, numHabitacion, numPlanta, fechaIng);
            dao.insertar(h);
            System.out.println("  [OK] Hospitalización registrada correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo registrar la hospitalización: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Obtiene todas las hospitalizaciones de la base de datos y las muestra
     * por consola.
     */
    private void listarTodas() {
        Utilidades.titulo("TODAS LAS HOSPITALIZACIONES");
        try {
            List<Hospitalizacion> lista = dao.listar();
            if (lista.isEmpty()) {
                System.out.println("  No hay hospitalizaciones registradas.");
            } else {
                for (Hospitalizacion h : lista) {
                    System.out.println("  [ID " + h.getIdHospitalizacion() + "] " + h);
                }
                System.out.println("\n  Total: " + lista.size() + " registro(s).");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo obtener el listado: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Obtiene únicamente las hospitalizaciones activas (sin fecha de alta)
     * y las muestra por consola.
     */
    private void listarActivas() {
        Utilidades.titulo("HOSPITALIZACIONES ACTIVAS");
        try {
            List<Hospitalizacion> lista = dao.listarActivas();
            if (lista.isEmpty()) {
                System.out.println("  No hay hospitalizaciones activas.");
            } else {
                for (Hospitalizacion h : lista) {
                    System.out.println("  [ID " + h.getIdHospitalizacion() + "] " + h);
                }
                System.out.println("\n  Total: " + lista.size() + " hospitalización(es) activa(s).");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo obtener el listado: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Solicita un criterio de búsqueda (ID, enfermo o habitación) y muestra
     * las hospitalizaciones que coinciden.
     */
    private void buscar() {
        Utilidades.titulo("BUSCAR HOSPITALIZACIONES");
        System.out.println("  1. Por ID de hospitalización");
        System.out.println("  2. Por enfermo");
        System.out.println("  3. Por habitación");
        int criterio = Utilidades.leerInt("Selecciona criterio: ");

        try {
            switch (criterio) {
                case 1 -> {
                    int id = Utilidades.leerIntPositivo("ID de la hospitalización: ");
                    Hospitalizacion h = dao.buscarPorId(id);
                    if (h != null) System.out.println("  " + h);
                    else System.out.println("  No se encontró la hospitalización con ID " + id);
                }
                case 2 -> {
                    int idEnfermo = Utilidades.leerIntPositivo("ID del enfermo: ");
                    List<Hospitalizacion> resultados = dao.buscarPorEnfermo(idEnfermo);
                    if (resultados.isEmpty()) {
                        System.out.println("  No se encontraron hospitalizaciones para ese enfermo.");
                    } else {
                        resultados.forEach(h -> System.out.println("  [ID " + h.getIdHospitalizacion() + "] " + h));
                    }
                }
                case 3 -> {
                    int numHab = Utilidades.leerIntPositivo("Número de habitación: ");
                    List<Hospitalizacion> resultados = dao.buscarPorHabitacion(numHab);
                    if (resultados.isEmpty()) {
                        System.out.println("  No se encontraron hospitalizaciones en esa habitación.");
                    } else {
                        resultados.forEach(h -> System.out.println("  [ID " + h.getIdHospitalizacion() + "] " + h));
                    }
                }
                default -> System.out.println("  [!] Criterio no válido.");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] Error durante la búsqueda: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Lista las hospitalizaciones activas y permite registrar la fecha de alta
     * de la que el usuario seleccione.
     */
    private void darAlta() {
        Utilidades.titulo("DAR DE ALTA (REGISTRAR FECHA DE ALTA)");
        try {
            List<Hospitalizacion> activas = dao.listarActivas();
            if (activas.isEmpty()) {
                System.out.println("  No hay hospitalizaciones activas.");
                Utilidades.pausar();
                return;
            }

            System.out.println("  Hospitalizaciones activas:");
            for (Hospitalizacion h : activas) {
                System.out.println("  [ID " + h.getIdHospitalizacion() + "] " + h);
            }
            System.out.println();

            int idHosp = Utilidades.leerIntPositivo("ID de la hospitalización a dar de alta: ");
            String fechaAlta = Utilidades.leerFechaComoString("Fecha de alta");

            dao.registrarAlta(idHosp, fechaAlta);
            System.out.println("  [OK] Alta registrada correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo registrar el alta: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Busca una hospitalización por su ID y permite modificar su fecha de alta.
     */
    private void modificar() {
        Utilidades.titulo("MODIFICAR HOSPITALIZACIÓN");
        try {
            int id = Utilidades.leerIntPositivo("ID de la hospitalización a modificar: ");
            Hospitalizacion h = dao.buscarPorId(id);

            if (h == null) {
                System.out.println("  [!] No se encontró la hospitalización con ID " + id);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Datos actuales: " + h);
            System.out.println("  (Deja el campo vacío para mantener el valor actual)\n");

            LocalDate nuevaAlta = Utilidades.leerFechaOpcional("Nueva fecha de alta");
            if (nuevaAlta != null) h.setFechaAlta(nuevaAlta);

            dao.actualizar(h);
            System.out.println("  [OK] Hospitalización actualizada correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo modificar la hospitalización: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Busca una hospitalización por su ID y, tras confirmación, la elimina
     * de la base de datos.
     */
    private void eliminar() {
        Utilidades.titulo("ELIMINAR HOSPITALIZACIÓN");
        try {
            int id = Utilidades.leerIntPositivo("ID de la hospitalización a eliminar: ");
            Hospitalizacion h = dao.buscarPorId(id);

            if (h == null) {
                System.out.println("  [!] No se encontró la hospitalización con ID " + id);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Se eliminará: " + h);
            if (Utilidades.confirmar("¿Estás seguro?")) {
                dao.eliminar(id);
                System.out.println("  [OK] Hospitalización eliminada.");
            } else {
                System.out.println("  Operación cancelada.");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo eliminar la hospitalización: " + e.getMessage());
        }
        Utilidades.pausar();
    }
}
