package PruebasIniciales.EjercicioGestionEmpleados2;

import java.util.ArrayList;
import java.util.Collections;

public class GestionEmpleados {
    private final ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    public void agregarEmpleado(Empleado empleado) {
        listaEmpleados.add(empleado);
    }

    public void asignarComparacion(char tipo) {
        for (Empleado empleado : listaEmpleados) {
           empleado.setComparacion(tipo);
        }
    }

    public void ordenarEmpleados() {
        Collections.sort(listaEmpleados);
    }

    public void mostrarEmpleados() {
        for (Empleado empleado : listaEmpleados) System.out.println(empleado);
    }
}
