package PruebasIniciales.EjercicioGestionEmpleados;

public class Empleado implements Comparable<Empleado> {
    private final String dni;
    private final String nombre;
    private final String apellido;
    private final float telefono;
    private final double salario;

    public Empleado(String dni, String nombre, String apellido, float telefono, double salario) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.salario = salario;
    }

    public int compareTo(Empleado otro) {
        return ComparadorCadenas.compararDni(this.dni, otro.dni);
    }

    public String toString() {
        return String.format("Empleado  [DNI=%s, Nombre=%s, Apellido=%s, Telefono=%f, Salario=%.2f]", dni, nombre, apellido, telefono, salario);
    }
}
