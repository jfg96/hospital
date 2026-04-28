package app.Menus;

import app.Utilidades;
import dao.EnfermoDAO;
import dao.impl.EnfermoDAOImpl;
import modelo.Enfermo;

import java.time.LocalDate;
import java.util.List;

/**
 * Submenú de consola para la gestión de enfermos.
 * @author Antonio Manuel Rodriguez Palenzuela
 */
public class MenuEnfermos {

    private final EnfermoDAO dao = new EnfermoDAOImpl();

    public void mostrar() {
        int opcion;
        do {
            Utilidades.titulo("GESTIÓN DE ENFERMOS");
            System.out.println("  1. Añadir enfermo");
            System.out.println("  2. Listar todos los enfermos");
            System.out.println("  3. Buscar enfermo");
            System.out.println("  4. Modificar enfermo");
            System.out.println("  5. Eliminar enfermo");
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
        Utilidades.titulo("NUEVO ENFERMO");
        try {
            String nombre      = Utilidades.leerString("Nombre: ");
            String direccion   = Utilidades.leerString("Dirección: ");
            String dni         = Utilidades.leerDni("DNI: ");
            String telefono    = Utilidades.leerTelefono("Teléfono: ");
            LocalDate fechaNac = Utilidades.leerFecha("Fecha de nacimiento");

            Enfermo enfermo = new Enfermo(0, nombre, direccion, dni, telefono, fechaNac);
            dao.insertar(enfermo);
            System.out.println("  [OK] Enfermo registrado correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo registrar el enfermo: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    private void listar() {
        Utilidades.titulo("LISTADO DE ENFERMOS");
        try {
            List<Enfermo> lista = dao.listar();
            if (lista.isEmpty()) {
                System.out.println("  No hay enfermos registrados.");
            } else {
                for (Enfermo e : lista) {
                    System.out.println("  " + e);
                }
                System.out.println("\n  Total: " + lista.size() + " enfermo(s).");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo obtener el listado: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    private void buscar() {
        Utilidades.titulo("BUSCAR ENFERMO");
        System.out.println("  1. Por ID");
        System.out.println("  2. Por nombre");
        System.out.println("  3. Por DNI");
        int criterio = Utilidades.leerInt("Selecciona criterio: ");

        try {
            switch (criterio) {
                case 1 -> {
                    int id = Utilidades.leerIntPositivo("ID del enfermo: ");
                    Enfermo e = dao.buscarPorId(id);
                    if (e != null) System.out.println("  " + e);
                    else System.out.println("  No se encontró el enfermo con ID " + id);
                }
                case 2 -> {
                    String nombre = Utilidades.leerString("Nombre a buscar: ");
                    List<Enfermo> resultados = dao.buscarPorNombre(nombre);
                    if (resultados.isEmpty()) {
                        System.out.println("  No se encontraron resultados.");
                    } else {
                        resultados.forEach(en -> System.out.println("  " + en));
                    }
                }
                case 3 -> {
                    String dni = Utilidades.leerDni("DNI: ");
                    Enfermo e = dao.buscarPorDni(dni);
                    if (e != null) System.out.println("  " + e);
                    else System.out.println("  No se encontró el enfermo con DNI " + dni);
                }
                default -> System.out.println("  [!] Criterio no válido.");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] Error durante la búsqueda: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    private void modificar() {
        Utilidades.titulo("MODIFICAR ENFERMO");
        try {
            int id = Utilidades.leerIntPositivo("ID del enfermo a modificar: ");
            Enfermo e = dao.buscarPorId(id);

            if (e == null) {
                System.out.println("  [!] No se encontró el enfermo con ID " + id);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Datos actuales: " + e);
            System.out.println("  (Deja el campo vacío para mantener el valor actual)\n");

            String nombre = Utilidades.leerStringOpcional("Nuevo nombre [" + e.getNombre() + "]: ");
            if (!nombre.isEmpty()) e.setNombre(nombre);

            String direccion = Utilidades.leerStringOpcional("Nueva dirección [" + e.getDireccion() + "]: ");
            if (!direccion.isEmpty()) e.setDireccion(direccion);

            String telefono = Utilidades.leerStringOpcional("Nuevo teléfono [" + e.getTelefono() + "]: ");
            if (!telefono.isEmpty()) {
                if (telefono.matches("\\d{9}")) {
                    e.setTelefono(telefono);
                } else {
                    System.out.println("  [!] Teléfono no válido, se mantiene el anterior.");
                }
            }

            LocalDate fechaNac = Utilidades.leerFechaOpcional("Nueva fecha de nacimiento");
            if (fechaNac != null) e.setFechaNacimiento(fechaNac);

            dao.actualizar(e);
            System.out.println("  [OK] Enfermo actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo modificar el enfermo: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    private void eliminar() {
        Utilidades.titulo("ELIMINAR ENFERMO");
        try {
            int id = Utilidades.leerIntPositivo("ID del enfermo a eliminar: ");
            Enfermo e = dao.buscarPorId(id);

            if (e == null) {
                System.out.println("  [!] No se encontró el enfermo con ID " + id);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Se eliminará: " + e);
            if (Utilidades.confirmar("¿Estás seguro?")) {
                dao.eliminar(id);
                System.out.println("  [OK] Enfermo eliminado.");
            } else {
                System.out.println("  Operación cancelada.");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo eliminar el enfermo: " + e.getMessage());
        }
        Utilidades.pausar();
    }
}