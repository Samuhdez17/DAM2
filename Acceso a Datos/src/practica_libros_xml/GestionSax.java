package practica_libros_xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class GestionSax extends DefaultHandler {
    private ArrayList<String> nombres = new ArrayList<>();
    private ArrayList<String> autores = new ArrayList<>();
    private ArrayList<Integer> precios = new ArrayList<>();

    public GestionSax() {
        super();
    }

    public void startElement(String uri, String localName, String qName, Attributes atts) {
    }

    public void endElement(String uri, String localName, String qName) {
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        String contenido = new String(ch, start, length);
        contenido = contenido.replaceAll("[\t\n]", "");

        verificarFalsoContenido(contenido);
    }

    private static void verificarFalsoContenido(String contenido) {
        int contador = 0;
        for (int i = 0; i < contenido.length(); i++) {
            if (contenido.charAt(i) == ' ') {
                contador++;
            }
        }
        if (contador == contenido.length()) return;
    }
}
