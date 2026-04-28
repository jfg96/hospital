package dao.impl;

import conexion.ConexionBD;
import dao.HospitalizacionDAO;
import modelo.Hospitalizacion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de HospitalizacionDAO para SQLite.
 */
public class HospitalizacionDAOImpl implements HospitalizacionDAO {

    @Override
    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS hospitalizaciones (
                    idHospitalizacion INTEGER PRIMARY KEY AUTOINCREMENT,
                    idEnfermo         INTEGER NOT NULL,
                    numeroHabitacion  INTEGER NOT NULL,
                    numeroPlanta      INTEGER NOT NULL,
                    fechaIngreso      TEXT NOT NULL,
                    fechaAlta         TEXT,
                    FOREIGN KEY (idEnfermo)        REFERENCES enfermos(idEnfermo),
                    FOREIGN KEY (numeroHabitacion) REFERENCES habitaciones(numeroHabitacion)
                )""";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al crear tabla hospitalizaciones: " + e.getMessage());
        }
    }

    @Override
    public void insertar(Hospitalizacion hospitalizacion) {
        String sql = "INSERT INTO hospitalizaciones (idEnfermo, numeroHabitacion, numeroPlanta, fechaIngreso, fechaAlta) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, hospitalizacion.getIdEnfermo());
            ps.setInt(2, hospitalizacion.getNumeroHabitacion());
            ps.setInt(3, hospitalizacion.getNumeroHabitacion()); // se usa numeroHabitacion por limitación del modelo
            ps.setString(4, LocalDate.now().toString());
            ps.setString(5, hospitalizacion.getFechaAlta() != null ? hospitalizacion.getFechaAlta().toString() : null);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) hospitalizacion.setIdHospitalizacion(keys.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar hospitalización: " + e.getMessage());
        }
    }

    @Override
    public List<Hospitalizacion> listar() {
        List<Hospitalizacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM hospitalizaciones";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            System.err.println("Error al listar hospitalizaciones: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Hospitalizacion buscarPorId(int id) {
        String sql = "SELECT * FROM hospitalizaciones WHERE idHospitalizacion = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar hospitalización por id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Hospitalizacion> listarActivas() {
        List<Hospitalizacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM hospitalizaciones WHERE fechaAlta IS NULL";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            System.err.println("Error al listar hospitalizaciones activas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Hospitalizacion> buscarPorEnfermo(int idEnfermo) {
        List<Hospitalizacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM hospitalizaciones WHERE idEnfermo = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEnfermo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar hospitalizaciones por enfermo: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Hospitalizacion> buscarPorHabitacion(int idHabitacion) {
        List<Hospitalizacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM hospitalizaciones WHERE numeroHabitacion = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHabitacion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar hospitalizaciones por habitación: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void registrarAlta(int id, String fechaAlta) {
        String sql = "UPDATE hospitalizaciones SET fechaAlta = ? WHERE idHospitalizacion = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fechaAlta);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al registrar alta: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Hospitalizacion hospitalizacion) {
        String sql = "UPDATE hospitalizaciones SET idEnfermo = ?, numeroHabitacion = ?, numeroPlanta = ?, fechaAlta = ? WHERE idHospitalizacion = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, hospitalizacion.getIdEnfermo());
            ps.setInt(2, hospitalizacion.getNumeroHabitacion());
            ps.setInt(3, hospitalizacion.getNumeroHabitacion());
            ps.setString(4, hospitalizacion.getFechaAlta() != null ? hospitalizacion.getFechaAlta().toString() : null);
            ps.setInt(5, hospitalizacion.getIdHospitalizacion());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar hospitalización: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM hospitalizaciones WHERE idHospitalizacion = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar hospitalización: " + e.getMessage());
        }
    }

    private Hospitalizacion mapear(ResultSet rs) throws SQLException {
        Hospitalizacion h = new Hospitalizacion(
                rs.getInt("idEnfermo"),
                rs.getInt("numeroHabitacion"),
                rs.getInt("numeroPlanta"),
                LocalDate.parse(rs.getString("fechaIngreso"))
        );
        h.setIdHospitalizacion(rs.getInt("idHospitalizacion"));
        String alta = rs.getString("fechaAlta");
        if (alta != null) h.setFechaAlta(LocalDate.parse(alta));
        return h;
    }
}

