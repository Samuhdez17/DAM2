package ejercicio_padre_hijo;

import java.io.*;
import java.util.Scanner;

public class Hijo {
    private static final String RUTA_CLASS = "out/production/Practicas";
    private static final String RUTA_ERRORES = "src/ejercicio_padre_hijo/errores.txt";

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String numeroPadre = entrada.nextLine();

        ProcessBuilder pb = new ProcessBuilder("java", "-cp", RUTA_CLASS, Nieto.class.getName());
        pb.redirectError(ProcessBuilder.Redirect.appendTo(new File(RUTA_ERRORES)));

        try {
            Process nieto = pb.start();
            mandarNumeroNieto(nieto, numeroPadre);
            String numeroNieto = recibirNumeroNieto(nieto);

            System.out.println(numeroNieto);
            System.out.flush();

            nieto.getInputStream().close();
            nieto.getErrorStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void mandarNumeroNieto(Process nieto, String numeroPadre) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(nieto.getOutputStream()));
            bw.write(numeroPadre + "\n");
            bw.flush();
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String recibirNumeroNieto(Process nieto) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(nieto.getInputStream()));
            String numero = br.readLine();
            br.close();
            nieto.getInputStream().close();
            return numero;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
