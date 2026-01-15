package org.example;

import entidades.Departamento;
import entidades.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.example.dao.EmpleadoDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("greentech");
        EntityManager em = emf.createEntityManager();

        Scanner entrada = new Scanner(System.in);
        EmpleadoDAO empDAO = new EmpleadoDAO(em);

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("""
                    1. Insertar empleado
                    2. Listar empleados por departamento
                    3. Modificar salario de un empleado
                    4. Eliminar empleado
                    0. Salir
                    """);

            opcion = entrada.nextInt();

            switch (opcion) {
                case 1 -> insertarEmpleado(entrada, em, empDAO);

                case 2 -> listarEmpleadosPorDept(entrada, em, empDAO);

                case 3 -> actualizarSalario(entrada, em, empDAO);

                case 4 -> eliminarEmpleado(entrada, em, empDAO);

                case 0 -> System.out.println("Saliendo...");
            }
        }
    }

    private static void insertarEmpleado(Scanner entrada, EntityManager em, EmpleadoDAO empDAO) {
        System.out.println("Indica el nombre del empleado:");
        entrada.nextLine();
        String nombre = entrada.nextLine();

        System.out.println("Indica el puesto del empleado:");
        String puesto = entrada.nextLine();

        System.out.println("Indica el salario del empleado:");
        BigDecimal salario = entrada.nextBigDecimal();

        System.out.println("Indica el nombre del departamento del empleado:");
        entrada.nextLine();
        String nombreDepartamento = entrada.nextLine();

        Departamento departamento = verificarDepartamento(em, nombreDepartamento);
        if (departamento == null) {
            System.out.println("El departamento no existe");
            return;
        }

        try {
            empDAO.insertarEmpleado(new Empleado(nombre, puesto, salario, departamento));

        } catch (Exception e) {
            System.err.println("Error al insertar el empleado " + e);
            return;
        }

        System.out.println("Empleado insertado correctamente");
    }

    private static void listarEmpleadosPorDept(Scanner entrada, EntityManager em, EmpleadoDAO empDAO) {
        System.out.println("Indica el nombre del departamento:");
        entrada.nextLine();
        String nombreDepartamento = entrada.nextLine();

        Departamento departamento = verificarDepartamento(em, nombreDepartamento);

        if (departamento == null) {
            System.out.println("El departamento no existe");
            return;
        }

        List<Empleado> empleados = empDAO.listarPorDepartamento(departamento.getId());
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados en el departamento " + nombreDepartamento);
            return;
        }

        System.out.println("    EMPLEADOS DEL DEPARTAMENTO " + nombreDepartamento.toUpperCase());
        for (Empleado empleado : empleados)
            System.out.printf("Nombre: %s; Puesto: %s; Salario: %.2f\n", empleado.getNombre(), empleado.getPuesto(), empleado.getSalario());

        System.out.println();
    }

    private static void actualizarSalario(Scanner entrada, EntityManager em, EmpleadoDAO empDAO) {
        Empleado empleado = pedirEmpleado(entrada, em);
        if (empleado == null) return;

        System.out.println("Indica el nuevo salario:");
        BigDecimal nuevoSalario = entrada.nextBigDecimal();

        try {
            empDAO.actualizarSalario(empleado.getId(), nuevoSalario);

        } catch (Exception e) {
            System.err.println("Error al actualizar el salario " + e);
            return;
        }

        System.out.println("Salario actualizado correctamente");
    }

    private static void eliminarEmpleado(Scanner entrada, EntityManager em, EmpleadoDAO empDAO) {
        Empleado empleado = pedirEmpleado(entrada, em);
        if (empleado == null) return;

        try {
            empDAO.eliminarEmpleado(empleado.getId());

        } catch (Exception e) {
            System.err.println("Error al eliminar el empleado " + e);
            return;
        }

        System.out.println("Empleado eliminado correctamente");
    }

    private static Departamento verificarDepartamento(EntityManager em, String nombreDepartamento ) {
        Query buscarDepartamento = em.createQuery("SELECT d FROM Departamento d WHERE d.nombre LIKE CONCAT('%', :nombre)");
        buscarDepartamento.setParameter("nombre", nombreDepartamento);

        Departamento departamento;
        try {
            departamento = (Departamento) buscarDepartamento.getSingleResult();

        } catch (Exception e) {
            return null;
        }

        return departamento;
    }

    private static Empleado verificarEmpleado(EntityManager em, String nombre) {
        Query buscarEmpleado = em.createQuery("SELECT e FROM Empleado e WHERE e.nombre = :nombre");
        buscarEmpleado.setParameter("nombre", nombre);

        Empleado emp;

        try {
            emp = (Empleado) buscarEmpleado.getSingleResult();

        } catch (Exception e) {
            return null;
        }

        return emp;
    }

    private static Empleado pedirEmpleado(Scanner entrada, EntityManager em) {
        System.out.println("Indica el nombre del empleado:");
        entrada.nextLine();
        String nombre = entrada.nextLine();

        Empleado empleado = verificarEmpleado(em, nombre);
        if (empleado == null) {
            System.out.println("El empleado no existe");
            return null;
        }
        return empleado;
    }
}
