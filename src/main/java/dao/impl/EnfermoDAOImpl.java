package dao.impl;

import conexion.ConexionBD;
import dao.EnfermoDAO;
import modelo.Enfermo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de EnfermoDAO para SQLite.
 */
public class EnfermoDAOImpl implements EnfermoDAO {

    @Override
    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS enfermos (
                    idEnfermo        INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre           TEXT NOT NULL,
                    direccion        TEXT,
                    dni              TEXT NOT NULL UNIQUE,
                    telefono         TEXT,
                    fechaNacimiento  TEXT NOT NULL
                )""";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al crear tabla enfermos: " + e.getMessage());
        }
    }

    @Override
    public void insertar(Enfermo enfermo) {
        String sql = "INSERT INTO enfermos (nombre, direccion, dni, telefono, fechaNacimiento) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, enfermo.getNombre());
            ps.setString(2, enfermo.getDireccion());
            ps.setString(3, enfermo.getDni());
            ps.setString(4, enfermo.getTelefono());
            ps.setString(5, enfermo.getFechaNacimiento().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar enfermo: " + e.getMessage());
        }
    }

    @Override
    public List<Enfermo> listar() {
        List<Enfermo> lista = new ArrayList<>();
        String sql = "SELECT * FROM enfermos";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            System.err.println("Error al listar enfermos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Enfermo buscarPorId(int id) {
        String sql = "SELECT * FROM enfermos WHERE idEnfermo = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar enfermo por id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Enfermo buscarPorDni(String dni) {
        String sql = "SELECT * FROM enfermos WHERE dni = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar enfermo por DNI: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Enfermo> buscarPorNombre(String nombre) {
        List<Enfermo> lista = new ArrayList<>();
        String sql = "SELECT * FROM enfermos WHERE nombre LIKE ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar enfermos por nombre: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Enfermo enfermo) {
        String sql = "UPDATE enfermos SET nombre = ?, direccion = ?, telefono = ?, fechaNacimiento = ? WHERE dni = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, enfermo.getNombre());
            ps.setString(2, enfermo.getDireccion());
            ps.setString(3, enfermo.getTelefono());
            ps.setString(4, enfermo.getFechaNacimiento().toString());
            ps.setString(5, enfermo.getDni());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar enfermo: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM enfermos WHERE idEnfermo = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar enfermo: " + e.getMessage());
        }
    }

    private Enfermo mapear(ResultSet rs) throws SQLException {
        return new Enfermo(
                rs.getInt("idEnfermo"),
                rs.getString("nombre"),
                rs.getString("direccion"),
                rs.getString("dni"),
                rs.getString("telefono"),
                LocalDate.parse(rs.getString("fechaNacimiento"))
        );
    }
}

