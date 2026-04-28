package dao;
import modelo.Hospitalizacion;
import java.util.List;

public interface HospitalizacionDAO {
    void crearTabla();
    void insertar(Hospitalizacion hospitalizacion);
    List<Hospitalizacion> listar();
    Hospitalizacion buscarPorId(int id);
    List<Hospitalizacion> listarActivas();
    List<Hospitalizacion> buscarPorEnfermo(int idEnfermo);
    List<Hospitalizacion> buscarPorHabitacion(int idHabitacion);
    void registrarAlta(int id, String fechaAlta);
    void actualizar(Hospitalizacion hospitalizacion);
    void eliminar(int id);
}
