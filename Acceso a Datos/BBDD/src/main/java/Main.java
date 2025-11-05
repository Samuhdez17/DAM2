import java.sql.*;
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
            3. Actualizar empleados
            4. Borrar empleados
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
                case 2 -> {}
                case 3 -> {}
                case 4 -> {}
                case 0 -> {
                    System.out.println("Saliendo...");
                    conexion.close();
                }
            }
        }
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

            if (contador == 0) System.out.printf("""
                                DATOS DE LOS EMPLEADOS
                        ====================================
                        ID: %s
                        DNI: %s
                        NOMBRE: %s
                        APELLIDO: %s
                        TELEFONO: %s
                        SALARIO: %s
                        DEPARTAMENTO: %s
                        ====================================
                        """, id, dni, nombre, apellido, telefono, salario, nombreDepartamento);

            else System.out.printf("""
                        ID: %s
                        DNI: %s
                        NOMBRE: %s
                        APELLIDO: %s
                        TELEFONO: %s
                        SALARIO: %s
                        DEPARTAMENTO: %s
                        ====================================
                        """, id, dni, nombre, apellido, telefono, salario, nombreDepartamento);

            contador++;
        }

        resultado.close();
        consulta.close();
        System.out.println("\n\n");
    }
}
