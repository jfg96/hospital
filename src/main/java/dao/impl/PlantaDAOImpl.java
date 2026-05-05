package dao.impl;

import conexion.ConexionBD;
import dao.PlantaDAO;
import modelo.Planta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de {@link PlantaDAO} para SQLite.
 *
 * @author Carlos Fernández
 */
public class PlantaDAOImpl implements PlantaDAO {

    /**
     * {@inheritDoc}
     */
    @Override
    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS plantas (
                    numeroPlanta       INTEGER PRIMARY KEY,
                    cantidadHabitaciones INTEGER NOT NULL,
                    especialidad       TEXT NOT NULL
                )""";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al crear tabla plantas: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertar(Planta planta) {
        String sql = "INSERT INTO plantas (numeroPlanta, cantidadHabitaciones, especialidad) VALUES (?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, planta.getNumeroPlanta());
            ps.setInt(2, planta.getCantidadHabitaciones());
            ps.setString(3, planta.getEspecialidad());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar planta: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Planta> listar() {
        List<Planta> lista = new ArrayList<>();
        String sql = "SELECT * FROM plantas";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar plantas: " + e.getMessage());
        }
        return lista;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Planta buscarPorId(int id) {
        String sql = "SELECT * FROM plantas WHERE numeroPlanta = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar planta por id: " + e.getMessage());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Planta> buscarPorEspecialidad(String especialidad) {
        List<Planta> lista = new ArrayList<>();
        String sql = "SELECT * FROM plantas WHERE especialidad LIKE ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + especialidad + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar plantas por especialidad: " + e.getMessage());
        }
        return lista;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizar(Planta planta) {
        String sql = "UPDATE plantas SET cantidadHabitaciones = ?, especialidad = ? WHERE numeroPlanta = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, planta.getCantidadHabitaciones());
            ps.setString(2, planta.getEspecialidad());
            ps.setInt(3, planta.getNumeroPlanta());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar planta: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM plantas WHERE numeroPlanta = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar planta: " + e.getMessage());
        }
    }

    /**
     * Convierte una fila del {@link ResultSet} en un objeto {@link Planta}.
     *
     * @param rs conjunto de resultados posicionado en la fila a mapear
     * @return objeto {@link Planta} con los datos de la fila
     * @throws SQLException si ocurre un error al leer el ResultSet
     */
    private Planta mapear(ResultSet rs) throws SQLException {
        return new Planta(
                rs.getInt("numeroPlanta"),
                rs.getInt("cantidadHabitaciones"),
                rs.getString("especialidad")
        );
    }
}
