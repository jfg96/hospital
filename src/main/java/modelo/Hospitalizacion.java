package modelo;

import java.time.LocalDate;

/**
 * Clase que registra la estancia de un enfermo en una habitación del hospital.
 * @author Carlos Fernández
 */
public class Hospitalizacion {
    /**
     * Identificador único de la hospitalización.
     */
    private int idHospitalizacion;
    /**
     * Identificador del enfermo hospitalizado.
     */
    private int idEnfermo;
    /**
     * Número de la habitación donde se hospeda el enfermo.
     */
    private int numeroHabitacion;
    /**
     * Número de la planta donde se encuentra la habitación.
     */
    private int numeroPlanta;
    /**
     * Fecha de ingreso del enfermo.
     */
    private LocalDate fechaIngreso;
    /**
     * Fecha de alta del enfermo. Puede ser null si sigue ingresado.
     */
    private LocalDate fechaAlta;

    /**
     * Constructor que inicializa una hospitalización con los datos especificados.
     * @param idEnfermo identificador del enfermo
     * @param numeroHabitacion número de la habitación
     * @param numeroPlanta número de la planta
     * @param fechaIngreso fecha de ingreso
     */
    public Hospitalizacion(int idEnfermo, int numeroHabitacion, int numeroPlanta, LocalDate fechaIngreso) {
        this.idEnfermo = idEnfermo;
        this.numeroHabitacion = numeroHabitacion;
        this.numeroPlanta = numeroPlanta;
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * Obtiene el identificador de la hospitalización.
     * @return identificador de la hospitalización
     */
    public int getIdHospitalizacion() {
        return idHospitalizacion;
    }

    /**
     * Establece el identificador de la hospitalización.
     * @param id nuevo identificador de la hospitalización
     */
    public void setIdHospitalizacion(int id) {
        this.idHospitalizacion = id;
    }

    /**
     * Obtiene la fecha de alta del enfermo.
     * @return fecha de alta o null si sigue ingresado
     */
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Establece la fecha de alta del enfermo.
     * @param fechaAlta nueva fecha de alta
     */
    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Obtiene el identificador del enfermo hospitalizado.
     * @return identificador del enfermo
     */
    public int getIdEnfermo() {
        return idEnfermo;
    }

    /**
     * Obtiene el número de la habitación.
     * @return número de la habitación
     */
    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    /**
     * Retorna una representación en String de la hospitalización.
     * @return cadena con los datos de la hospitalización
     */
    @Override
    public String toString() {
        return "Hospitalizacion{" + "paciente=" + idEnfermo + ", hab=" + numeroHabitacion +
                ", ingreso=" + fechaIngreso + ", alta=" + (fechaAlta == null ? "ACTIVA" : fechaAlta) + '}';
    }
}