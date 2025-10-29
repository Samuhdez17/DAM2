package ficheros_sax;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws SAXException, IOException {
        XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
        GestionSax gSax = new GestionSax();
        procesadorXML.setContentHandler(gSax);

        InputSource fileXml = new InputSource("src/ficheros_sax/fichero.xml");
        procesadorXML.parse(fileXml);
    }
}
