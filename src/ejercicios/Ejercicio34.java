package ejercicios;

// Importaciones necesarias para trabajar con XML y DOM
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
            // 1️⃣ Crear una fábrica de constructores de documentos DOM
            // DocumentBuilderFactory se utiliza para obtener una instancia que nos permita construir objetos DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // 2️⃣ Crear el objeto DocumentBuilder que construirá el árbol DOM en memoria
            DocumentBuilder builder = factory.newDocumentBuilder();

            // 3️⃣ Analizar (parsear) el fichero XML "direcciones.xml" y construir el DOM
            Document doc = builder.parse(new File("direcciones.xml"));

            // 4️⃣ Obtener el nodo raíz del documento (en este caso <direcciones>)
            Element raiz = doc.getDocumentElement();

            // 5️⃣ Mostrar el nombre del nodo raíz por consola
            System.out.println("Nodo: " + raiz.getNodeName());

            // 6️⃣ Llamar al método recursivo que recorrerá todo el árbol DOM
            //    Se pasa la raíz y el nivel de indentación inicial (1)
            recorrerNodos(raiz, 1);

        } catch (Exception e) {
            // Captura cualquier excepción producida por errores de lectura o parseo del XML
            e.printStackTrace();
        }
    }

    /**
     * Método recursivo que recorre el árbol DOM mostrando información de cada nodo.
     * @param nodo  Nodo actual a procesar
     * @param nivel Nivel de profundidad (para aplicar sangrías visuales)
     */
    private static void recorrerNodos(Node nodo, int nivel) {
        // Obtener la lista de nodos hijo del nodo actual
        NodeList hijos = nodo.getChildNodes();

        // Recorrer todos los hijos
        for (int i = 0; i < hijos.getLength(); i++) {
            Node hijo = hijos.item(i);

            // 🔸 Ignorar nodos de texto vacíos o con solo espacios/saltos de línea
            // (Estos aparecen entre etiquetas cuando el XML tiene formato legible)
            if (hijo.getNodeType() == Node.TEXT_NODE && hijo.getNodeValue().trim().isEmpty()) {
                continue;
            }

            // 🔸 Solo procesamos nodos de tipo ELEMENT_NODE (es decir, etiquetas)
            if (hijo.getNodeType() == Node.ELEMENT_NODE) {

                // Crear una sangría proporcional al nivel de profundidad (2 espacios por nivel)
                String tab = "  ".repeat(nivel);

                // Mostrar el nombre del nodo
                System.out.print(tab + "Nodo: " + hijo.getNodeName());

                // Si el nodo tiene atributos, los mostramos junto al nombre del nodo
                if (hijo.hasAttributes()) {
                    NamedNodeMap atributos = hijo.getAttributes();

                    // Recorremos todos los atributos y mostramos su nombre y valor
                    for (int j = 0; j < atributos.getLength(); j++) {
                        Node atributo = atributos.item(j);
                        System.out.print(" Atributo:" + atributo.getNodeName() + ":" + atributo.getNodeValue());
                    }
                }

                // Si el nodo no tiene otros elementos dentro (solo texto), mostramos ese texto
                if (!tieneElementosHijo(hijo)) {
                    String texto = hijo.getTextContent().trim();
                    if (!texto.isEmpty()) {
                        System.out.print(" " + texto);
                    }
                }

                // Salto de línea al final de cada nodo para mantener formato limpio
                System.out.println();

                // Llamada recursiva para procesar los hijos del nodo actual
                // Solo se hace si el hijo es un ELEMENT_NODE (evita repetir texto)
                recorrerNodos(hijo, nivel + 1);
            }
        }
    }

    /**
     * Comprueba si un nodo tiene hijos que sean elementos (ELEMENT_NODE).
     * Sirve para saber si un nodo contiene otros nodos o solo texto.
     */
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
