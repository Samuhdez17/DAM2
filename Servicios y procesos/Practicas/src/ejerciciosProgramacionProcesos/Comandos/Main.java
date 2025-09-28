package ejerciciosProgramacionProcesos.Comandos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
    private static final Scanner ENTRADA = new Scanner(System.in);

    public static void main(String[] args) {
        Process procesoEnEjecucion = null;
        UtilidadesSistema us = new UtilidadesSistema();
        ProcessBuilder procesoPreparado;
        boolean isWin = !us.isUNIX();

        if (isWin) procesoPreparado = new ProcessBuilder("cmd");
        else       procesoPreparado = new ProcessBuilder("sh");

        int opcion = -1;

        System.out.printf("Bienvenido usuari@ de %s\n", isWin ? "Windows" : "Linux");
        while (opcion != 0) {
            System.out.println("------------ MENU ------------");
            System.out.println("1. Ejecutar ping");
            System.out.println("2. Realizar listado de ficheros");
            System.out.println("3. Terminar proceso por PID");
            System.out.println("4. Ejecutar navegador");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");
            opcion = ENTRADA.nextInt();
            System.out.println();

            if (isWin) {
                switch (opcion) {
                    case 1 -> procesoEnEjecucion = hacerPingWin(procesoPreparado);
                    
                    case 2 -> procesoEnEjecucion = hacerTree(procesoEnEjecucion, procesoPreparado);
                    
                    case 3 -> procesoEnEjecucion = matarProcesoWin(procesoPreparado);
                    
                    case 4 -> procesoEnEjecucion = abrirNavegadorWin(procesoPreparado);
                    
                }

            } else {
                switch (opcion) {
                    case 1 -> procesoEnEjecucion = hacerPingUNIX(procesoPreparado);
                    
                    case 2 -> procesoEnEjecucion = hacerTree(procesoEnEjecucion, procesoPreparado);
                    
                    case 3 -> procesoEnEjecucion = matarProcesoUNIX(procesoPreparado);
                    
                    case 4 -> procesoEnEjecucion = abrirNavegadorUNIX(procesoPreparado);
                    
                }
            }

            if (opcion != 4 && opcion != 0 && opcion != 3) mostrarPorConsola(procesoEnEjecucion);

            if (opcion == 0) System.out.println("Saliendo.....");
            System.out.println();
        }

        ENTRADA.close();
        if (procesoEnEjecucion != null) procesoEnEjecucion.destroy();
    }
    // GENÉRICOS
    private static Process hacerTree(Process procesoEnEjecucion, ProcessBuilder procesoPreparado) {
        try {
            procesoEnEjecucion = procesoPreparado.start();
            OutputStream os = procesoEnEjecucion.getOutputStream();

            os.write("tree\n".getBytes());

            os.write("exit\n".getBytes());
            os.flush();
        } catch (IOException e){
            System.out.println("Error al ejecutar el comando");
        }
        return procesoEnEjecucion;
    }
    private static void mostrarPorConsola(Process procesoEnEjecucion) {
        try {
            assert procesoEnEjecucion != null;
            BufferedReader lecturaSalida = new BufferedReader(
                    new InputStreamReader(procesoEnEjecucion.getInputStream())
            );

            String linea;
            while ((linea = lecturaSalida.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
    }

    // NAVEGADOR
    private static Process abrirNavegadorUNIX(ProcessBuilder procesoPreparado) {
        Process procesoEnEjecucion;

        System.out.println("Abriendo navegador...");
        try {
            procesoEnEjecucion = procesoPreparado.start();
            OutputStream os = procesoEnEjecucion.getOutputStream();

            os.write("firefox https://www.youtube.com/watch?v=dQw4w9WgXcQ&list=RDdQw4w9WgXcQ&start_radio=1\n".getBytes());
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return procesoEnEjecucion;
    }
    private static Process abrirNavegadorWin(ProcessBuilder procesoPreparado) {
        Process procesoEnEjecucion;

        System.out.println("Abriendo navegador...");
        try {
            procesoEnEjecucion = procesoPreparado.start();
            OutputStream os = procesoEnEjecucion.getOutputStream();

            os.write("start chrome https://www.youtube.com/watch?v=dQw4w9WgXcQ&list=RDdQw4w9WgXcQ&start_radio=1\n".getBytes());
            os.write("exit\n".getBytes());
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return procesoEnEjecucion;
    }

    // PING
    private static Process hacerPingUNIX(ProcessBuilder procesoPreparado) {
        Process procesoEnEjecucion;
        String comando;
        System.out.print("Indique la ip para hacer ping: ");
        ENTRADA.nextLine();
        comando = ENTRADA.nextLine();
        comando = "ping -c4 " + comando + "\n";
        System.out.println();

        try {
            procesoEnEjecucion = procesoPreparado.start();
            OutputStream os = procesoEnEjecucion.getOutputStream();
            os.write(comando.getBytes());

            os.flush();
        } catch (IOException e) {
            System.out.println("Error al ejecutar el comando");
            throw new RuntimeException(e);
        }

        return procesoEnEjecucion;
    }
    private static Process hacerPingWin(ProcessBuilder procesoPreparado) {
        Process procesoEnEjecucion;
        String comando;
        System.out.print("Indique la ip para hacer ping: ");
        ENTRADA.nextLine();
        comando = ENTRADA.nextLine();
        comando = "ping " + comando + "\n";
        System.out.println();

        try {
            procesoEnEjecucion = procesoPreparado.start();
            OutputStream os = procesoEnEjecucion.getOutputStream();
            os.write(comando.getBytes());

            os.write("exit\n".getBytes());
            os.flush();
        } catch (IOException e) {
            System.out.println("Error al ejecutar el comando");
            throw new RuntimeException(e);
        }

        return procesoEnEjecucion;
    }

    // MATAR PROCESO
    private static Process matarProcesoUNIX(ProcessBuilder procesoPreparado) {
        Process procesoEnEjecucion;
        System.out.println("Mostrando procesos...");
        try {
            procesoEnEjecucion = procesoPreparado.start();
            OutputStream os = procesoEnEjecucion.getOutputStream();

            os.write("ps aux\n".getBytes());
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mostrarPorConsola(procesoEnEjecucion);

        System.out.print("Indica el PID del proceso a finalizar: ");
        int pid = ENTRADA.nextInt();
        System.out.println();

        try {
            procesoEnEjecucion = procesoPreparado.start();
            OutputStream os = procesoEnEjecucion.getOutputStream();

            os.write(("kill -9 " + pid + "\n").getBytes());
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Proceso finalizado correctamente");
        return procesoEnEjecucion;
    }
    private static Process matarProcesoWin(ProcessBuilder procesoPreparado) {
        Process procesoEnEjecucion;
        System.out.println("Mostrando procesos...");
        try {
            procesoEnEjecucion = procesoPreparado.start();
            OutputStream os = procesoEnEjecucion.getOutputStream();

            os.write("tasklist\n".getBytes());
            os.write("exit\n".getBytes());
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mostrarPorConsola(procesoEnEjecucion);

        System.out.print("Indica el PID del proceso a finalizar: ");
        int pid = ENTRADA.nextInt();
        System.out.println();

        try {
            procesoEnEjecucion = procesoPreparado.start();
            OutputStream os = procesoEnEjecucion.getOutputStream();

            os.write(("taskkill /F /PID " + pid + "\n").getBytes());
            os.write("exit\n".getBytes());
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Proceso finalizado correctamente");
        return procesoEnEjecucion;
    }
}
