package modelo;

import java.time.LocalDate;

/**
 * Clase que representa un diagnóstico emitido por un médico a un enfermo.
 * @author Carlos Fernández
 */
public class Diagnostico {
    /**
     * Identificador único del diagnóstico.
     */
    private int codigo;
    /**
     * DNI del médico que emite el diagnóstico.
     */
    private String dniMedico;
    /**
     * Identificador del enfermo al que se emite el diagnóstico.
     */
    private int idEnfermo;
    /**
     * Fecha en que se emite el diagnóstico.
     */
    private LocalDate fecha;
    /**
     * Informe detallado del diagnóstico.
     */
    private String informe;

    /**
     * Constructor que inicializa un diagnóstico con los datos especificados.
     * @param codigo identificador único del diagnóstico
     * @param dniMedico DNI del médico que emite el diagnóstico
     * @param idEnfermo identificador del enfermo al que se emite el diagnóstico
     * @param fecha fecha en que se emite el diagnóstico
     * @param informe informe detallado del diagnóstico
     */
    public Diagnostico(int codigo, String dniMedico, int idEnfermo, LocalDate fecha, String informe) {
        this.codigo = codigo;
        this.dniMedico = dniMedico;
        this.idEnfermo = idEnfermo;
        this.fecha = fecha;
        this.informe = informe;
    }

    /**
     * Obtiene el identificador del diagnóstico.
     * @return identificador del diagnóstico
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el DNI del médico que emite el diagnóstico.
     * @return DNI del médico
     */
    public String getDniMedico() {
        return dniMedico;
    }

    /**
     * Obtiene el identificador del enfermo al que se emite el diagnóstico.
     * @return identificador del enfermo
     */
    public int getIdEnfermo() {
        return idEnfermo;
    }

    /**
     * Obtiene la fecha en que se emite el diagnóstico.
     * @return fecha del diagnóstico
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Obtiene el informe detallado del diagnóstico.
     * @return informe del diagnóstico
     */
    public String getInforme() {
        return informe;
    }

    /**
     * Establece el informe detallado del diagnóstico.
     * @param informe nuevo informe del diagnóstico
     */
    public void setInforme(String informe) {
        this.informe = informe;
    }

    /**
     * Retorna una representación en String del diagnóstico.
     * @return cadena con los datos del diagnóstico
     */
    @Override
    public String toString() {
        return "Diagnostico{" + "id=" + codigo + ", medico='" + dniMedico + '\'' +
                ", informe='" + (informe.length() > 20 ? informe.substring(0, 20) + "..." : informe) + "'}";
    }
}