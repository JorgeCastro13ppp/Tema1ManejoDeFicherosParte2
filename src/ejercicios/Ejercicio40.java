package ejercicios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Ejercicio40 {

	public static void main(String[] args) {
        String inputPath = "personas.xml";
        String outputPath = "personas_sin_duplicados.txt";

        try {
            // 1️⃣ Cargar el XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(inputPath));
            doc.getDocumentElement().normalize();

            // 2️⃣ Obtener la lista de nodos <persona>
            NodeList listaPersonas = doc.getElementsByTagName("persona");

            // 3️⃣ Conjunto para evitar duplicados de teléfono
            Set<String> telefonos = new HashSet<>();

            // 4️⃣ Abrir el fichero de salida
            try (BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8))) {

                // 5️⃣ Recorrer cada persona
                for (int i = 0; i < listaPersonas.getLength(); i++) {
                    Node nodo = listaPersonas.item(i);

                    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                        Element persona = (Element) nodo;

                        // Obtener los valores de los campos
                        String nombre = getTexto(persona, "nombre");
                        String apellidos = getTexto(persona, "apellidos");
                        String telefono = getTexto(persona, "telefono");

                        // Limpiar los datos (por si vienen con espacios o ';')
                        nombre = nombre.replace(";", "").trim();
                        apellidos = apellidos.replace(";", "").trim();
                        telefono = telefono.replace(";", "").trim();

                        // 6️⃣ Comprobar duplicado
                        if (telefonos.contains(telefono)) {
                            System.out.println("⚠️ Duplicado ignorado: " + nombre + " (" + telefono + ")");
                            continue;
                        }

                        telefonos.add(telefono);

                        // 7️⃣ Escribir en el fichero
                        bw.write(nombre + " " + apellidos + ";" + telefono);
                        bw.newLine();
                    }
                }

                System.out.println("✅ Fichero '" + outputPath + "' creado sin duplicados.");

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para obtener el texto de una etiqueta
    private static String getTexto(Element elem, String etiqueta) {
        NodeList lista = elem.getElementsByTagName(etiqueta);
        if (lista.getLength() > 0) {
            return lista.item(0).getTextContent();
        }
        return "";
    }

}
