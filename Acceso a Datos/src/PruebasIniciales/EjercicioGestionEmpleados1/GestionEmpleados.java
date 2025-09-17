package PruebasIniciales.EjercicioGestionEmpleados1;

import java.util.ArrayList;
import java.util.Collections;

public class GestionEmpleados {
    private final ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    public void agregarEmpleado(Empleado empleado) {
        listaEmpleados.add(empleado);
    }

    public void ordenarPorDni() {
        Collections.sort(listaEmpleados);
    }

    public void mostrarEmpleados() {
        for (Empleado empleado : listaEmpleados) System.out.println(empleado);
    }
}
