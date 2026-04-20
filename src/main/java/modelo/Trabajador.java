package modelo;

public abstract class Trabajador {
    private String nombre;
    private String direccion;
    private String telefono;
    private String DNI;
    private double sueldo;

    public Trabajador(String nombre, String direccion, String telefono, String DNI, double sueldo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.DNI = DNI;
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", DNI='" + DNI + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }
}
