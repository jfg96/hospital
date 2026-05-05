package dao;
import modelo.Planta;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad {@link modelo.Planta}.
 * @author Carlos Fernández
 */
public interface PlantaDAO {

    /**
     * Crea la tabla de plantas en la base de datos si no existe.
     */
    void crearTabla();

    /**
     * Inserta una nueva planta en la base de datos.
     * @param planta objeto {@link Planta} con los datos a insertar
     */
    void insertar(Planta planta);

    /**
     * Obtiene la lista de todas las plantas registradas.
     * @return lista de plantas, vacía si no hay ninguna
     */
    List<Planta> listar();

    /**
     * Busca una planta por su número identificador.
     * @param id número de la planta
     * @return la {@link Planta} encontrada, o {@code null} si no existe
     */
    Planta buscarPorId(int id);

    /**
     * Busca plantas cuya especialidad contenga el texto indicado (búsqueda parcial).
     * @param especialidad texto a buscar dentro de la especialidad
     * @return lista de plantas que coinciden, vacía si no hay resultados
     */
    List<Planta> buscarPorEspecialidad(String especialidad);

    /**
     * Actualiza los datos de una planta existente en la base de datos.
     * @param planta objeto {@link Planta} con los nuevos datos
     */
    void actualizar(Planta planta);

    /**
     * Elimina una planta de la base de datos por su número identificador.
     * @param id número de la planta a eliminar
     */
    void eliminar(int id);
}
