package modelo;

/**
 * Clase que representa a un auxiliar en el sistema hospitalario.
 * @author Carlos Fernández
 */
public class Auxiliar extends Trabajador{
    /**
     * Identificador de la planta en la que trabaja el auxiliar.
     */
    private int idPlanta;

    /**
     * Constructor que inicializa un auxiliar con los datos especificados.
     * @param nombre nombre del auxiliar
     * @param direccion dirección del auxiliar
     * @param telefono teléfono del auxiliar
     * @param DNI DNI del auxiliar
     * @param sueldo sueldo del auxiliar
     * @param idPlanta identificador de la planta del auxiliar
     */
    public Auxiliar(String nombre, String direccion, String telefono, String DNI, double sueldo, int idPlanta) {
        super(nombre, direccion, telefono, DNI, sueldo);
        this.idPlanta = idPlanta;
    }

    /**
     * Obtiene el identificador de la planta del auxiliar.
     * @return identificador de la planta
     */
    public int getIdPlanta() {
        return idPlanta;
    }

    /**
     * Establece el identificador de la planta del auxiliar.
     * @param idPlanta nuevo identificador de la planta
     */
    public void setIdPlanta(int idPlanta) {
        this.idPlanta = idPlanta;
    }

    /**
     * Retorna una representación en String del auxiliar.
     * @return cadena con los datos del auxiliar
     */
    @Override
    public String toString() {
        return super.toString() +
                ", Planta=" + idPlanta + "}";
    }
}
