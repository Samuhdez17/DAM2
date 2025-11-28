package practica_libros_xml;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws SAXException, IOException {
        String rutaFichero = "practica_libros_xml/pedidos.xml";

        XMLReader procesador = XMLReaderFactory.createXMLReader();
        GestionSax gSax = new GestionSax();
        procesador.setContentHandler(gSax);

        InputSource ficheroXml = new InputSource(rutaFichero);
        procesador.parse(ficheroXml);
    }
}
