package padre_hijo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LanzarComandoPingBloqueo {

    public static void main(String[] args) {

        ProcessBuilder constructorProcesos = new ProcessBuilder();
        constructorProcesos.command("bash", "-c", "ping google.com");

        try {
            constructorProcesos.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            Process hijo = constructorProcesos.start();

            System.out.println("Me quedo esperando a que termine el ping");

            //Espero a que termine el hijo durante 5 segundos
            hijo.waitFor(5, TimeUnit.SECONDS);

            System.out.println("Voy a comprobar si sigue activo: " + hijo.isAlive());

            if(hijo.isAlive()){
                System.out.println("Sigue activo, lo mato");
                hijo.destroyForcibly();
            }
            System.out.println("Murió el ping");


            //imprimirSalidaHijo(hijo);



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
