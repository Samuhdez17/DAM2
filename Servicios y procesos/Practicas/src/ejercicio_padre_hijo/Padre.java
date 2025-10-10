package ejercicio_padre_hijo;

import java.io.*;

public class Padre {
    private static final String RUTA_CLASS = "out/production/Practicas";
    private static final String RUTA_ERRORES = "src/ejercicio_padre_hijo/errores.txt";

    public static void main(String[] args) {
        ProcessBuilder proceso = new ProcessBuilder("java", "-cp", RUTA_CLASS, Hijo.class.getName());
        proceso.redirectError(ProcessBuilder.Redirect.appendTo(new File(RUTA_ERRORES)));

        String numero;

        try {
            Process hijo = proceso.start();
            mandarNumeroHijo(hijo);
            numero = recibirNumeroHijo(hijo);
            hijo.getErrorStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("El número devuelto por nieto es: " + numero);
    }

    private static void mandarNumeroHijo(Process proceso) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));

            bw.write("20\n");
            bw.flush();
            bw.close();
            proceso.getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String recibirNumeroHijo(Process proceso) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String numero = br.readLine();
        br.close();
        proceso.getInputStream().close();
        return numero;
    }
}
