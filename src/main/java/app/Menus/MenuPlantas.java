package app;

import dao.PlantaDAO;
import dao.impl.PlantaDAOImpl;
import modelo.Planta;

import java.util.List;

/**
 * Submenú de consola para la gestión de plantas del hospital.
 * @author Kyle
 */
public class MenuPlantas {

    private final PlantaDAO dao = new PlantaDAOImpl();

    public void mostrar() {
        int opcion;
        do {
            Utilidades.titulo("GESTIÓN DE PLANTAS");
            System.out.println("  1. Añadir planta");
            System.out.println("  2. Listar todas las plantas");
            System.out.println("  3. Buscar planta");
            System.out.println("  4. Modificar planta");
            System.out.println("  5. Eliminar planta");
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
        Utilidades.titulo("NUEVA PLANTA");
        try {
            int numero           = Utilidades.leerIntPositivo("Número de planta: ");
            int cantHabitaciones = Utilidades.leerIntPositivo("Cantidad de habitaciones: ");
            String especialidad  = Utilidades.leerString("Especialidad: ");

            Planta planta = new Planta(numero, cantHabitaciones, especialidad);
            dao.insertar(planta);
            System.out.println("  [OK] Planta registrada correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo registrar la planta: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    private void listar() {
        Utilidades.titulo("LISTADO DE PLANTAS");
        try {
            List<Planta> lista = dao.listar();
            if (lista.isEmpty()) {
                System.out.println("  No hay plantas registradas.");
            } else {
                for (Planta p : lista) {
                    System.out.println("  " + p);
                }
                System.out.println("\n  Total: " + lista.size() + " planta(s).");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo obtener el listado: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    private void buscar() {
        Utilidades.titulo("BUSCAR PLANTA");
        System.out.println("  1. Por número de planta");
        System.out.println("  2. Por especialidad");
        int criterio = Utilidades.leerInt("Selecciona criterio: ");

        try {
            switch (criterio) {
                case 1 -> {
                    int num = Utilidades.leerIntPositivo("Número de planta: ");
                    Planta p = dao.buscarPorId(num);
                    if (p != null) System.out.println("  " + p);
                    else System.out.println("  No se encontró la planta " + num);
                }
                case 2 -> {
                    String esp = Utilidades.leerString("Especialidad: ");
                    List<Planta> resultados = dao.buscarPorEspecialidad(esp);
                    if (resultados.isEmpty()) {
                        System.out.println("  No se encontraron plantas con esa especialidad.");
                    } else {
                        resultados.forEach(p -> System.out.println("  " + p));
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
        Utilidades.titulo("MODIFICAR PLANTA");
        try {
            int num = Utilidades.leerIntPositivo("Número de la planta a modificar: ");
            Planta p = dao.buscarPorId(num);

            if (p == null) {
                System.out.println("  [!] No se encontró la planta " + num);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Datos actuales: " + p);
            System.out.println("  (Deja el campo vacío para mantener el valor actual)\n");

            String cantStr = Utilidades.leerStringOpcional("Nueva cantidad de habitaciones [" + p.getCantidadHabitaciones() + "]: ");
            if (!cantStr.isEmpty()) {
                try {
                    int cant = Integer.parseInt(cantStr);
                    if (cant > 0) p.setCantidadHabitaciones(cant);
                    else System.out.println("  [!] Valor no válido, se mantiene el anterior.");
                } catch (NumberFormatException ex) {
                    System.out.println("  [!] Formato no válido, se mantiene el valor anterior.");
                }
            }

            String especialidad = Utilidades.leerStringOpcional("Nueva especialidad [" + p.getEspecialidad() + "]: ");
            if (!especialidad.isEmpty()) p.setEspecialidad(especialidad);

            dao.actualizar(p);
            System.out.println("  [OK] Planta actualizada correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo modificar la planta: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    private void eliminar() {
        Utilidades.titulo("ELIMINAR PLANTA");
        try {
            int num = Utilidades.leerIntPositivo("Número de la planta a eliminar: ");
            Planta p = dao.buscarPorId(num);

            if (p == null) {
                System.out.println("  [!] No se encontró la planta " + num);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Se eliminará: " + p);
            if (Utilidades.confirmar("¿Estás seguro?")) {
                dao.eliminar(num);
                System.out.println("  [OK] Planta eliminada.");
            } else {
                System.out.println("  Operación cancelada.");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo eliminar la planta: " + e.getMessage());
        }
        Utilidades.pausar();
    }
}
