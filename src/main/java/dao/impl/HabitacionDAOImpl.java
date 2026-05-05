package dao.impl;

import conexion.ConexionBD;
import dao.HabitacionDAO;
import modelo.Habitacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de {@link HabitacionDAO} para SQLite.
 *
 * @author Carlos Fernández
 */
public class HabitacionDAOImpl implements HabitacionDAO {

    /**
     * {@inheritDoc}
     */
    @Override
    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS habitaciones (
                    numeroHabitacion INTEGER PRIMARY KEY,
                    numeroPlanta     INTEGER NOT NULL,
                    cantidadCamas    INTEGER NOT NULL,
                    observaciones    TEXT,
                    FOREIGN KEY (numeroPlanta) REFERENCES plantas(numeroPlanta)
                )""";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al crear tabla habitaciones: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertar(Habitacion habitacion) {
        String sql = "INSERT INTO habitaciones (numeroHabitacion, numeroPlanta, cantidadCamas, observaciones) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, habitacion.getNumeroHabitacion());
            ps.setInt(2, habitacion.getNumeroPlanta());
            ps.setInt(3, habitacion.getCantidadCamas());
            ps.setString(4, habitacion.getObservaciones());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar habitación: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Habitacion> listar() {
        List<Habitacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM habitaciones";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            System.err.println("Error al listar habitaciones: " + e.getMessage());
        }
        return lista;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Habitacion buscarPorId(int id) {
        String sql = "SELECT * FROM habitaciones WHERE numeroHabitacion = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar habitación por id: " + e.getMessage());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Habitacion> buscarPorPlanta(int idPlanta) {
        List<Habitacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM habitaciones WHERE numeroPlanta = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPlanta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar habitaciones por planta: " + e.getMessage());
        }
        return lista;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizar(Habitacion habitacion) {
        String sql = "UPDATE habitaciones SET numeroPlanta = ?, cantidadCamas = ?, observaciones = ? WHERE numeroHabitacion = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, habitacion.getNumeroPlanta());
            ps.setInt(2, habitacion.getCantidadCamas());
            ps.setString(3, habitacion.getObservaciones());
            ps.setInt(4, habitacion.getNumeroHabitacion());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar habitación: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM habitaciones WHERE numeroHabitacion = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar habitación: " + e.getMessage());
        }
    }

    /**
     * Convierte una fila del {@link ResultSet} en un objeto {@link Habitacion}.
     *
     * @param rs conjunto de resultados posicionado en la fila a mapear
     * @return objeto {@link Habitacion} con los datos de la fila
     * @throws SQLException si ocurre un error al leer el ResultSet
     */
    private Habitacion mapear(ResultSet rs) throws SQLException {
        return new Habitacion(
                rs.getInt("numeroHabitacion"),
                rs.getInt("numeroPlanta"),
                rs.getInt("cantidadCamas"),
                rs.getString("observaciones")
        );
    }
}
