package modelo;

/**
 * Clase abstracta que representa a un trabajador en el sistema hospitalario.
 * @author Carlos Fernández
 */
public abstract class Trabajador {
    /**
     * Nombre del trabajador.
     */
    private String nombre;
    /**
     * Dirección del trabajador.
     */
    private String direccion;
    /**
     * Teléfono del trabajador.
     */
    private String telefono;
    /**
     * DNI del trabajador.
     */
    private String dni;
    /**
     * Sueldo del trabajador.
     */
    private double sueldo;
    /**
     * Identificador único del trabajador (generado por la BD).
     */
    private int idTrabajador;

    /**
     * Constructor que inicializa un trabajador con los datos especificados.
     * @param nombre nombre del trabajador
     * @param direccion dirección del trabajador
     * @param telefono teléfono del trabajador
     * @param dni DNI del trabajador
     * @param sueldo sueldo del trabajador
     */
    public Trabajador(String nombre, String direccion, String telefono, String dni, double sueldo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.dni = dni;
        this.sueldo = sueldo;
    }

    /**
     * Obtiene el nombre del trabajador.
     * @return nombre del trabajador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del trabajador.
     * @param nombre nuevo nombre del trabajador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección del trabajador.
     * @return dirección del trabajador
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del trabajador.
     * @param direccion nueva dirección del trabajador
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el teléfono del trabajador.
     * @return teléfono del trabajador
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del trabajador.
     * @param telefono nuevo teléfono del trabajador
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el DNI del trabajador.
     * @return DNI del trabajador
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del trabajador.
     * @param dni nuevo DNI del trabajador
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el sueldo del trabajador.
     * @return sueldo del trabajador
     */
    public double getSueldo() {
        return sueldo;
    }

    /**
     * Establece el sueldo del trabajador.
     * @param sueldo nuevo sueldo del trabajador
     */
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * Obtiene el id único del trabajador (generado por la BD).
     * @return
     */
    public int getIdTrabajador() {
        return idTrabajador;
    }
    /**
     * Establece el identificador único del trabajador.
     * NOTA: Este método está pensado para ser utilizado exclusivamente por las clases DAO
     * tras realizar un INSERT, para sincronizar el objeto en memoria con el ID
     * autogenerado (AUTOINCREMENT) por la base de datos SQLite.
     *
     * @param idTrabajador el ID generado por la base de datos.
     */
    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    /**
     * Retorna una representación en String del trabajador.
     * @return cadena con los datos del trabajador
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {" +
                "DNI='" + dni + '\'' +
                ", Nombre='" + nombre + '\'' +
                ", Sueldo=" + sueldo +
                ", Teléfono='" + telefono + '\'' +
                ", Dirección='" + direccion + '\'';
    }
}
