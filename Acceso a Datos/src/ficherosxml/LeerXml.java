package ficherosxml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class LeerXml {
    public static void main(String[] args) throws ParserConfigurationException {
        try {
            File fichero = new File("ficherosxml/Empleados.xml");
            if (!fichero.exists()) {
                System.out.println("El fichero no existe");
                CrearXml.main(null);
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fichero);

            NodeList empleados = document.getElementsByTagName("empleado");

            System.out.println("Lista de empleados: ");
            for (int i = 0 ; i < empleados.getLength() ; i++) {
                Node nodo = empleados.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                    String id = elemento.getElementsByTagName("id").item(0).getTextContent();
                    String apellido = elemento.getElementsByTagName("apellido").item(0).getTextContent();
                    String depto = elemento.getElementsByTagName("depto").item(0).getTextContent();
                    String salario = elemento.getElementsByTagName("salario").item(0).getTextContent();

                    System.out.printf("%s %10s %4s %s\n", id, apellido, depto, salario);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
