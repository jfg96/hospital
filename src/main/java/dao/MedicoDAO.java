package dao;
import modelo.Medico;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad {@link modelo.Medico}.
 * @author Carlos Fernández
 */
public interface MedicoDAO {

    /**
     * Crea la tabla de médicos en la base de datos si no existe.
     */
    void crearTabla();

    /**
     * Inserta un nuevo médico en la base de datos.
     * @param medico objeto {@link Medico} con los datos a insertar
     */
    void insertar(Medico medico);

    /**
     * Obtiene la lista de todos los médicos registrados.
     * @return lista de médicos, vacía si no hay ninguno
     */
    List<Medico> listar();

    /**
     * Busca un médico por su identificador único.
     * @param id identificador del médico
     * @return el {@link Medico} encontrado, o {@code null} si no existe
     */
    Medico buscarPorId(int id);

    /**
     * Busca un médico por su DNI.
     * @param dni DNI del médico
     * @return el {@link Medico} encontrado, o {@code null} si no existe
     */
    Medico buscarPorDni(String dni);

    /**
     * Busca médicos cuyo nombre contenga el texto indicado (búsqueda parcial).
     * @param nombre texto a buscar dentro del nombre
     * @return lista de médicos que coinciden, vacía si no hay resultados
     */
    List<Medico> buscarPorNombre(String nombre);

    /**
     * Busca médicos cuya especialidad contenga el texto indicado (búsqueda parcial).
     * @param especialidad texto a buscar dentro de la especialidad
     * @return lista de médicos que coinciden, vacía si no hay resultados
     */
    List<Medico> buscarPorEspecialidad(String especialidad);

    /**
     * Actualiza los datos de un médico existente en la base de datos.
     * @param medico objeto {@link Medico} con los nuevos datos (se identifica por DNI)
     */
    void actualizar(Medico medico);

    /**
     * Elimina un médico de la base de datos por su identificador.
     * @param id identificador del médico a eliminar
     */
    void eliminar(int id);
}
