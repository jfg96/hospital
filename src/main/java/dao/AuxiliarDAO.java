package dao;

import modelo.Auxiliar;

import java.util.List;

public interface AuxiliarDAO {
    void insertar(Auxiliar auxiliar);

    List<Auxiliar> listar();

    Auxiliar buscarPorId(int id);

    Auxiliar buscarPorDni(String dni);

    List<Auxiliar> buscarPorNombre(String nombre);

    List<Auxiliar> buscarPorPlanta(int idPlanta);

    void actualizar(Auxiliar auxiliar);

    void eliminar(int id);
}
