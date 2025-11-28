package LecturaYEscritura;

import java.io.*;

public class Dat {
    public static void main(String[] args) {
        String ruta = "src/LecturaYEscritura/proyecto.dat";
        String[] ciudades =    {"Madrid", "Salamanca", "Murcia", "Tenerife"};
        String[] poblaciones = {"991209", "662561", "5544", "23232"};
        int[]    cps =         {28500, 37000, 58000, 69000};

        try (DataOutputStream data = new DataOutputStream(new FileOutputStream(ruta))) {
            for (int i = 0 ; i < ciudades.length; i++) {
                data.writeUTF(ciudades[i]);
                data.writeUTF(poblaciones[i]);
                data.writeInt(cps[i]);
            }

            System.out.println("Archivo escrito correctamente");

        } catch (FileNotFoundException e) {
            System.out.println("No existe el archivo");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error al escribir");
            throw new RuntimeException(e);
        }
        System.out.println();

        try (DataInputStream fichero = new DataInputStream(new FileInputStream(ruta))) {
            while (true) {
                try {
                    String ciudad = fichero.readUTF();
                    String poblacion = fichero.readUTF();
                    int cp = fichero.readInt();
                    System.out.printf("Ciudad: %s ; Poblacion: %s ; CP: %d\n", ciudad, poblacion, cp);

                } catch (EOFException e) {
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("No existe el archivo");
        }
        catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
    }
}
