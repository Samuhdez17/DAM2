package PruebasIniciales.EjercicioGestionEmpleados2;

public class Empleado implements Comparable<Empleado> {
    private final String dni;
    private final String nombre;
    private final String apellido;
    private final float telefono;
    private final double salario;
    private char comparacion;

    public Empleado(String dni, String nombre, String apellido, float telefono, double salario) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.salario = salario;
    }

    public void setComparacion(char comparacion) {
        this.comparacion = comparacion;
    }

    public int compareTo(Empleado otro) {
        switch (comparacion) {
            case 'D' -> {return ComparadorCadenas.compararDni     (this.dni, otro.dni);           }
            case 'N' -> {return ComparadorCadenas.compararNombre  (this.nombre, otro.nombre);     }
            case 'A' -> {return ComparadorCadenas.compararApellido(this.apellido, otro.apellido); }
            case 'T' -> {return ComparadorCadenas.compararTelefono(this.telefono, otro.telefono); }
            case 'S' -> {return ComparadorCadenas.compararSalario (this.salario, otro.salario);   }

            default -> {return 0;}
        }
    }

    public String toString() {
        return String.format("Empleado  [DNI=%s, Nombre=%s, Apellido=%s, Telefono=%f, Salario=%.2f]", dni, nombre, apellido, telefono, salario);
    }
}
