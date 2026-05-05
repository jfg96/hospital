package dao;
import modelo.Enfermo;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad {@link modelo.Enfermo}.
 * @author Carlos Fernández
 */
public interface EnfermoDAO {

    /**
     * Crea la tabla de enfermos en la base de datos si no existe.
     */
    void crearTabla();

    /**
     * Inserta un nuevo enfermo en la base de datos.
     * @param enfermo objeto {@link Enfermo} con los datos a insertar
     */
    void insertar(Enfermo enfermo);

    /**
     * Obtiene la lista de todos los enfermos registrados.
     * @return lista de enfermos, vacía si no hay ninguno
     */
    List<Enfermo> listar();

    /**
     * Busca un enfermo por su identificador único.
     * @param id identificador del enfermo
     * @return el {@link Enfermo} encontrado, o {@code null} si no existe
     */
    Enfermo buscarPorId(int id);

    /**
     * Busca un enfermo por su DNI.
     * @param dni DNI del enfermo
     * @return el {@link Enfermo} encontrado, o {@code null} si no existe
     */
    Enfermo buscarPorDni(String dni);

    /**
     * Busca enfermos cuyo nombre contenga el texto indicado (búsqueda parcial).
     * @param nombre texto a buscar dentro del nombre
     * @return lista de enfermos que coinciden, vacía si no hay resultados
     */
    List<Enfermo> buscarPorNombre(String nombre);

    /**
     * Actualiza los datos de un enfermo existente en la base de datos.
     * @param enfermo objeto {@link Enfermo} con los nuevos datos (se identifica por DNI)
     */
    void actualizar(Enfermo enfermo);

    /**
     * Elimina un enfermo de la base de datos por su identificador.
     * @param id identificador del enfermo a eliminar
     */
    void eliminar(int id);
}
