package dao.impl;

import conexion.ConexionBD;
import dao.AuxiliarDAO;
import modelo.Auxiliar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de AuxiliarDAO para SQLite.
 */
public class AuxiliarDAOImpl implements AuxiliarDAO {

    @Override
    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS auxiliares (
                    id       INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre   TEXT NOT NULL,
                    direccion TEXT,
                    telefono TEXT,
                    dni      TEXT NOT NULL UNIQUE,
                    sueldo   REAL NOT NULL,
                    idPlanta INTEGER NOT NULL,
                    FOREIGN KEY (idPlanta) REFERENCES plantas(numeroPlanta)
                )""";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al crear tabla auxiliares: " + e.getMessage());
        }
    }

    @Override
    public void insertar(Auxiliar auxiliar) {
        String sql = "INSERT INTO auxiliares (nombre, direccion, telefono, dni, sueldo, idPlanta) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, auxiliar.getNombre());
            ps.setString(2, auxiliar.getDireccion());
            ps.setString(3, auxiliar.getTelefono());
            ps.setString(4, auxiliar.getDni());
            ps.setDouble(5, auxiliar.getSueldo());
            ps.setInt(6, auxiliar.getIdPlanta());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar auxiliar: " + e.getMessage());
        }
    }

    @Override
    public List<Auxiliar> listar() {
        List<Auxiliar> lista = new ArrayList<>();
        String sql = "SELECT * FROM auxiliares";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            System.err.println("Error al listar auxiliares: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Auxiliar buscarPorId(int id) {
        String sql = "SELECT * FROM auxiliares WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar auxiliar por id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Auxiliar buscarPorDni(String dni) {
        String sql = "SELECT * FROM auxiliares WHERE dni = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar auxiliar por DNI: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Auxiliar> buscarPorNombre(String nombre) {
        List<Auxiliar> lista = new ArrayList<>();
        String sql = "SELECT * FROM auxiliares WHERE nombre LIKE ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar auxiliares por nombre: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Auxiliar> buscarPorPlanta(int idPlanta) {
        List<Auxiliar> lista = new ArrayList<>();
        String sql = "SELECT * FROM auxiliares WHERE idPlanta = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPlanta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar auxiliares por planta: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Auxiliar auxiliar) {
        String sql = "UPDATE auxiliares SET nombre = ?, direccion = ?, telefono = ?, sueldo = ?, idPlanta = ? WHERE dni = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, auxiliar.getNombre());
            ps.setString(2, auxiliar.getDireccion());
            ps.setString(3, auxiliar.getTelefono());
            ps.setDouble(4, auxiliar.getSueldo());
            ps.setInt(5, auxiliar.getIdPlanta());
            ps.setString(6, auxiliar.getDni());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar auxiliar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM auxiliares WHERE id = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar auxiliar: " + e.getMessage());
        }
    }

    private Auxiliar mapear(ResultSet rs) throws SQLException {
        Auxiliar a = new Auxiliar(
                rs.getString("nombre"),
                rs.getString("direccion"),
                rs.getString("telefono"),
                rs.getString("dni"),
                rs.getDouble("sueldo"),
                rs.getInt("idPlanta")
        );
        a.setIdTrabajador(rs.getInt("id"));
        return a;
    }
}

