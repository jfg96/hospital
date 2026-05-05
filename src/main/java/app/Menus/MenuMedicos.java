package app.Menus;

import app.Utilidades;
import dao.MedicoDAO;
import dao.impl.MedicoDAOImpl;
import modelo.Medico;

import java.util.List;

/**
 * Submenú de consola para la gestión de médicos.
 * @author Antonio Manuel Rodriguez Palenzuela
 */
public class MenuMedicos {

    private final MedicoDAO dao = new MedicoDAOImpl();

    /**
     * Muestra el submenú de médicos y gestiona la opción seleccionada
     * por el usuario hasta que éste elija volver al menú principal.
     */
    public void mostrar() {
        int opcion;
        do {
            Utilidades.titulo("GESTIÓN DE MÉDICOS");
            System.out.println("  1. Añadir médico");
            System.out.println("  2. Listar todos los médicos");
            System.out.println("  3. Buscar médico");
            System.out.println("  4. Modificar médico");
            System.out.println("  5. Eliminar médico");
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
     * Solicita los datos de un nuevo médico por consola y lo inserta
     * en la base de datos.
     */
    private void añadir() {
        Utilidades.titulo("NUEVO MÉDICO");
        try {
            String nombre       = Utilidades.leerString("Nombre: ");
            String direccion    = Utilidades.leerString("Dirección: ");
            String telefono     = Utilidades.leerTelefono("Teléfono: ");
            String dni          = Utilidades.leerDni("DNI: ");
            double sueldo       = Utilidades.leerDoublePositivo("Sueldo: ");
            String especialidad = Utilidades.leerString("Especialidad: ");

            Medico medico = new Medico(nombre, direccion, telefono, dni, sueldo, especialidad);
            dao.insertar(medico);
            System.out.println("  [OK] Médico registrado correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo registrar el médico: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Obtiene todos los médicos de la base de datos y los muestra por consola.
     */
    private void listar() {
        Utilidades.titulo("LISTADO DE MÉDICOS");
        try {
            List<Medico> lista = dao.listar();
            if (lista.isEmpty()) {
                System.out.println("  No hay médicos registrados.");
            } else {
                for (Medico m : lista) {
                    System.out.println("  [ID " + m.getIdTrabajador() + "] " + m);
                }
                System.out.println("\n  Total: " + lista.size() + " médico(s).");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo obtener el listado: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Solicita un criterio de búsqueda (ID, DNI, nombre o especialidad)
     * y muestra los médicos que coinciden.
     */
    private void buscar() {
        Utilidades.titulo("BUSCAR MÉDICO");
        System.out.println("  1. Por ID");
        System.out.println("  2. Por DNI");
        System.out.println("  3. Por nombre");
        System.out.println("  4. Por especialidad");
        int criterio = Utilidades.leerInt("Selecciona criterio: ");

        try {
            switch (criterio) {
                case 1 -> {
                    int id = Utilidades.leerIntPositivo("ID del médico: ");
                    Medico m = dao.buscarPorId(id);
                    if (m != null) System.out.println("  " + m);
                    else System.out.println("  No se encontró el médico con ID " + id);
                }
                case 2 -> {
                    String dni = Utilidades.leerDni("DNI: ");
                    Medico m = dao.buscarPorDni(dni);
                    if (m != null) System.out.println("  " + m);
                    else System.out.println("  No se encontró el médico con DNI " + dni);
                }
                case 3 -> {
                    String nombre = Utilidades.leerString("Nombre a buscar: ");
                    List<Medico> resultados = dao.buscarPorNombre(nombre);
                    if (resultados.isEmpty()) {
                        System.out.println("  No se encontraron resultados.");
                    } else {
                        resultados.forEach(m -> System.out.println("  " + m));
                    }
                }
                case 4 -> {
                    String esp = Utilidades.leerString("Especialidad: ");
                    List<Medico> resultados = dao.buscarPorEspecialidad(esp);
                    if (resultados.isEmpty()) {
                        System.out.println("  No se encontraron resultados.");
                    } else {
                        resultados.forEach(m -> System.out.println("  " + m));
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
     * Busca un médico por su ID, muestra sus datos actuales y permite
     * modificar los campos que el usuario desee.
     */
    private void modificar() {
        Utilidades.titulo("MODIFICAR MÉDICO");
        try {
            int id = Utilidades.leerIntPositivo("ID del médico a modificar: ");
            Medico m = dao.buscarPorId(id);

            if (m == null) {
                System.out.println("  [!] No se encontró el médico con ID " + id);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Datos actuales: " + m);
            System.out.println("  (Deja el campo vacío para mantener el valor actual)\n");

            String nombre = Utilidades.leerStringOpcional("Nuevo nombre [" + m.getNombre() + "]: ");
            if (!nombre.isEmpty()) m.setNombre(nombre);

            String direccion = Utilidades.leerStringOpcional("Nueva dirección [" + m.getDireccion() + "]: ");
            if (!direccion.isEmpty()) m.setDireccion(direccion);

            String telefono = Utilidades.leerStringOpcional("Nuevo teléfono [" + m.getTelefono() + "]: ");
            if (!telefono.isEmpty()) {
                if (telefono.matches("\\d{9}")) {
                    m.setTelefono(telefono);
                } else {
                    System.out.println("  [!] Teléfono no válido, se mantiene el anterior.");
                }
            }

            String sueldoStr = Utilidades.leerStringOpcional("Nuevo sueldo [" + m.getSueldo() + "]: ");
            if (!sueldoStr.isEmpty()) {
                try {
                    double nuevoSueldo = Double.parseDouble(sueldoStr);
                    if (nuevoSueldo > 0) m.setSueldo(nuevoSueldo);
                    else System.out.println("  [!] Sueldo no válido, se mantiene el anterior.");
                } catch (NumberFormatException ex) {
                    System.out.println("  [!] Formato no válido, se mantiene el sueldo anterior.");
                }
            }

            String especialidad = Utilidades.leerStringOpcional("Nueva especialidad [" + m.getEspecialidad() + "]: ");
            if (!especialidad.isEmpty()) m.setEspecialidad(especialidad);

            dao.actualizar(m);
            System.out.println("  [OK] Médico actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo modificar el médico: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Busca un médico por su ID y, tras confirmación, lo elimina de la
     * base de datos.
     */
    private void eliminar() {
        Utilidades.titulo("ELIMINAR MÉDICO");
        try {
            int id = Utilidades.leerIntPositivo("ID del médico a eliminar: ");
            Medico m = dao.buscarPorId(id);

            if (m == null) {
                System.out.println("  [!] No se encontró el médico con ID " + id);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Se eliminará: " + m);
            if (Utilidades.confirmar("¿Estás seguro?")) {
                dao.eliminar(id);
                System.out.println("  [OK] Médico eliminado.");
            } else {
                System.out.println("  Operación cancelada.");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo eliminar el médico: " + e.getMessage());
        }
        Utilidades.pausar();
    }
}
