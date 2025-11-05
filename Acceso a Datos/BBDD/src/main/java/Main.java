import java.sql.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static final String USER = "root";
    private static final String CONTRA = "a";

    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final String MENU = """
            =================================
                           MENU
            =================================
            1. Leer empleados
            2. Insertar empleados
            3. Actualizar empleado
            4. Borrar empleado
            0. Salir
            """;

    public static void main(String[] args) throws  SQLException {
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empleadosdb", USER, CONTRA);

        int opcionMenu = -1;
        while (opcionMenu != 0) {
            System.out.println(MENU);
            opcionMenu = verificarOpciones();

            switch (opcionMenu) {
                case 1 -> leerEmpleados(conexion);
                case 2 -> insertarEmpleados(conexion);
                case 3 -> actualizarEmpleado(conexion);
                case 4 -> borrarEmpleado(conexion);
                case 0 -> {
                    System.out.println("Saliendo...");
                    conexion.close();
                }
            }
        }
    }

    private static void leerEmpleados(Connection conexion) throws  SQLException {
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery("SELECT * FROM empleado e JOIN departamento d ON e.departamento_id = d.id");

        int contador = 0;
        while (resultado.next()) {
            String  id = resultado.getString("id");
            String dni = resultado.getString("dni");
            String nombre = resultado.getString("nombre");
            String apellido = resultado.getString("apellido");
            String telefono = resultado.getString("telefono");
            Double salario = resultado.getDouble("salario");
            String nombreDepartamento = resultado.getString("d.nombre");

            if (contador == 0) {
                System.out.printf("""
                                                      DATOS DE LOS EMPLEADOS
            +======================================================================================================+
            | %-5s | %-10s | %-15s | %-15s | %-10s | %-10s | %-17s |
            +======================================================================================================+
            """, "ID", "DNI", "NOMBRE", "APELLIDO", "TELÉFONO", "SALARIO", "DEPARTAMENTO");
            }

            System.out.printf("| %-5s | %-10s | %-15s | %-15s | %-10s | %-10s | %-17s |\n",
                    id, dni, nombre, apellido, telefono, salario, nombreDepartamento);

            System.out.println("+------------------------------------------------------------------------------------------------------+");

            contador++;
        }

        resultado.close();
        consulta.close();
        System.out.println("\n\n");
    }

    private static void insertarEmpleados(Connection conexion) throws SQLException {
        System.out.print("Desea agregar uno o varios empleados? (u/v): ");
        char respuesta = verificarU_V();

        if (respuesta == 'v') {
            do {
                pedirEmpleado(conexion, 'a', 0);
                System.out.print("Desea agregar otro empleado? (s/n): ");
                respuesta = verificarS_N();

            } while  (respuesta == 's');
        } else pedirEmpleado(conexion, 'a', 0);

        System.out.println("Empleado insertado correctamente");
    }

    private static void pedirEmpleado(Connection conexion, char tipo, int id) throws SQLException {
        System.out.print("Indica el DNI del empleado: ");
        ENTRADA.nextLine();
        String dni = ENTRADA.nextLine();
        System.out.println();

        System.out.print("Indica el nombre del empleado: ");
        String nombre = ENTRADA.nextLine();
        System.out.println();

        System.out.print("Indica el apellido del empleado: ");
        String apellido = ENTRADA.nextLine();
        System.out.println();

        System.out.print("Indica el telefono del empleado: ");
        String telefono = ENTRADA.nextLine();
        System.out.println();

        System.out.print("Indica el salario del empleado: ");
        double salario = ENTRADA.nextDouble();
        System.out.println();

        System.out.print("Indica el departamento del empleado: ");
        String departamento = verificarDepartamento(conexion);
        System.out.println();

        PreparedStatement consulta;

        switch (tipo) {
            case 'a' -> consulta = conexion.prepareStatement("INSERT INTO empleado (dni, nombre, apellido, telefono, salario, departamento_id) " +
                                                             "VALUES (?, ?, ?, ?, ?, (SELECT id FROM departamento d WHERE d.nombre = ?))");

            case 'u' -> {
                consulta = conexion.prepareStatement("UPDATE empleado SET dni = ?, nombre = ?, apellido = ?, telefono = ?, salario = ?, departamento_id = (SELECT id FROM departamento d WHERE d.nombre = ?) WHERE id = ?");

                consulta.setInt(7, id);
            }

            default -> throw new IllegalStateException("Tipo de consulta incorrecto: " + tipo);
        }
        consulta.setString(1, dni);
        consulta.setString(2, nombre);
        consulta.setString(3, apellido);
        consulta.setString(4, telefono);
        consulta.setDouble(5, salario);
        consulta.setString(6, departamento);
        consulta.executeUpdate();
        consulta.close();
    }

    private static void actualizarEmpleado(Connection conexion) throws SQLException {
        leerEmpleados(conexion);
        System.out.print("Indica el ID del empleado: ");
        int id = verificarID(conexion);

        pedirEmpleado(conexion, 'u', id);
        System.out.println("Empleado actualizado correctamente");
    }

    private static void borrarEmpleado(Connection conexion) throws SQLException {
        leerEmpleados(conexion);

        System.out.print("Indica el ID del empleado: ");
        int id = verificarID(conexion);

        PreparedStatement consulta = conexion.prepareStatement("DELETE FROM empleado WHERE id = ?");
        consulta.setInt(1, id);
        consulta.executeUpdate();
        consulta.close();

        System.out.println("Empleado borrado correctamente");
    }

    private static int verificarOpciones() {
        int opcion;

        System.out.print("Seleccione una opcion: ");
        opcion = ENTRADA.nextInt();
        System.out.println();

        while (opcion < 0 || opcion > 4) {
            System.out.println("ERROR, selecciona una opción válida");
            System.out.println(MENU);
            opcion = ENTRADA.nextInt();
            System.out.println();
        }

        return  opcion;
    }

    private static int verificarID(Connection conexion) throws SQLException {
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery("SELECT id FROM empleado e");
        LinkedList<Integer> ids = new LinkedList<>();

        while (resultado.next()) {
            ids.add(resultado.getInt("e.id"));
        }

        int respuesta = ENTRADA.nextInt();
        System.out.println();

        boolean valido = false;

        while (!valido) {
            for (int id : ids) {
                if (id == respuesta) {
                    valido = true;
                    break;
                }
            }

            if (!valido) {
                System.out.println("ERROR, selecciona un departamento válido");
                System.out.print("Indica el departamento del empleado: ");
                respuesta = ENTRADA.nextInt();
                System.out.println();
            }
        }

        return respuesta;
    }

    private static String verificarDepartamento(Connection conexion) throws SQLException {
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery("SELECT nombre FROM departamento d");
        LinkedList<String> depts = new LinkedList<>();

        while (resultado.next()) {
            depts.add(resultado.getString("d.nombre").toUpperCase());
        }

        ENTRADA.nextLine();
        String departamento = ENTRADA.nextLine().toUpperCase();
        System.out.println();

        boolean valido = false;

        while (!valido) {
            for (String dept : depts) {
                if (departamento.equals(dept)) {
                    valido = true;
                    break;
                }
            }

            if (!valido) {
                System.out.println("ERROR, selecciona un departamento válido");
                System.out.print("Indica el departamento del empleado: ");
                departamento = ENTRADA.nextLine().toUpperCase();
                System.out.println();
            }
        }

        return departamento;
    }

    private static char verificarS_N() {
        char variente;

        variente = ENTRADA.next().charAt(0);
        System.out.println();

        while (variente != 's' && variente != 'n') {
            System.out.println();
            System.out.print("ERROR, indica s o n: ");
            variente = ENTRADA.next().charAt(0);
        }
        System.out.println();
        return variente;
    }

    private static char verificarU_V() {
        char respuesta;

        respuesta = ENTRADA.next().charAt(0);
        System.out.println();

        while (respuesta != 'u' && respuesta != 'v') {
            System.out.println("ERROR, selecciona una opción válida");
            System.out.print("Desea agregar uno o varios empleados? (u/v): ");
            respuesta = ENTRADA.nextLine().charAt(0);
            System.out.println();
        }

        return  respuesta;
    }
}
