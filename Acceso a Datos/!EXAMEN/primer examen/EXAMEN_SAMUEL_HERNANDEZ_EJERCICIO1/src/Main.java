import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String RUTA_DAT = "src/productos.dat";
    private static final String MENU = """
            =============== MENU ===============
            1. Añadir productos
            2. Mostrar contenido
            3. Salir
            """;

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 3) {
            System.out.println(MENU);
            System.out.print("Elige una opción: ");
            opcion = entrada.nextInt();
            System.out.println();

            while (opcion < 1 || opcion > 3) {
                System.out.println("ERROR, indique una opción válida");
                System.out.println(MENU);
                System.out.print("Elige una opción: ");
                opcion = entrada.nextInt();
                System.out.println();
            }

            switch (opcion) {
                case 1 -> aniadirProductos(entrada);
                case 2 -> mostrarContenido();
                case 3 -> System.out.println("Saliendo...");
            }
        }
    }

    private static void mostrarContenido() {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(RUTA_DAT));
            leerFichero(dis);
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        }
    }

    private static void leerFichero(DataInputStream dis) {
        try {
            while (dis.available() > 0) {
                int codigo = dis.readInt();
                String nombre = dis.readUTF();
                double precio = dis.readDouble();

                System.out.println("Código: " + codigo);
                System.out.println("Nombre: " + nombre);
                System.out.println("Precio: " + precio);
                System.out.println("-----------------------------------\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        private static void aniadirProductos(Scanner entrada) {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(RUTA_DAT, true));
            datosProducto(entrada, dos);

        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe, se creará uno nuevo");
            File file = new File(RUTA_DAT);
            try {
                file.createNewFile();
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
                datosProducto(entrada, dos);
            } catch (Exception e1) {
                System.out.println("Error al crear el fichero");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void datosProducto(Scanner entrada, DataOutputStream dos) throws IOException {
        char respuesta = 's';

        while (respuesta != 'n') {
            System.out.print("Indica el código del producto: ");
            int codigo = entrada.nextInt();
            System.out.println();

            System.out.print("Indica el nombre del producto: ");
            entrada.nextLine();
            String nombre = entrada.nextLine();
            System.out.println();

            System.out.print("Indica el precio del producto: ");
            String precioString = entrada.nextLine();
            if (precioString.contains(",")) precioString = precioString.replace(",", ".");
            double precio = Double.parseDouble(precioString);

            dos.writeInt(codigo);
            dos.writeUTF(nombre);
            dos.writeDouble(precio);

            System.out.print("Quieres añadir otro producto? s/n: ");
            respuesta = entrada.next().toLowerCase().charAt(0);
            System.out.println();

            while (respuesta != 's' && respuesta != 'n') {
                System.out.println("ERROR, indique s o n");
                System.out.print("Quieres añadir otro producto? s/n: ");
                respuesta = entrada.next().toLowerCase().charAt(0);
                System.out.println();
            }
        }

        dos.close();
    }
}
