package app.Menus;

import app.Utilidades;
import dao.AuxiliarDAO;
import dao.impl.AuxiliarDAOImpl;
import modelo.Auxiliar;

import java.util.List;

/**
 * Submenú de consola para la gestión de auxiliares de enfermería.
 * @author Antonio Manuel Rodriguez Palenzuela
 */
public class MenuAuxiliares {

    private final AuxiliarDAO dao = new AuxiliarDAOImpl();

    /**
     * Muestra el submenú de auxiliares y gestiona la opción seleccionada
     * por el usuario hasta que éste elija volver al menú principal.
     */
    public void mostrar() {
        int opcion;
        do {
            Utilidades.titulo("GESTIÓN DE AUXILIARES");
            System.out.println("  1. Añadir auxiliar");
            System.out.println("  2. Listar todos los auxiliares");
            System.out.println("  3. Buscar auxiliar");
            System.out.println("  4. Modificar auxiliar");
            System.out.println("  5. Eliminar auxiliar");
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
     * Solicita los datos de un nuevo auxiliar por consola y lo inserta
     * en la base de datos.
     */
    private void añadir() {
        Utilidades.titulo("NUEVO AUXILIAR");
        try {
            String nombre    = Utilidades.leerString("Nombre: ");
            String direccion = Utilidades.leerString("Dirección: ");
            String telefono  = Utilidades.leerTelefono("Teléfono: ");
            String dni       = Utilidades.leerDni("DNI: ");
            double sueldo    = Utilidades.leerDoublePositivo("Sueldo: ");
            int idPlanta     = Utilidades.leerIntPositivo("Número de planta asignada: ");

            Auxiliar auxiliar = new Auxiliar(nombre, direccion, telefono, dni, sueldo, idPlanta);
            dao.insertar(auxiliar);
            System.out.println("  [OK] Auxiliar registrado correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo registrar el auxiliar: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Obtiene todos los auxiliares de la base de datos y los muestra por consola.
     */
    private void listar() {
        Utilidades.titulo("LISTADO DE AUXILIARES");
        try {
            List<Auxiliar> lista = dao.listar();
            if (lista.isEmpty()) {
                System.out.println("  No hay auxiliares registrados.");
            } else {
                for (Auxiliar a : lista) {
                    System.out.println("  [ID " + a.getIdTrabajador() + "] " + a);
                }
                System.out.println("\n  Total: " + lista.size() + " auxiliar(es).");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo obtener el listado: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Solicita un criterio de búsqueda (ID, DNI, nombre o planta asignada)
     * y muestra los auxiliares que coinciden.
     */
    private void buscar() {
        Utilidades.titulo("BUSCAR AUXILIAR");
        System.out.println("  1. Por ID");
        System.out.println("  2. Por DNI");
        System.out.println("  3. Por nombre");
        System.out.println("  4. Por planta asignada");
        int criterio = Utilidades.leerInt("Selecciona criterio: ");

        try {
            switch (criterio) {
                case 1 -> {
                    int id = Utilidades.leerIntPositivo("ID del auxiliar: ");
                    Auxiliar a = dao.buscarPorId(id);
                    if (a != null) System.out.println("  " + a);
                    else System.out.println("  No se encontró el auxiliar con ID " + id);
                }
                case 2 -> {
                    String dni = Utilidades.leerDni("DNI: ");
                    Auxiliar a = dao.buscarPorDni(dni);
                    if (a != null) System.out.println("  " + a);
                    else System.out.println("  No se encontró el auxiliar con DNI " + dni);
                }
                case 3 -> {
                    String nombre = Utilidades.leerString("Nombre a buscar: ");
                    List<Auxiliar> resultados = dao.buscarPorNombre(nombre);
                    if (resultados.isEmpty()) {
                        System.out.println("  No se encontraron resultados.");
                    } else {
                        resultados.forEach(a -> System.out.println("  " + a));
                    }
                }
                case 4 -> {
                    int planta = Utilidades.leerIntPositivo("Número de planta: ");
                    List<Auxiliar> resultados = dao.buscarPorPlanta(planta);
                    if (resultados.isEmpty()) {
                        System.out.println("  No hay auxiliares en la planta " + planta);
                    } else {
                        resultados.forEach(a -> System.out.println("  " + a));
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
     * Busca un auxiliar por su ID, muestra sus datos actuales y permite
     * modificar los campos que el usuario desee.
     */
    private void modificar() {
        Utilidades.titulo("MODIFICAR AUXILIAR");
        try {
            int id = Utilidades.leerIntPositivo("ID del auxiliar a modificar: ");
            Auxiliar a = dao.buscarPorId(id);

            if (a == null) {
                System.out.println("  [!] No se encontró el auxiliar con ID " + id);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Datos actuales: " + a);
            System.out.println("  (Deja el campo vacío para mantener el valor actual)\n");

            String nombre = Utilidades.leerStringOpcional("Nuevo nombre [" + a.getNombre() + "]: ");
            if (!nombre.isEmpty()) a.setNombre(nombre);

            String direccion = Utilidades.leerStringOpcional("Nueva dirección [" + a.getDireccion() + "]: ");
            if (!direccion.isEmpty()) a.setDireccion(direccion);

            String telefono = Utilidades.leerStringOpcional("Nuevo teléfono [" + a.getTelefono() + "]: ");
            if (!telefono.isEmpty()) {
                if (telefono.matches("\\d{9}")) {
                    a.setTelefono(telefono);
                } else {
                    System.out.println("  [!] Teléfono no válido, se mantiene el anterior.");
                }
            }

            String sueldoStr = Utilidades.leerStringOpcional("Nuevo sueldo [" + a.getSueldo() + "]: ");
            if (!sueldoStr.isEmpty()) {
                try {
                    double nuevoSueldo = Double.parseDouble(sueldoStr);
                    if (nuevoSueldo > 0) a.setSueldo(nuevoSueldo);
                    else System.out.println("  [!] Sueldo no válido, se mantiene el anterior.");
                } catch (NumberFormatException ex) {
                    System.out.println("  [!] Formato no válido, se mantiene el sueldo anterior.");
                }
            }

            String plantaStr = Utilidades.leerStringOpcional("Nueva planta [" + a.getIdPlanta() + "]: ");
            if (!plantaStr.isEmpty()) {
                try {
                    int nuevaPlanta = Integer.parseInt(plantaStr);
                    if (nuevaPlanta > 0) a.setIdPlanta(nuevaPlanta);
                    else System.out.println("  [!] Planta no válida, se mantiene la anterior.");
                } catch (NumberFormatException ex) {
                    System.out.println("  [!] Formato no válido, se mantiene la planta anterior.");
                }
            }

            dao.actualizar(a);
            System.out.println("  [OK] Auxiliar actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo modificar el auxiliar: " + e.getMessage());
        }
        Utilidades.pausar();
    }

    /**
     * Busca un auxiliar por su ID y, tras confirmación, lo elimina de la
     * base de datos.
     */
    private void eliminar() {
        Utilidades.titulo("ELIMINAR AUXILIAR");
        try {
            int id = Utilidades.leerIntPositivo("ID del auxiliar a eliminar: ");
            Auxiliar a = dao.buscarPorId(id);

            if (a == null) {
                System.out.println("  [!] No se encontró el auxiliar con ID " + id);
                Utilidades.pausar();
                return;
            }

            System.out.println("  Se eliminará: " + a);
            if (Utilidades.confirmar("¿Estás seguro?")) {
                dao.eliminar(id);
                System.out.println("  [OK] Auxiliar eliminado.");
            } else {
                System.out.println("  Operación cancelada.");
            }
        } catch (Exception e) {
            System.out.println("  [ERROR] No se pudo eliminar el auxiliar: " + e.getMessage());
        }
        Utilidades.pausar();
    }
}
