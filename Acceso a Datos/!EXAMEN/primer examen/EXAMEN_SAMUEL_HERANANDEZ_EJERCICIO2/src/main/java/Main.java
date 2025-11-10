import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String XML = "src/main/java/";
    private static Videoclub videoclub = new Videoclub();
    private static ArrayList<Pelicula> peliculas = new ArrayList<>();
    private static final String MENU = """
            ----------------- MENU -----------------
            1. Generar XML
            2. Leer medias de pelis
            3. Ver videoclub
            0. Salir
            """;
    
    public static void main(String[] args) throws JAXBException {
        JAXBContext contexto = JAXBContext.newInstance(Videoclub.class);

        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        
        while (opcion != 0) {
            System.out.println(MENU);
            System.out.print("Elige una opcion: ");
            opcion = verificarOpcion(sc.nextInt(), sc);
            System.out.println();

            switch (opcion) {
                case 1 -> crearXml(sc, contexto);

                case 2 -> {

                }

                case 3 -> verVideoclub(sc, contexto);

                case 0 -> System.out.println("Saliendo...");
            }
        }
    }

    private static void crearXml(Scanner sc, JAXBContext contexto) throws JAXBException {
        System.out.print("Indica el nombre del fichero xml (todo junto): ");
        String nombreFichero = sc.next();
        System.out.println();

        File fichero = new File(XML + nombreFichero + ".xml");
        if (fichero.exists()) {
            System.out.println("ERROR, el fichero ya existe");
        } else {
            datosVideoclub(sc, fichero, contexto);
        }
    }

    private static void verVideoclub(Scanner sc, JAXBContext contexto) {
        System.out.print("Indica el nombre del fichero xml (todo junto): ");
        String nombreFichero = sc.next();
        System.out.println();

        File fichero = new File(XML + nombreFichero + ".xml");
        if (fichero.exists()) {
            videoclub = leerXML(fichero, contexto);

            System.out.printf("""
                    Videoclub: %s (%s)
                    Películas:
                    
                    """, videoclub.getNombre(), videoclub.getCiudad());

            for (Pelicula pelicula : videoclub.getPeliculas()) {
                System.out.printf("""
                        - %s     (%d) - %d min
                        """, pelicula.getTitulo(), pelicula.getAño(), pelicula.getDuracion());
            }
        } else {
            System.out.println("ERROR, el fichero no existe");
        }
    }

    private static Videoclub leerXML(File fichero, JAXBContext contexto) {
        Videoclub videoclub = new Videoclub();
        try {
            videoclub = (Videoclub) contexto.createUnmarshaller().unmarshal(fichero);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return videoclub;

    }

    private static void datosVideoclub(Scanner sc, File file, JAXBContext contexto) throws JAXBException {
        System.out.print("Indica el nombre del videoclub: ");
        sc.nextLine();
        String nombre = sc.nextLine();
        System.out.println();
        System.out.print("Indica la ciudad del videoclub: ");
        String ciudad = sc.nextLine();
        System.out.println();

        System.out.print("Quieres añadir una pelicula? (s/n): ");
        char respuesta = sc.next().charAt(0);
        respuesta = revisarS_N(respuesta, sc);
        System.out.println();

        while (respuesta != 'n') {
            System.out.print("Titulo de la peli: ");
            sc.nextLine();
            String titulo = sc.nextLine();
            System.out.println();

            System.out.print("Director de la peli: ");
            String director = sc.nextLine();
            System.out.println();

            System.out.print("Año de la peli: ");
            int anio = sc.nextInt();
            verificarAnio(anio, sc);
            System.out.println();

            System.out.print("Duración de la peli (minutos): ");
            int duracion = sc.nextInt();
            System.out.println();

            System.out.print("Genero de la peli: ");
            sc.nextLine();
            String genero = sc.nextLine();
            System.out.println();

            Pelicula pelicula = new Pelicula(titulo, director, anio, genero, duracion);
            peliculas.add(pelicula);

            System.out.print("Quieres añadir otra pelicula? (s/n): ");
            respuesta = sc.next().charAt(0);
            respuesta = revisarS_N(respuesta, sc);
            System.out.println();
        }

        Videoclub videoclub = new Videoclub(nombre, ciudad);
        videoclub.setPeliculas(peliculas);

        Marshaller marshaller = contexto.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(videoclub, file);

        peliculas.clear();
    }

    private static void verificarAnio(int anio, Scanner sc) {
        while (anio < 1900 || anio > 2050) {
            System.out.println("ERROR, indique un año válido");
            System.out.print("Año de la peli: ");
            anio = sc.nextInt();
        }
    }

    private static char revisarS_N(char respuesta, Scanner sc) {
        while (respuesta != 's' && respuesta != 'n') {
            System.out.println("ERROR, indique una respuesta válida");
            System.out.print("Quieres añadir una pelicula? (s/n): ");
            respuesta = sc.next().charAt(0);
        }
        return respuesta;
    }

    private static int verificarOpcion(int opcion, Scanner sc) {
        while (opcion < 0 || opcion > 3) {
            System.out.println("ERROR, indique una opción válida");
            System.out.println(MENU);
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            System.out.println();
        }
        return opcion;
    }
}
