package dao;
import modelo.Diagnostico;
import java.util.List;

public interface DiagnosticoDAO {
    void crearTabla();
    void insertar(Diagnostico diagnostico);
    List<Diagnostico> listar();
    Diagnostico buscarPorId(int id);
    List<Diagnostico> buscarPorEnfermo(int idEnfermo);
    List<Diagnostico> buscarPorMedico(int idMedico);
    List<Diagnostico> buscarPorFecha(String fecha);
    void actualizar(Diagnostico diagnostico);
    void eliminar(int id);
}
