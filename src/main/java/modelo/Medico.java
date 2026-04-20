package modelo;

/**
 * Clase que representa a un médico en el sistema hospitalario.
 * @author Carlos Fernández
 */
public class Medico extends Trabajador {
    /**
     * Especialidad médica del médico.
     */
    private String especialidad;

    /**
     * Constructor que inicializa un médico con los datos especificados.
     * @param nombre nombre del médico
     * @param direccion dirección del médico
     * @param telefono teléfono del médico
     * @param dni DNI del médico
     * @param sueldo sueldo del médico
     * @param especialidad especialidad médica del médico
     */
    public Medico(String nombre, String direccion, String telefono, String dni, double sueldo, String especialidad) {
        super(nombre, direccion, telefono, dni, sueldo);
        this.especialidad = especialidad;
    }

    /**
     * Obtiene la especialidad médica del médico.
     * @return especialidad del médico
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad médica del médico.
     * @param especialidad nueva especialidad del médico
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Retorna una representación en String del médico.
     * @return cadena con los datos del médico
     */
    @Override
    public String toString() {
        return super.toString() +
                ", Especialidad='" + especialidad + "'}";
    }
}
