package ejerciciosProgramacionProcesos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        UtilidadesSistema us = new  UtilidadesSistema();
        ProcessBuilder procesoPreparado;

        if (us.isUNIX()) procesoPreparado = new ProcessBuilder("sh", "-c", "ping -c4 8.8.8.8");
        else             procesoPreparado = new ProcessBuilder("cmd", "/c", "ping 8.8.8.8");

        try {
            Process procesoEnEjecucion = procesoPreparado.start();

            BufferedReader lecturaSalida = new BufferedReader(
                    new InputStreamReader(procesoEnEjecucion.getInputStream())
            );

            String linea;
            while ((linea = lecturaSalida.readLine()) != null) {
                System.out.println(linea);
            }

            int codigoSalida = procesoEnEjecucion.waitFor();
            System.out.println("Codigo de salida: " + codigoSalida);

        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
    }
}
