package padre_hijo;

import
        java.io
        .*;
import java.util.ArrayList;
import java.util.List;

public class LanzarComandoPingBasico {

    public static void main(String[] args) {

        ProcessBuilder constructorProcesos = new ProcessBuilder();
        constructorProcesos.command("bash", "-c", "ping google.com");

        try {
            // constructorProcesos.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process hijo = constructorProcesos.start();


            imprimirSalidaHijoBloqueoManual(hijo);
            //imprimirSalidaHijoWaitFor(hijo);

            System.out.println("Murió");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void imprimirSalidaHijoBloqueoManual(Process hijo) throws IOException {
        InputStream salidaDelHijo = hijo.getInputStream();
        BufferedReader lector = new BufferedReader(new InputStreamReader(salidaDelHijo));
        List<String> lineasProceso = new ArrayList<>();
        String linea;
        int numLineasLeidas = 1;
        while (hijo.isAlive() && (linea = lector.readLine()) != null) {
            System.out.println(numLineasLeidas + ": linea = " + linea);
            lineasProceso.add(linea);
            numLineasLeidas++;

            //Probamos a matar de forma forzosa el proceso para utiliza esa funcion.
            //Además cerramos el
            if(numLineasLeidas > 10){
                hijo.destroyForcibly();
                break;
            }
        }
        lector.close();

    }



    private static void imprimirSalidaHijoWaitFor(Process hijo) throws IOException{
        InputStream salidaDelHijo = hijo.getInputStream();
        BufferedReader lector = new BufferedReader(new InputStreamReader(salidaDelHijo));
        List<String> lineasProceso = new ArrayList<>();
        String linea;
        int numLineasLeidas = 1;
        try{
            hijo.waitFor();
        }catch(InterruptedException e )
        {
            System.err.println("Proceso finalizado por una interrupción");
        }

        while ((linea = lector.readLine()) != null) {
            System.out.println(numLineasLeidas + ": linea = " + linea);
            lineasProceso.add(linea);
            numLineasLeidas++;
        }
        lector.close();

    }
}