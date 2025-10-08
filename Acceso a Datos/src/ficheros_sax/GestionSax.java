package ficheros_sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionSax extends DefaultHandler {
    public  GestionSax() {
        super();
    }

    public void startDocument() {
        System.out.println("Comienzo del documento");
    }

    public void endDocument() {
        System.out.println("Fin del documento");
    }

    public void startElement(String uri, String localName, String qName, Attributes atts) {
        System.out.printf("\tComienzo del elemento: %s\n", qName);
    }

    public void endElement(String uri, String localName, String qName) {
        System.out.printf("\tFin del elemento: %s\n", qName);
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        String contenido = new String(ch, start, length);
        contenido = contenido.replaceAll("[\t\n]", "");

        System.out.printf("\tContenido: %s, %d\n", contenido, contenido.length());
    }
}
