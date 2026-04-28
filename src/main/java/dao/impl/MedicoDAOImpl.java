package dao.impl;

import conexion.ConexionBD;
import dao.MedicoDAO;
import modelo.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de MedicoDAO para SQLite.
 */
public class MedicoDAOImpl implements MedicoDAO {

    @Override
    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS medicos (
                    id          INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre      TEXT NOT NULL,
                    direccion   TEXT,
                    telefono    TEXT,
                    dni         TEXT NOT NULL UNIQUE,
                    sueldo      REAL NOT NULL,
                    especialidad TEXT NOT NULL
                )""";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al crear tabla medicos: " + e.getMessage());
        }
    }

    @Override
    public void insertar(Medico medico) {
        String sql = "INSERT INTO medicos (nombre, direccion, telefono, dni, sueldo, especialidad) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, medico.getNombre());
            ps.setString(2, medico.getDireccion());
            ps.setString(3, medico.getTelefono());
            ps.setString(4, medico.getDni());
            ps.setDouble(5, medico.getSueldo());
            ps.setString(6, medico.getEspecialidad());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar médico: " + e.getMessage());
        }
    }

    @Override
    public List<Medico> listar() {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicos";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            System.err.println("Error al listar médicos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Medico buscarPorId(int id) {
        String sql = "SELECT * FROM medicos WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar médico por id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Medico buscarPorDni(String dni) {
        String sql = "SELECT * FROM medicos WHERE dni = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar médico por DNI: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Medico> buscarPorNombre(String nombre) {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicos WHERE nombre LIKE ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar médicos por nombre: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Medico> buscarPorEspecialidad(String especialidad) {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicos WHERE especialidad LIKE ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + especialidad + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar médicos por especialidad: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Medico medico) {
        String sql = "UPDATE medicos SET nombre = ?, direccion = ?, telefono = ?, sueldo = ?, especialidad = ? WHERE dni = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, medico.getNombre());
            ps.setString(2, medico.getDireccion());
            ps.setString(3, medico.getTelefono());
            ps.setDouble(4, medico.getSueldo());
            ps.setString(5, medico.getEspecialidad());
            ps.setString(6, medico.getDni());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar médico: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM medicos WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar médico: " + e.getMessage());
        }
    }

    private Medico mapear(ResultSet rs) throws SQLException {
        Medico m = new Medico(
                rs.getString("nombre"),
                rs.getString("direccion"),
                rs.getString("telefono"),
                rs.getString("dni"),
                rs.getDouble("sueldo"),
                rs.getString("especialidad")
        );
        m.setIdTrabajador(rs.getInt("id"));
        return m;
    }
}

