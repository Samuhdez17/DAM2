package Ejercicios.E007akinator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {
    public static void main(String[] args) {
        Random random = new Random();
        int puerto = 5000;

        try (ServerSocket akinator = new ServerSocket(puerto)) {
            String mensajeAkinator = "";
            Socket jugador = null;
            String mensajeJugador = "";

            System.out.println("Akinator está iniciado, esperando jugador...");

            while (!mensajeJugador.contains("salir")) {
                // Espera hasta que un jugador se conecte
                jugador = akinator.accept();
                System.out.println("Jugador conectado desde: " + jugador.getInetAddress().getHostAddress());

                PrintWriter salida = null;
                boolean seHaGanado = false;
                boolean[] numerosDescartados = new boolean[101];

                while (!seHaGanado) {
                    salida = new PrintWriter(jugador.getOutputStream(), true);
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(jugador.getInputStream()));

                    bienvenida(salida);
                    mensajeJugador = entrada.readLine();

                    if (mensajeJugador.isEmpty()) {
                        preguntarPar(salida);

                        mensajeJugador = entrada.readLine().toLowerCase();
                        while (!mensajeJugador.equals("s") && !mensajeJugador.equals("n")) {
                            mensajeErrorS_N(salida);
                            mensajeJugador = entrada.readLine().toLowerCase();
                        }

                        if (mensajeJugador.equals("s")) {
                            for (int i = 0 ; i < numerosDescartados.length ; i++) if (i % 2 != 0) numerosDescartados[i] = true;

                        } else {
                            for (int i = 0 ; i < numerosDescartados.length ; i++) if (i % 2 == 0) numerosDescartados[i] = true;
                        }

                        int numero;

                        for (int i = 0; i < 7; i++) {
                            if (seHaGanado) break;

                            numero = generarDeduccion(numerosDescartados, random);
                            decirDeduccion(numero, salida);
                            mensajeJugador = entrada.readLine();

                            while (!mensajeJugador.equals("M") && !mensajeJugador.equals("m") && !mensajeJugador.equals("s")) {
                                mensajeErrorM_m(salida, numero);
                                mensajeJugador = entrada.readLine();
                            }

                            switch (mensajeJugador) {
                                case "M" -> {
                                    for (int M = 0; M <= numero; M++) numerosDescartados[M] = true;
                                }

                                case "m" -> {
                                    for (int m = numero; m < numerosDescartados.length; m++) numerosDescartados[m] = true;
                                }

                                case "s" -> seHaGanado = true;
                            }
                        }

                        if (!seHaGanado) {
                            mensajeAkinator = """
                                    Ganaste! Akinator no ha podido adivinar tu numero.
                                    Vuelve a conectarte si quieres jugar de nuevo.
                                    """;
                            salida.println(mensajeAkinator);
                            break;
                        }
                    } else if (mensajeJugador.contains("salir")) break;
                }

                if (seHaGanado) {
                    salida.println("Akinator te ha ganado! Vuelve a conectarte si quieres jugar de nuevo.");
                    seHaGanado = false;

                } else  {
                    salida.println("Cerrando servidor...");
                }

                // Cierre
                jugador.close();
            }

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static void decirDeduccion(int numero, PrintWriter salida) {
        String mensajeAkinator;
        mensajeAkinator = String.format("Es tu numero %d? (M, m, s) Mayor, menor, si: ", numero);
        salida.println(mensajeAkinator);
    }

    private static int generarDeduccion(boolean[] numerosDescartados, Random random) {
        int numero = random.nextInt(101);
        while (numerosDescartados[numero]) numero = random.nextInt(101);
        return numero;
    }

    private static void mensajeErrorM_m(PrintWriter salida, int numero) {
        String mensajeAkinator;
        mensajeAkinator = String.format("""
                ERROR, indica M, m o s
                Es tu numero %d? (M, m, s) Mayor, menor, si: """, numero);
        salida.println(mensajeAkinator);
    }

    private static void mensajeErrorS_N(PrintWriter salida) {
        String mensajeAkinator;
        mensajeAkinator = """
                ERROR, indica s o n
                Es tu numero par? (s/n): """;
        salida.println(mensajeAkinator);
    }

    private static void preguntarPar(PrintWriter salida) {
        String mensajeAkinator;
        mensajeAkinator = """
            Muy bien, comencemos!
            Es tu numero par? (s/n): """;
        salida.println(mensajeAkinator);
    }

    private static void bienvenida(PrintWriter salida) {
        String mensajeAkinator;
        mensajeAkinator = """
                Bienvenido, jugador. Piensa en un numero del 0 al 100 y lo adivinare.
                Presiona ENTER para comenzar.
                """;
        salida.println(mensajeAkinator);
    }
}
