package ejerciciosProgramacionProcesos.Ej2_PingConPb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        UtilidadesSistema us = new  UtilidadesSistema();
        ProcessBuilder procesoPreparado;
        boolean isWin = !us.isUNIX();

        if (isWin) procesoPreparado = new ProcessBuilder("cmd");
        else       procesoPreparado = new ProcessBuilder("sh");

        System.out.print("Indica el comando: ");
        String comando = entrada.nextLine() + "\n";

        try {
            Process procesoEnEjecucion = procesoPreparado.start();
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(procesoEnEjecucion.getOutputStream()));
            OutputStream os = procesoEnEjecucion.getOutputStream();
            os.write(comando.getBytes());

            os.write("exit\n".getBytes());
            os.flush();

            BufferedReader lecturaSalida = new BufferedReader(
                    new InputStreamReader(procesoEnEjecucion.getInputStream())
            );

            String linea;
            while ((linea = lecturaSalida.readLine()) != null) {
                System.out.println(linea);
            }

            int codigoSalida = procesoEnEjecucion.waitFor();
            System.out.println("Código de salida: " + codigoSalida);

        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
    }
}
