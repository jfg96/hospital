package app.Menus;

import app.Utilidades;
import dao.DiagnosticoDAO;
import dao.impl.DiagnosticoDAOImpl;
import modelo.Diagnostico;

import java.time.LocalDate;
import java.util.List;

/**
 * Submenú de consola para la gestión de diagnósticos.
 * @author Antonio Manuel Rodriguez Palenzuela
 */
public class MenuDiagnosticos {

    private final DiagnosticoDAO dao = new DiagnosticoDAOImpl();

    public void mostrar() {
        int opcion;
        do {
            Utilidades.titulo("GESTIÓN DE DIAGNÓSTICOS");
            System.out.println("  1. Añadir diagnóstico");
            System.out.println("  2. Listar todos los diagnósticos");
            System.out.println("  3. Buscar diagnósticos");
            System.out.println("  4. Modificar diagnóstico");
            System.out.println("  5. Eliminar diagnóstico");
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

    private void añadir() {
        Utilidades.titulo("NUEVO DIAGNÓSTICO");
        try {
            String dniMedico = Utilidades.leerDni("DNI del médico: ");
            int idEnfermo    = Utilidades.leerIntPositivo("ID del enfermo: ");
            LocalDate fecha  = Utilidades.leerFecha("Fecha del diagnóstico");
            String informe   = Utilidades.leerString("Informe: ");

            Diagnostico diag = new Diagnostico(0, dniMedico, idEnfermo, fecha, informe);
            dao.insertar(diag);
            System.out.println("  [OK] Diagnóstico registrado correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo registrar el diagnóstico: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    private void listar() {
        Utilidades.titulo("LISTADO DE DIAGNÓSTICOS");
        try {
            List<Diagnostico> lista = dao.listar();
            if (lista.isEmpty()) {
                System.out.println("  No hay diagnósticos registrados.");
            } else {
                for (Diagnostico d : lista) {
                    System.out.println("  [ID " + d.getCodigo() + "] " + d);
                }
                System.out.println("\n  Total: " + lista.size() + " diagnóstico(s).");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo obtener el listado: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    private void buscar() {
        Utilidades.titulo("BUSCAR DIAGNÓSTICOS");
        System.out.println("  1. Por ID del diagnóstico");
        System.out.println("  2. Por enfermo");
        System.out.println("  3. Por médico (ID del médico)");
        System.out.println("  4. Por fecha");
        int criterio = Utilidades.leerInt("Selecciona criterio: ");

        try {
            switch (criterio) {
                case 1 -> {
                    int id = Utilidades.leerIntPositivo("ID del diagnóstico: ");
                    Diagnostico d = dao.buscarPorId(id);
                    if (d != null) System.out.println("  " + d);
                    else System.out.println("  No se encontró el diagnóstico con ID " + id);
                }
                case 2 -> {
                    int idEnfermo = Utilidades.leerIntPositivo("ID del enfermo: ");
                    List<Diagnostico> resultados = dao.buscarPorEnfermo(idEnfermo);
                    if (resultados.isEmpty()) {
                        System.out.println("  No hay diagnósticos para el enfermo con ID " + idEnfermo);
                    } else {
                        resultados.forEach(d -> System.out.println("  [ID " + d.getCodigo() + "] " + d));
                        System.out.println("\n  Total: " + resultados.size() + " diagnóstico(s).");
                    }
                }
                case 3 -> {
                    int idMedico = Utilidades.leerIntPositivo("ID del médico: ");
                    List<Diagnostico> resultados = dao.buscarPorMedico(idMedico);
                    if (resultados.isEmpty()) {
                        System.out.println("  No hay diagnósticos emitidos por el médico con ID " + idMedico);
                    } else {
                        resultados.forEach(d -> System.out.println("  [ID " + d.getCodigo() + "] " + d));
                        System.out.println("\n  Total: " + resultados.size() + " diagnóstico(s).");
                    }
                }
                case 4 -> {
                    String fecha = Utilidades.leerFechaComoString("Fecha");
                    List<Diagnostico> resultados = dao.buscarPorFecha(fecha);
                    if (resultados.isEmpty()) {
                        System.out.println("  No hay diagnósticos en esa fecha.");
                    } else {
                        resultados.forEach(d -> System.out.println("  [ID " + d.getCodigo() + "] " + d));
                        System.out.println("\n  Total: " + resultados.size() + " diagnóstico(s).");
                    }
                }
                default -> System.out.println("  [!] Criterio no válido.");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] Error durante la búsqueda: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    private void modificar() {
        Utilidades.titulo("MODIFICAR DIAGNÓSTICO");
        try {
            int id = Utilidades.leerIntPositivo("ID del diagnóstico a modificar: ");
            Diagnostico d = dao.buscarPorId(id);

            if (d == null) {
                System.out.println("  [!] No se encontró el diagnóstico con ID " + id);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Datos actuales: " + d);
            System.out.println("  (Solo se puede modificar el informe)\n");

            String nuevoInforme = Utilidades.leerStringOpcional("Nuevo informe: ");
            if (!nuevoInforme.isEmpty()) {
                d.setInforme(nuevoInforme);
            }

            dao.actualizar(d);
            System.out.println("  [OK] Diagnóstico actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo modificar el diagnóstico: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    private void eliminar() {
        Utilidades.titulo("ELIMINAR DIAGNÓSTICO");
        try {
            int id = Utilidades.leerIntPositivo("ID del diagnóstico a eliminar: ");
            Diagnostico d = dao.buscarPorId(id);

            if (d == null) {
                System.out.println("  [!] No se encontró el diagnóstico con ID " + id);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Se eliminará: " + d);
            if (Utilidades.confirmar("¿Estás seguro?")) {
                dao.eliminar(id);
                System.out.println("  [OK] Diagnóstico eliminado.");
            } else {
                System.out.println("  Operación cancelada.");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo eliminar el diagnóstico: " + e.getMessage());
        }
        Utilidades.pausar();
    }
}
