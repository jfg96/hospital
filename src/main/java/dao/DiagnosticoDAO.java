package dao;
import modelo.Diagnostico;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad {@link modelo.Diagnostico}.
 * @author Carlos Fernández
 */
public interface DiagnosticoDAO {

    /**
     * Crea la tabla de diagnósticos en la base de datos si no existe.
     */
    void crearTabla();

    /**
     * Inserta un nuevo diagnóstico en la base de datos.
     * @param diagnostico objeto {@link Diagnostico} con los datos a insertar
     */
    void insertar(Diagnostico diagnostico);

    /**
     * Obtiene la lista de todos los diagnósticos registrados.
     * @return lista de diagnósticos, vacía si no hay ninguno
     */
    List<Diagnostico> listar();

    /**
     * Busca un diagnóstico por su código identificador.
     * @param id código del diagnóstico
     * @return el {@link Diagnostico} encontrado, o {@code null} si no existe
     */
    Diagnostico buscarPorId(int id);

    /**
     * Busca todos los diagnósticos emitidos a un enfermo concreto.
     * @param idEnfermo identificador del enfermo
     * @return lista de diagnósticos del enfermo, vacía si no hay ninguno
     */
    List<Diagnostico> buscarPorEnfermo(int idEnfermo);

    /**
     * Busca todos los diagnósticos emitidos por un médico concreto.
     * @param idMedico identificador del médico
     * @return lista de diagnósticos emitidos por ese médico, vacía si no hay ninguno
     */
    List<Diagnostico> buscarPorMedico(int idMedico);

    /**
     * Busca todos los diagnósticos emitidos en una fecha concreta.
     * @param fecha fecha en formato {@code yyyy-MM-dd}
     * @return lista de diagnósticos de esa fecha, vacía si no hay ninguno
     */
    List<Diagnostico> buscarPorFecha(String fecha);

    /**
     * Actualiza los datos de un diagnóstico existente en la base de datos.
     * @param diagnostico objeto {@link Diagnostico} con los nuevos datos
     */
    void actualizar(Diagnostico diagnostico);

    /**
     * Elimina un diagnóstico de la base de datos por su código identificador.
     * @param id código del diagnóstico a eliminar
     */
    void eliminar(int id);
}
