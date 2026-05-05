package dao;
import modelo.Hospitalizacion;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad {@link modelo.Hospitalizacion}.
 * @author Carlos Fernández
 */
public interface HospitalizacionDAO {

    /**
     * Crea la tabla de hospitalizaciones en la base de datos si no existe.
     */
    void crearTabla();

    /**
     * Inserta una nueva hospitalización en la base de datos.
     * @param hospitalizacion objeto {@link Hospitalizacion} con los datos a insertar
     */
    void insertar(Hospitalizacion hospitalizacion);

    /**
     * Obtiene la lista de todas las hospitalizaciones registradas.
     * @return lista de hospitalizaciones, vacía si no hay ninguna
     */
    List<Hospitalizacion> listar();

    /**
     * Busca una hospitalización por su identificador único.
     * @param id identificador de la hospitalización
     * @return la {@link Hospitalizacion} encontrada, o {@code null} si no existe
     */
    Hospitalizacion buscarPorId(int id);

    /**
     * Obtiene la lista de hospitalizaciones activas (sin fecha de alta registrada).
     * @return lista de hospitalizaciones activas, vacía si no hay ninguna
     */
    List<Hospitalizacion> listarActivas();

    /**
     * Busca todas las hospitalizaciones de un enfermo concreto.
     * @param idEnfermo identificador del enfermo
     * @return lista de hospitalizaciones del enfermo, vacía si no hay ninguna
     */
    List<Hospitalizacion> buscarPorEnfermo(int idEnfermo);

    /**
     * Busca todas las hospitalizaciones registradas en una habitación concreta.
     * @param idHabitacion número de la habitación
     * @return lista de hospitalizaciones de esa habitación, vacía si no hay ninguna
     */
    List<Hospitalizacion> buscarPorHabitacion(int idHabitacion);

    /**
     * Registra la fecha de alta de una hospitalización activa.
     * @param id identificador de la hospitalización
     * @param fechaAlta fecha de alta en formato {@code yyyy-MM-dd}
     */
    void registrarAlta(int id, String fechaAlta);

    /**
     * Actualiza los datos de una hospitalización existente en la base de datos.
     * @param hospitalizacion objeto {@link Hospitalizacion} con los nuevos datos
     */
    void actualizar(Hospitalizacion hospitalizacion);

    /**
     * Elimina una hospitalización de la base de datos por su identificador.
     * @param id identificador de la hospitalización a eliminar
     */
    void eliminar(int id);
}
