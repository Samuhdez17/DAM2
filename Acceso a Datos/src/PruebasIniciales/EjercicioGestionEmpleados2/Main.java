package PruebasIniciales.EjercicioGestionEmpleados2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        GestionEmpleados gestion = new GestionEmpleados();

        gestion.agregarEmpleado(new Empleado("12345679a", "Pedro", "López", 5558765, 2800));
        gestion.agregarEmpleado(new Empleado("12345678b", "Ana", "Garcia", 5555678, 2500));
        gestion.agregarEmpleado(new Empleado("12345678a", "Juan", "Pérez", 5551234, 3000));

        char opcion = ' ';

        System.out.println("Listado de empleados sin ordenar:");
        gestion.mostrarEmpleados();

        do {
            System.out.print("\n\nComo quieres organizar los empleados? (dni = d ; nombre = n ; apellido = a ; telf = t ; salario = s ; q = salir): ");
            opcion = entrada.next().toUpperCase().charAt(0);
            gestion.asignarComparacion(opcion);
            System.out.println();

            if (opcion != 'Q') {
                gestion.ordenarEmpleados();
                System.out.printf("Empleados ordenados por %s\n", opcion == 'D' ? "DNI" : opcion == 'N' ? "nombre" :  opcion == 'A' ? "apellido" : "salario");
                gestion.mostrarEmpleados();
            }
        } while (opcion != 'Q');

        System.out.println("Saliendo del programa....");
    }
}
