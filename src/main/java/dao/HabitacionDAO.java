package dao;
import modelo.Habitacion;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad {@link modelo.Habitacion}.
 * @author Carlos Fernández
 */
public interface HabitacionDAO {

    /**
     * Crea la tabla de habitaciones en la base de datos si no existe.
     */
    void crearTabla();

    /**
     * Inserta una nueva habitación en la base de datos.
     * @param habitacion objeto {@link Habitacion} con los datos a insertar
     */
    void insertar(Habitacion habitacion);

    /**
     * Obtiene la lista de todas las habitaciones registradas.
     * @return lista de habitaciones, vacía si no hay ninguna
     */
    List<Habitacion> listar();

    /**
     * Busca una habitación por su número identificador.
     * @param id número de la habitación
     * @return la {@link Habitacion} encontrada, o {@code null} si no existe
     */
    Habitacion buscarPorId(int id);

    /**
     * Busca todas las habitaciones pertenecientes a una planta concreta.
     * @param idPlanta número de la planta
     * @return lista de habitaciones de esa planta, vacía si no hay ninguna
     */
    List<Habitacion> buscarPorPlanta(int idPlanta);

    /**
     * Actualiza los datos de una habitación existente en la base de datos.
     * @param habitacion objeto {@link Habitacion} con los nuevos datos
     */
    void actualizar(Habitacion habitacion);

    /**
     * Elimina una habitación de la base de datos por su número identificador.
     * @param id número de la habitación a eliminar
     */
    void eliminar(int id);
}
