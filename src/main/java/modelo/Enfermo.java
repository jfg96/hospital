package modelo;

import java.time.LocalDate;
import java.time.Period;

/**
 * Clase que representa a un enfermo en el sistema hospitalario.
 * @author Carlos Fernández
 */
public class Enfermo {
    /**
     * Identificador único del enfermo.
     */
    private int idEnfermo;
    /**
     * Nombre del enfermo.
     */
    private String nombre;
    /**
     * Dirección del enfermo.
     */
    private String direccion;
    /**
     * DNI del enfermo.
     */
    private String dni;
    /**
     * Teléfono del enfermo.
     */
    private String telefono;
    /**
     * Fecha de nacimiento del enfermo.
     */
    private LocalDate fechaNacimiento;

    /**
     * Constructor que inicializa un enfermo con los datos especificados.
     * @param idEnfermo identificador único del enfermo
     * @param nombre nombre del enfermo
     * @param direccion dirección del enfermo
     * @param dni DNI del enfermo
     * @param telefono teléfono del enfermo
     * @param fechaNacimiento fecha de nacimiento del enfermo
     */
    public Enfermo(int idEnfermo, String nombre, String direccion, String dni, String telefono, LocalDate fechaNacimiento) {
        this.idEnfermo = idEnfermo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.dni = dni;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el identificador del enfermo.
     * @return identificador del enfermo
     */
    public int getIdEnfermo() {
        return idEnfermo;
    }

    /**
     * Establece el identificador del enfermo.
     * @param idEnfermo nuevo identificador del enfermo
     */
    public void setIdEnfermo(int idEnfermo) {
        this.idEnfermo = idEnfermo;
    }

    /**
     * Obtiene el nombre del enfermo.
     * @return nombre del enfermo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del enfermo.
     * @param nombre nuevo nombre del enfermo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección del enfermo.
     * @return dirección del enfermo
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del enfermo.
     * @param direccion nueva dirección del enfermo
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el DNI del enfermo.
     * @return DNI del enfermo
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del enfermo.
     * @param dni nuevo DNI del enfermo
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el teléfono del enfermo.
     * @return teléfono del enfermo
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del enfermo.
     * @param telefono nuevo teléfono del enfermo
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la fecha de nacimiento del enfermo.
     * @return fecha de nacimiento del enfermo
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del enfermo.
     * @param fechaNacimiento nueva fecha de nacimiento del enfermo
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Calcula y obtiene la edad del enfermo en años.
     * @return edad del enfermo en años
     */
    public int getEdad() {
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }

    /**
     * Retorna una representación en String del enfermo.
     * @return cadena con los datos del enfermo
     */
    @Override
    public String toString() {
        return "Enfermo{" +
                "idEnfermo=" + idEnfermo +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento=" + fechaNacimiento + "(Edad)=" + getEdad() +
                '}';
    }
}
