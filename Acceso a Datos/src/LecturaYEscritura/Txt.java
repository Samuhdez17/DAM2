package LecturaYEscritura;

import java.io.*;

public class Txt {
    public static void main(String[] args) {
        String ruta = "src/LecturaYEscritura/proyecto.txt";

        try (FileWriter fichero = new FileWriter(ruta)){
            fichero.write("Hola Mundo");

            System.out.println("Se ha escrito en: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al escribir");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;

            System.out.println("Lectura de: " + ruta);
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            System.out.println();
        } catch (FileNotFoundException e) {}
          catch (IOException e) {}

        try (FileWriter fichero = new FileWriter(ruta, true)){
            fichero.write("\nque talll");

            System.out.println("Se ha escrito en: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al escribir");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;

            System.out.println("Lectura de: " + ruta);
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            System.out.println();
        } catch (FileNotFoundException e) {}
        catch (IOException e) {}

        File file = new File(ruta);
        file.delete();
    }
}
