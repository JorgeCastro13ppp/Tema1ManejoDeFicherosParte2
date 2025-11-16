package ejercicios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Ejercicio50 {

    public static void main(String[] args) {

        try {
            // 1️⃣ Cargar el XML
            File xml = new File("concesionario.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml);

            doc.getDocumentElement().normalize();

            // 2️⃣ Lista donde guardaremos coches
            List<CocheEj50> coches = new ArrayList<>();

            NodeList listaNodos = doc.getElementsByTagName("coche");

            for (int i = 0; i < listaNodos.getLength(); i++) {
                Node nodo = listaNodos.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element coche = (Element) nodo;

                    String id = coche.getAttribute("id");
                    String marca = coche.getElementsByTagName("marca").item(0).getTextContent();
                    String modelo = coche.getElementsByTagName("modelo").item(0).getTextContent();
                    String precio = coche.getElementsByTagName("precio").item(0).getTextContent();

                    coches.add(new CocheEj50(id, marca, modelo, precio));
                }
            }

            // 3️⃣ Preparar objeto raíz para JSON
            ConcesionarioWrapperEj50 wrapper = new ConcesionarioWrapperEj50(coches);
            ConcesionarioRootEj50 root = new ConcesionarioRootEj50(wrapper);

            // 4️⃣ Crear JSON con Jackson
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

            mapper.writeValue(new File("concesionario.json"), root);

            System.out.println("✅ Archivo 'concesionario.json' creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
