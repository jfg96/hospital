package dao;

import modelo.Enfermo;

import java.util.List;

public interface EnfermoDAO {
    void insertar(Enfermo enfermo);

    List<Enfermo> listar();

    Enfermo buscarPorId(int id);

    Enfermo buscarPorDni(String dni);

    List<Enfermo> buscarPorNombre(String nombre);

    void actualizar(Enfermo enfermo);

    void eliminar(int id);
}
