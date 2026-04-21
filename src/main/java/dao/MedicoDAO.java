package dao;

import modelo.Medico;

import java.util.List;

public interface MedicoDAO {
    void insertar(Medico medico);

    List<Medico> listar();

    Medico buscarPorId(int id);

    Medico buscarPorDni(String dni);

    List<Medico> buscarPorNombre(String nombre);

    List<Medico> buscarPorEspecialidad(String especialidad);

    void actualizar(Medico medico);

    void eliminar(int id);
}
