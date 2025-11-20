package org.example;

import dao.Conexion;
import model.Asistente;
import model.Evento;
import services.Gestionador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        Conexion conexion = new Conexion();
        Gestionador gestionador = new Gestionador(conexion);

        int opcion;

        do {
            System.out.println("\n===== MENÚ GESTIÓN EVENTOS =====");
            System.out.println("1. Añadir un nuevo evento");
            System.out.println("2. Listar eventos");
            System.out.println("3. Añadir asistente a un evento");
            System.out.println("4. Borrar un asistente");
            System.out.println("5. Modificar un evento");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> { // Añadir evento
                    agregarEvento(gestionador);
                }

                case 2 -> { //Listar eventos
                    ArrayList<Evento> eventos = gestionador.getEventos();

                    for (Evento evento : eventos) {
                        ArrayList<Asistente> asistentes = gestionador.getAsistentesPorEvento(evento.getID());
                        System.out.print(evento + " ");

                        if (asistentes.isEmpty()) System.out.println("(Sin asistentes)");
                        else {
                            for (Asistente asistente : asistentes) {
                                System.out.printf("     - %s ", asistente.getNombre());
                            }
                        }
                    }
                }

                case 3 -> { //Añadir asistente a un evento
                    Evento evento = pedirEventoPorTitulo(gestionador);

                    if (evento == null) {
                        System.out.println("No se encontró evento");
                        continue;
                    }

                    int aforoEvento = evento.getAforoMax();
                    ArrayList<Asistente> asistentesDelEvento = gestionador.getAsistentesPorEvento(evento.getID());

                    if (asistentesDelEvento.size() >= aforoEvento) {
                        System.out.println("ERROR. Aforo máximo completado, elija otro evento");
                        continue;

                    } else {
                        System.out.print("Indica el nombre del asistente: ");
                        String nombre = sc.nextLine();

                        System.out.print("Indica el email del asistente: ");
                        String email = sc.nextLine();

                        gestionador.aniadirAsistente(nombre, email, evento.getID());

                        System.out.println("Asistente agregado");
                    }
                }
                case 4 -> { // Borrar un asistente
                    Evento evento = pedirEventoPorTitulo(gestionador);
                    ArrayList<Asistente> asistentesDelEvento = gestionador.getAsistentesPorEvento(evento.getID());

                    System.out.println("Mostrando asistentes del evento:");
                    for (Asistente asistente : asistentesDelEvento) {
                        System.out.println(asistente);
                    }

                    System.out.print("\nIndica el ID del asistente a eliminar: ");
                    int asistente = sc.nextInt();

                    for (Asistente a : asistentesDelEvento) {
                        if (a.getID() == asistente) {
                            gestionador.eliminarAsistente(asistente);
                            break;
                        }
                    }

                    System.out.println("ERROR. El asistente seleccionado no existe");
                }
                case 5 -> { //Modificar un evento
                    ArrayList<Evento> eventos = gestionador.getEventos();

                    System.out.println("Mostrando eventos: ");
                    for (Evento evento : eventos) {
                        System.out.println(evento.strigSimple());
                    }

                    pedirDatos(gestionador);
                }
                case 6 -> {
                    System.out.println("Saliendo...");
                    gestionador.cerrarConexion();
                }
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 6);
    }

    private static void agregarEvento(Gestionador gestionador) {
        System.out.print("Indica el título del evento: ");
        String titulo = sc.nextLine();

        System.out.print("Indica la fecha del evento (dd/mm/aaaa): ");
        String fecha = sc.nextLine();

        System.out.print("Indica el aforo máximo: ");
        int aforo = sc.nextInt();

        gestionador.aniadirEvento(titulo, fecha, aforo);
    }

    public static Evento pedirEventoPorTitulo(Gestionador gestionador) {
        System.out.print("Indica el nombre del evento: ");
        String titulo = sc.nextLine();

        return gestionador.getEventoPorTitulo(titulo);
    }

    private static void pedirDatos(Gestionador gestionador) {
        System.out.print("Indica el id del evento a modificar: ");
        int id = sc.nextInt();

        Evento evento = gestionador.getEventoPorID(id);

        String titulo = evento.getTitulo();
        String fecha = evento.getFecha();
        int aforo = evento.getAforoMax();

        System.out.print("Indica los campos a modificar (t/f/a): ");
        String campos = sc.nextLine().toLowerCase();

        if (campos.contains("t")) {
            System.out.print("Indica el título del evento: ");
            titulo = sc.nextLine();
        }

        if (campos.contains("f")) {
            System.out.print("Indica la fecha del evento (dd/mm/aaaa): ");
            fecha = sc.nextLine();
        }

        if (campos.contains("a")) {
            System.out.print("Indica el aforo máximo: ");
            aforo = sc.nextInt();
        }

        gestionador.actualizarEvento(id, titulo, fecha, aforo);
    }
}