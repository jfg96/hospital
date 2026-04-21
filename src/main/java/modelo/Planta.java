package modelo;

/**
 * Clase que representa una planta del hospital.
 * @author Carlos Fernández
 */
public class Planta {
    /**
     * Número de la planta.
     */
    private int numeroPlanta;
    /**
     * Cantidad total de habitaciones en la planta.
     */
    private int cantidadHabitaciones;
    /**
     * Especialidad médica asignada a la planta.
     */
    private String especialidad;

    /**
     * Constructor que inicializa una planta con los datos especificados.
     * @param numeroPlanta número de la planta
     * @param cantidadHabitaciones cantidad de habitaciones
     * @param especialidad especialidad médica de la planta
     */
    public Planta(int numeroPlanta, int cantidadHabitaciones, String especialidad) {
        this.numeroPlanta = numeroPlanta;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.especialidad = especialidad;
    }

    /**
     * Obtiene el número de la planta.
     * @return número de la planta
     */
    public int getNumeroPlanta() {
        return numeroPlanta;
    }

    /**
     * Establece el número de la planta.
     * @param numeroPlanta nuevo número de la planta
     */
    public void setNumeroPlanta(int numeroPlanta) {
        this.numeroPlanta = numeroPlanta;
    }

    /**
     * Obtiene la cantidad de habitaciones de la planta.
     * @return cantidad de habitaciones
     */
    public int getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    /**
     * Establece la cantidad de habitaciones de la planta.
     * @param cantidadHabitaciones nueva cantidad de habitaciones
     */
    public void setCantidadHabitaciones(int cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    /**
     * Obtiene la especialidad médica de la planta.
     * @return especialidad médica
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad médica de la planta.
     * @param especialidad nueva especialidad médica
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Retorna una representación en String de la planta.
     * @return cadena con los datos de la planta
     */
    @Override
    public String toString() {
        return "Planta{" + "nº=" + numeroPlanta + ", especialidad='" + especialidad + '\'' +
                ", habitaciones=" + cantidadHabitaciones + '}';
    }
