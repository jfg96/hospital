package conexion;
import java.sql.*;

/**
 * Clase que gestiona la conexión con la base de datos SQLite del hospital.
 * Utiliza el patrón Singleton para mantener una única conexión a la base de datos.
 * 
 * @author Hospital System
 * @version 1.0
 */
public class ConexionBD {
    /** URL de conexión a la base de datos SQLite */
    private static final String URL = "jdbc:sqlite:hospital.db";
    
    /** Instancia única de la conexión a la base de datos */
    private static Connection conexion = null;

    /**
     * Constructor privado para evitar la instantiación de la clase.
     * Esta clase utiliza métodos estáticos para mantener una única conexión.
     */
    private ConexionBD(){}

    /**
     * Obtiene la conexión a la base de datos. Si no existe una conexión activa,
     * crea una nueva. Implementa el patrón Singleton para la gestión de conexiones.
     * 
     * @return la conexión activa a la base de datos
     * @throws SQLException si ocurre un error al establecer la conexión
     */
    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL);
            System.out.println("Conexión a la base de datos establecida.");
        }
        return conexion;
    }

    /**
     * Cierra la conexión con la base de datos si está abierta.
     * Este método debe llamarse al finalizar la aplicación para liberar recursos.
     */
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al intentar cerrar la conexión: " + e.getMessage());
        }
    }

}
