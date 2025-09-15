package PruebasIniciales.EjercicioGestionEmpleados;

public class Main {
    public static void main(String[] args) {
        GestionEmpleados gestion = new GestionEmpleados();

        gestion.agregarEmpleado(new Empleado("12345679a", "Pedro", "López", 555-8765, 2800));
        gestion.agregarEmpleado(new Empleado("12345678b", "Ana", "Garcia", 555-5678, 2500));
        gestion.agregarEmpleado(new Empleado("12345678a", "Juan", "Pérez", 555-1234, 3000));

        System.out.println("Empleados antes de ordenar:");
        gestion.mostrarEmpleados();

        gestion.ordenarPorDni();
        System.out.println("\nEmpleados después de ordenar por DNI:");
        gestion.mostrarEmpleados();
    }
}
