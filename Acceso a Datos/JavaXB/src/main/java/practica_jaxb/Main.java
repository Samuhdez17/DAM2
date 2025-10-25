package practica_jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static Libreria LIB_1 = new Libreria("Las Hojas", "Getafe");
    private static final File XML = new File("src/main/java/practica_jaxb/libreria.xml");

    public static void main(String[] args) throws JAXBException {
        // Creamos el contexto JAXB
        JAXBContext context = JAXBContext.newInstance(Libreria.class);
        // TODO: menu en el que se pueda: leer el xml ya sea leyendo el propio xml, leer solo titulos de los libros

        int opcion = -1;

        while (opcion != 0) {
            System.out.println("""
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
                    """); // Opcion 5, se borra el libro y si existe xml, se borra y se genera uno nuevo
            opcion = ENTRADA.nextInt();

            switch (opcion) {
                case 1 -> agregarLibro(context);
                case 2 -> buscarPorAutor(context);
                case 3 -> {
                    if (XML.exists()) {
                        System.out.println("Que orden quieres para mostrar los libros? autor: a / titulo: t / editorial: e / precio: p");
                        char orden = ENTRADA.next().toLowerCase().charAt(0);
                        while (orden != 'a' && orden != 't' && orden != 'e' && orden != 'p') {
                            System.out.println("ERROR, indica a, t, e o p");
                            orden = ENTRADA.next().toLowerCase().charAt(0);
                        }

                        // Unmarshalling: XML -> Objeto Java
                        Libreria lib2 = leerXml(context);
                        for (Libro libro : lib2.getLibros()) libro.setComparacion(orden);

                        lib2.ordenarLibros();

                        System.out.println(lib2);

                    } else System.out.println("El fichero no existe\n");
                }
                case 4 -> {}
                case 5 -> {}
                case 6 -> {}
                case 0 -> System.out.println("Saliendo...");
            }
        }
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

        Libreria lib = leerXml(context);
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

    private static void fusionarLibros(JAXBContext context) throws JAXBException {
        Libreria libXml = leerXml(context);

        ArrayList<Libro> librosAAgregar = new ArrayList<>();

        for (Libro libroXml : libXml.getLibros()) {
            for (Libro libro1 : LIB_1.getLibros()) {
                if (!libroXml.getIsbn().equals(libro1.getIsbn())) librosAAgregar.add(libroXml);
            }
        }

        LIB_1.setLibros(librosAAgregar);
        XML.delete();
    }

    private static Libreria leerXml(JAXBContext context) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Libreria libXml = (Libreria) unmarshaller.unmarshal(XML);
        return libXml;
    }

    private static void escribirFichero(JAXBContext context) throws JAXBException {
        // Mandamos los objetos a XML
        // Marshalling: Objeto Java -> XML
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Se meten los objetos en el XML
        marshaller.marshal(LIB_1, XML);
        LIB_1.getLibros().clear();
//        marshaller.marshal(LIB_1, System.out); // Se imprime en el XML
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
        System.out.println();

        System.out.print("Indica el ISBN del libro: ");
        int isbn = ENTRADA.nextInt();
        String isbnString = String.valueOf(isbn);
        System.out.println();

        return new Libro(titulo, autor, editorial, precioString, isbnString);
    }
}
