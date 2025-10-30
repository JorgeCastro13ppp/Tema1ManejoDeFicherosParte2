package ejercicios;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio34 {

	public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("direcciones.xml"));

            Element raiz = doc.getDocumentElement();
            System.out.println("Nodo: " + raiz.getNodeName());
            recorrerNodos(raiz, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void recorrerNodos(Node nodo, int nivel) {
        NodeList hijos = nodo.getChildNodes();

        for (int i = 0; i < hijos.getLength(); i++) {
            Node hijo = hijos.item(i);

            // Ignorar texto vacío o saltos de línea
            if (hijo.getNodeType() == Node.TEXT_NODE && hijo.getNodeValue().trim().isEmpty()) {
                continue;
            }

            // Solo procesamos nodos de tipo ELEMENT_NODE
            if (hijo.getNodeType() == Node.ELEMENT_NODE) {

                String tab = "  ".repeat(nivel); // sangría con dos espacios por nivel
                System.out.print(tab + "Nodo: " + hijo.getNodeName());

                // Mostrar atributos, si los hay
                if (hijo.hasAttributes()) {
                    NamedNodeMap atributos = hijo.getAttributes();
                    for (int j = 0; j < atributos.getLength(); j++) {
                        Node atributo = atributos.item(j);
                        System.out.print(" Atributo:" + atributo.getNodeName() + ":" + atributo.getNodeValue());
                    }
                }

                // Mostrar texto si no tiene elementos hijos
                if (!tieneElementosHijo(hijo)) {
                    String texto = hijo.getTextContent().trim();
                    if (!texto.isEmpty()) {
                        System.out.print(" " + texto);
                    }
                }

                System.out.println(); // salto de línea tras el nodo

                // Recursión solo en elementos hijos
                recorrerNodos(hijo, nivel + 1);
            }
        }
    }

    private static boolean tieneElementosHijo(Node nodo) {
        NodeList hijos = nodo.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            if (hijos.item(i).getNodeType() == Node.ELEMENT_NODE) {
                return true;
            }
        }
        return false;
    }

}
