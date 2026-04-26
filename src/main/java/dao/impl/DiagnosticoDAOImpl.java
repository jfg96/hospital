package dao.impl;

import conexion.ConexionBD;
import dao.DiagnosticoDAO;
import modelo.Diagnostico;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de DiagnosticoDAO para SQLite.
 */
public class DiagnosticoDAOImpl implements DiagnosticoDAO {

    @Override
    public void crearTabla() {
        String sql = """
                CREATE TABLE IF NOT EXISTS diagnosticos (
                    codigo     INTEGER PRIMARY KEY AUTOINCREMENT,
                    dniMedico  TEXT NOT NULL,
                    idEnfermo  INTEGER NOT NULL,
                    fecha      TEXT NOT NULL,
                    informe    TEXT,
                    FOREIGN KEY (idEnfermo) REFERENCES enfermos(idEnfermo)
                )""";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al crear tabla diagnosticos: " + e.getMessage());
        }
    }

    @Override
    public void insertar(Diagnostico diagnostico) {
        String sql = "INSERT INTO diagnosticos (dniMedico, idEnfermo, fecha, informe) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, diagnostico.getDniMedico());
            ps.setInt(2, diagnostico.getIdEnfermo());
            ps.setString(3, diagnostico.getFecha().toString());
            ps.setString(4, diagnostico.getInforme());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar diagnóstico: " + e.getMessage());
        }
    }

    @Override
    public List<Diagnostico> listar() {
        List<Diagnostico> lista = new ArrayList<>();
        String sql = "SELECT * FROM diagnosticos";
        try (Connection con = ConexionBD.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            System.err.println("Error al listar diagnósticos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Diagnostico buscarPorId(int id) {
        String sql = "SELECT * FROM diagnosticos WHERE codigo = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar diagnóstico por id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Diagnostico> buscarPorEnfermo(int idEnfermo) {
        List<Diagnostico> lista = new ArrayList<>();
        String sql = "SELECT * FROM diagnosticos WHERE idEnfermo = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEnfermo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar diagnósticos por enfermo: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Diagnostico> buscarPorMedico(int idMedico) {
        List<Diagnostico> lista = new ArrayList<>();
        // Buscamos el DNI del médico a partir de su id y luego filtramos diagnósticos
        String sql = """
                SELECT d.* FROM diagnosticos d
                JOIN medicos m ON d.dniMedico = m.dni
                WHERE m.id = ?""";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMedico);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar diagnósticos por médico: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Diagnostico> buscarPorFecha(String fecha) {
        List<Diagnostico> lista = new ArrayList<>();
        String sql = "SELECT * FROM diagnosticos WHERE fecha = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fecha);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar diagnósticos por fecha: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Diagnostico diagnostico) {
        String sql = "UPDATE diagnosticos SET dniMedico = ?, idEnfermo = ?, fecha = ?, informe = ? WHERE codigo = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, diagnostico.getDniMedico());
            ps.setInt(2, diagnostico.getIdEnfermo());
            ps.setString(3, diagnostico.getFecha().toString());
            ps.setString(4, diagnostico.getInforme());
            ps.setInt(5, diagnostico.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar diagnóstico: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM diagnosticos WHERE codigo = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar diagnóstico: " + e.getMessage());
        }
    }

    private Diagnostico mapear(ResultSet rs) throws SQLException {
        return new Diagnostico(
                rs.getInt("codigo"),
                rs.getString("dniMedico"),
                rs.getInt("idEnfermo"),
                LocalDate.parse(rs.getString("fecha")),
                rs.getString("informe")
        );
    }
}

