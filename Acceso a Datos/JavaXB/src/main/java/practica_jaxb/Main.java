package practica_jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static Libreria LIB_1 = new Libreria("Las Hojas", "Getafe");
    private static final File XML = new File("src/main/java/practica_jaxb/libreria.xml");

    public static void main(String[] args) throws JAXBException {
        // Creamos el contexto JAXB
        JAXBContext context = JAXBContext.newInstance(Libreria.class);

        int opcion = -1;
        String menu = """
                    ===============================
                                 MENU
                    ===============================
                    1. Agregar libro
                    2. Buscar por autor
                    3. Ver librería
                    4. Ver títulos de libros
                    5. Borrar libro
                    6. Leer XML
                    0. Salir
                    """;

        while (opcion != 0) {
            System.out.println(menu);
            opcion = ENTRADA.nextInt();

            switch (opcion) {
                case 1 -> agregarLibro(context);
                case 2 -> buscarPorAutor(context);
                case 3 -> verLibreria(context);
                case 4 -> {
                    Libreria lib = leerFichero(context);
                    System.out.println(lib.titulosLibros());
                }
                case 5 -> borrarLibro(context);
                case 6 -> leerXml(context);
                case 0 -> System.out.println("Saliendo...");
                default -> {
                    while (opcion > 6 || opcion < 0) {
                        System.out.println("ERROR, indica una opción válida");
                        System.out.println(menu);
                        opcion = ENTRADA.nextInt();
                    }
                }
            }
        }
    }

    private static void fusionarLibros(JAXBContext context) throws JAXBException {
        Libreria libXml = leerFichero(context);

        ArrayList<Libro> librosAAgregar = new ArrayList<>();

        for (Libro libroXml : libXml.getLibros()) {
            for (Libro libro1 : LIB_1.getLibros()) {
                if (!libroXml.getIsbn().equals(libro1.getIsbn())) librosAAgregar.add(libroXml);
            }
        }

        LIB_1.setLibros(librosAAgregar);
        XML.delete();
    }

    private static Libreria leerFichero(JAXBContext context) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Libreria) unmarshaller.unmarshal(XML);
    }

    private static void escribirFichero(JAXBContext context) throws JAXBException {
        for (Libro libro : LIB_1.getLibros()) libro.setComparacion('t');
        LIB_1.ordenarLibros();

        // Mandamos los objetos a XML
        // Marshalling: Objeto Java -> XML
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Se meten los objetos en el XML
        marshaller.marshal(LIB_1, XML);
        LIB_1.getLibros().clear();
    }

    private static char verificarS_N(char variente) {
        while (variente != 's' && variente != 'n') {
            System.out.println();
            System.out.print("ERROR, indica s o n: ");
            variente = ENTRADA.next().charAt(0);
        }
        System.out.println();
        return variente;
    }

    private static void agregarLibro(JAXBContext context) throws JAXBException {
        System.out.print("Desea agregar varios libros? (s/n): ");
        char variente = ENTRADA.next().charAt(0);
        variente = verificarS_N(variente);

        if (variente == 's') {
            variente = ' ';
            ArrayList<Libro> librosNuevos = new ArrayList<>();

            while (variente != 'n') {
                Libro libro = crearlibro();
                librosNuevos.add(libro);

                System.out.print("Desea agregar otro libro? (s/n): ");
                variente = ENTRADA.next().charAt(0);
                variente = verificarS_N(variente);
            }

            LIB_1.setLibros(librosNuevos);
            System.out.println("Libros agregados correctamente\n");

        } else {
            Libro libro = crearlibro();
            LIB_1.setLibro(libro);
            System.out.println("Libro agregado correctamente\n");
        }

        if (XML.exists()) fusionarLibros(context); //Se borra el xml si existe para agregar en orden los libros nuevos
        escribirFichero(context);
    }

    public static Libro crearlibro() {
        System.out.print("Indica el título del libro: ");
        ENTRADA.nextLine();
        String titulo = ENTRADA.nextLine();
        System.out.println();

        System.out.print("Indica el autor del libro: ");
        String autor = ENTRADA.nextLine();
        System.out.println();

        System.out.print("Indica la editorial del libro: ");
        String editorial = ENTRADA.nextLine();
        System.out.println();

        System.out.print("Indica el precio del libro: ");
        double precio = ENTRADA.nextDouble();
        String precioString = String.format("%.2f", precio);
        precioString = precioString.replace(',', '.');
        System.out.println();

        System.out.print("Indica el ISBN del libro: ");
        int isbn = ENTRADA.nextInt();
        String isbnString = String.valueOf(isbn);
        System.out.println();

        return new Libro(titulo, autor, editorial, precioString, isbnString);
    }

    private static void buscarPorAutor(JAXBContext context) throws JAXBException {
        if (!XML.exists()) {
            System.out.println("El fichero no existe\n");
            return;
        }

        System.out.print("Indica el autor del libro: ");
        ENTRADA.nextLine();
        String autor = ENTRADA.nextLine();
        System.out.println();

        LinkedList<Libro> librosDeAutor = new LinkedList<>();

        Libreria lib = leerFichero(context);
        for (Libro libro : lib.getLibros()) {
            if (libro.getAutor().equals(autor)) {
                librosDeAutor.add(libro);
            }
        }

        if (librosDeAutor.isEmpty()) {
            System.out.println("No se encontraron libros de ese autor\n");
        } else {
            System.out.println("Libros encontrados del autor " + autor + ":");
            for (int i = 0; i < librosDeAutor.size(); i++) {
                Libro libro = librosDeAutor.get(i);

                if (i < librosDeAutor.size() - 1) {
                    System.out.print(libro);
                    System.out.println("---------------------");

                } else {
                    System.out.print(libro);
                    System.out.println();
                }
            }
        }
    }

    private static void verLibreria(JAXBContext context) throws JAXBException {
        if (XML.exists()) {
            System.out.print("Que orden quieres para mostrar los libros? autor: a / titulo: t / editorial: e / precio: p: ");
            ENTRADA.nextLine();
            char orden = ENTRADA.next().toLowerCase().charAt(0);
            System.out.println();

            while (orden != 'a' && orden != 't' && orden != 'e' && orden != 'p') {
                System.out.println("ERROR, indica a, t, e o p");
                orden = ENTRADA.next().toLowerCase().charAt(0);
            }

            Libreria lib2 = leerFichero(context);
            for (Libro libro : lib2.getLibros()) libro.setComparacion(orden);

            if (orden == 'p') {
                System.out.print("Desea ordenar de menor a mayor precio o de mayor a menor? m/M: ");
                ENTRADA.nextLine();
                char variente = ENTRADA.next().charAt(0);
                System.out.println();

                while (variente != 'm' && variente != 'M') {
                    System.out.println("ERROR, indica m o M");
                    variente = ENTRADA.next().charAt(0);
                }

                lib2.ordenarLibros();

                if (variente == 'M') {
                    Collections.reverse(lib2.getLibros());
                }
            } else  lib2.ordenarLibros();

            System.out.println(lib2);

        } else System.out.println("El fichero no existe\n");
    }

    private static void borrarLibro(JAXBContext context) throws JAXBException {
        System.out.print("Quieres ver los libros para saber cual borrar? s/n: ");
        ENTRADA.nextLine();
        char respuesta = ENTRADA.next().charAt(0);
        System.out.println();

        respuesta = verificarS_N(respuesta);
        if (respuesta == 's') verLibreria(context, 't');

        System.out.print("Indica el ISBN del libro a borrar: ");
        ENTRADA.nextLine();
        String isbn = ENTRADA.nextLine();
        System.out.println();

        LinkedList<Libro> librosBuenos = new LinkedList<>();
        Libreria lib = leerFichero(context);

        for (Libro libro : lib.getLibros()) {
            if (!libro.getIsbn().equals(isbn)) librosBuenos.add(libro);
        }

        LIB_1.setLibros(librosBuenos);
        escribirFichero(context);
    }

    private static void verLibreria(JAXBContext context, char orden) throws JAXBException {
        Libreria lib2 = leerFichero(context);
        for (Libro libro : lib2.getLibros()) libro.setComparacion(orden);
        lib2.ordenarLibros();
        System.out.println(lib2 + "\n");
    }

    private static void leerXml(JAXBContext context) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        LIB_1 = leerFichero(context);
        marshaller.marshal(LIB_1, System.out);
        System.out.println();// Se imprime en el XML
        LIB_1.getLibros().clear();
    }
}
