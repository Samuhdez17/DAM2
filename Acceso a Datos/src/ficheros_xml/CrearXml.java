package ficheros_xml;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CrearXml {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            DOMImplementation implementation = builder.getDOMImplementation();

            Document document = implementation.createDocument(null, "Empleados", null);
            document.setXmlVersion("1.0");

            File fichero = new File("ficheros_xml/AleatorioEmple.dat");
            if (!fichero.exists()) {
                System.out.println("El fichero no existe");
                CrearDat.main(null);
            }

            try (RandomAccessFile raf = new RandomAccessFile("ficheros_xml/AleatorioEmple.dat", "r")){
                while (raf.getFilePointer() < raf.length()) {
                    int id = raf.readInt();
                    String apellido = raf.readUTF();
                    int depto = raf.readInt();
                    double salario = raf.readDouble();

                    Element raiz = document.createElement("empleado");

                    escribirID(document, id, raiz);

                    escribirApellido(document, apellido, raiz);

                    escribirDepartamento(document, depto, raiz);

                    escribirSalario(document, salario, raiz);

                    document.getDocumentElement().appendChild(raiz);
                }

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult("ficheros_xml/Empleados.xml");

                transformer.transform(source, result);
                System.out.println("Archivo creado correctamente");

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (TransformerConfigurationException e) {
                throw new RuntimeException(e);
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirSalario(Document document, double salario, Element raiz) {
        Element salarioElement = document.createElement("salario");
        salarioElement.setTextContent(String.valueOf(salario));
        raiz.appendChild(salarioElement);
    }

    private static void escribirDepartamento(Document document, int depto, Element raiz) {
        Element deptoElement = document.createElement("depto");
        deptoElement.setTextContent(String.valueOf(depto));
        raiz.appendChild(deptoElement);
    }

    private static void escribirApellido(Document document, String apellido, Element raiz) {
        Element apellidoElement = document.createElement("apellido");
        apellidoElement.setTextContent(apellido);
        raiz.appendChild(apellidoElement);
    }

    private static void escribirID(Document document, int id, Element raiz) {
        Element idElement = document.createElement("id");
        idElement.setTextContent(String.valueOf(id));
        raiz.appendChild(idElement);
    }
}
