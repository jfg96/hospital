package dao;
import modelo.Auxiliar;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad {@link modelo.Auxiliar}.
 * @author Carlos Fernández
 */
public interface AuxiliarDAO {

    /**
     * Crea la tabla de auxiliares en la base de datos si no existe.
     */
    void crearTabla();

    /**
     * Inserta un nuevo auxiliar en la base de datos.
     * @param auxiliar objeto {@link Auxiliar} con los datos a insertar
     */
    void insertar(Auxiliar auxiliar);

    /**
     * Obtiene la lista de todos los auxiliares registrados.
     * @return lista de auxiliares, vacía si no hay ninguno
     */
    List<Auxiliar> listar();

    /**
     * Busca un auxiliar por su identificador único.
     * @param id identificador del auxiliar
     * @return el {@link Auxiliar} encontrado, o {@code null} si no existe
     */
    Auxiliar buscarPorId(int id);

    /**
     * Busca un auxiliar por su DNI.
     * @param dni DNI del auxiliar
     * @return el {@link Auxiliar} encontrado, o {@code null} si no existe
     */
    Auxiliar buscarPorDni(String dni);

    /**
     * Busca auxiliares cuyo nombre contenga el texto indicado (búsqueda parcial).
     * @param nombre texto a buscar dentro del nombre
     * @return lista de auxiliares que coinciden, vacía si no hay resultados
     */
    List<Auxiliar> buscarPorNombre(String nombre);

    /**
     * Busca todos los auxiliares asignados a una planta concreta.
     * @param idPlanta número de la planta
     * @return lista de auxiliares de esa planta, vacía si no hay ninguno
     */
    List<Auxiliar> buscarPorPlanta(int idPlanta);

    /**
     * Actualiza los datos de un auxiliar existente en la base de datos.
     * @param auxiliar objeto {@link Auxiliar} con los nuevos datos (se identifica por DNI)
     */
    void actualizar(Auxiliar auxiliar);

    /**
     * Elimina un auxiliar de la base de datos por su identificador.
     * @param id identificador del auxiliar a eliminar
     */
    void eliminar(int id);
}
