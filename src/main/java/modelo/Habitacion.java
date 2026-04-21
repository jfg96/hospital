package modelo;

/**
 * Clase que representa una habitación física en una planta determinada.
 * @author Carlos Fernández
 */
public class Habitacion {
    /**
     * Número de la habitación.
     */
    private int numeroHabitacion;
    /**
     * Número de la planta en la que se encuentra la habitación.
     */
    private int numeroPlanta;
    /**
     * Cantidad de camas disponibles en la habitación.
     */
    private int cantidadCamas;
    /**
     * Observaciones o notas adicionales sobre la habitación.
     */
    private String observaciones;

    /**
     * Constructor que inicializa una habitación con los datos especificados.
     * @param numeroHabitacion número de la habitación
     * @param numeroPlanta número de la planta
     * @param cantidadCamas cantidad de camas en la habitación
     * @param observaciones observaciones sobre la habitación
     */
    public Habitacion(int numeroHabitacion, int numeroPlanta, int cantidadCamas, String observaciones) {
        this.numeroHabitacion = numeroHabitacion;
        this.numeroPlanta = numeroPlanta;
        this.cantidadCamas = cantidadCamas;
        this.observaciones = observaciones;
    }

    /**
     * Obtiene el número de la habitación.
     * @return número de la habitación
     */
    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    /**
     * Obtiene el número de la planta.
     * @return número de la planta
     */
    public int getNumeroPlanta() {
        return numeroPlanta;
    }

    /**
     * Obtiene la cantidad de camas en la habitación.
     * @return cantidad de camas
     */
    public int getCantidadCamas() {
        return cantidadCamas;
    }

    /**
     * Establece la cantidad de camas en la habitación.
     * @param cantidadCamas nueva cantidad de camas
     */
    public void setCantidadCamas(int cantidadCamas) {
        this.cantidadCamas = cantidadCamas;
    }

    /**
     * Obtiene las observaciones sobre la habitación.
     * @return observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece las observaciones sobre la habitación.
     * @param observaciones nuevas observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Retorna una representación en String de la habitación.
     * @return cadena con los datos de la habitación
     */
    @Override
    public String toString() {
        return "Habitacion{" + "nº=" + numeroHabitacion + ", planta=" + numeroPlanta +
                ", camas=" + cantidadCamas + '}';
    }
}