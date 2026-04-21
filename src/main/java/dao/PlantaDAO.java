package dao;

import modelo.Planta;

import java.util.List;

public interface PlantaDAO {
    void insertar(Planta planta);

    List<Planta> listar();

    Planta buscarPorId(int id);

    List<Planta> buscarPorEspecialidad(String especialidad);

    void actualizar(Planta planta);

    void eliminar(int id);
}
