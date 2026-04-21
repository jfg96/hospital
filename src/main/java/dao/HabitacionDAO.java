package dao;

import modelo.Habitacion;

import java.util.List;

public interface HabitacionDAO {
    void insertar(Habitacion habitacion);

    List<Habitacion> listar();

    Habitacion buscarPorId(int id);

    List<Habitacion> buscarPorPlanta(int idPlanta);

    void actualizar(Habitacion habitacion);

    void eliminar(int id);
}
