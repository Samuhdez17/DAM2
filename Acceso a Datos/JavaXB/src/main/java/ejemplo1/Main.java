package ejemplo1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JAXBException {
        Libro[] libros = {
                new Libro("Cervantes", "Quijote", "El barco de Vapor", "1234"),
                new Libro("Fernando de Rojas", "La Celestina", "Anaya", "1456"),
        };
        Libreria lib1 = new Libreria("Las Hojas", "Getafe");

        lib1.setLibros(List.of(libros));

        // Creamos el contexto JAXB
        JAXBContext context = JAXBContext.newInstance(Libreria.class);

        // Marshalling: Objeto Java -> XML
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        System.out.println("Generando XML...");

        // Se meten los objetos en el XML
        marshaller.marshal(lib1, new File("libreria.xml"));
        marshaller.marshal(lib1, System.out); // Se imprime en el XML

        System.out.println("XML generado correctamente\n");

        System.out.println("Leemos el XML...");
        // Unmarshalling: XML -> Objeto Java
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Libreria lib2 = (Libreria) unmarshaller.unmarshal(new File("libreria.xml"));
        System.out.println(lib2);
    }
}
