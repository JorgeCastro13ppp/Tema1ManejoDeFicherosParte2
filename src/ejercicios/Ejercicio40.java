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
        // Rutas de entrada y salida
        String inputPath = "personas.xml";
        String outputPath = "personas_sin_duplicados.txt";

        try {
            // 1️⃣ Cargar el fichero XML en memoria como un documento DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(inputPath));

            // Normalizar el documento (elimina espacios y nodos de texto redundantes)
            doc.getDocumentElement().normalize();

            // 2️⃣ Obtener todos los elementos <persona> del documento
            NodeList listaPersonas = doc.getElementsByTagName("persona");

            // 3️⃣ Crear un conjunto (Set) para almacenar los teléfonos y evitar duplicados
            Set<String> telefonos = new HashSet<>();

            // 4️⃣ Crear el BufferedWriter para escribir el fichero de salida en UTF-8
            try (BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8))) {

                // 5️⃣ Recorrer cada nodo <persona>
                for (int i = 0; i < listaPersonas.getLength(); i++) {
                    Node nodo = listaPersonas.item(i);

                    // Solo procesamos nodos de tipo ELEMENT_NODE (descartamos texto o comentarios)
                    if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                        Element persona = (Element) nodo;

                        // 6️⃣ Obtener el texto de las etiquetas hijas
                        // Utilizamos el método auxiliar getTexto() definido más abajo
                        String nombre = getTexto(persona, "nombre");
                        String apellidos = getTexto(persona, "apellidos");
                        String telefono = getTexto(persona, "telefono");

                        // 7️⃣ Limpiar los datos (por si vienen con caracteres extra)
                        nombre = nombre.replace(";", "").trim();
                        apellidos = apellidos.replace(";", "").trim();
                        telefono = telefono.replace(";", "").trim();

                        // 8️⃣ Verificar si el teléfono ya fue añadido (duplicado)
                        if (telefonos.contains(telefono)) {
                            System.out.println("⚠️ Duplicado ignorado: " + nombre + " (" + telefono + ")");
                            continue; // Saltar esta persona
                        }

                        // Si no es duplicado, lo añadimos al conjunto
                        telefonos.add(telefono);

                        // 9️⃣ Escribir la información en el fichero de salida
                        // Formato: "Nombre Apellidos;Teléfono"
                        bw.write(nombre + " " + apellidos + ";" + telefono);
                        bw.newLine(); // salto de línea
                    }
                }

                // 10️⃣ Confirmación de que el fichero se generó correctamente
                System.out.println("✅ Fichero '" + outputPath + "' creado sin duplicados.");

            } catch (IOException e) {
                // Manejo de errores de escritura
                e.printStackTrace();
            }

        } catch (Exception e) {
            // Captura de cualquier otra excepción: lectura XML, parseo, etc.
            e.printStackTrace();
        }
    }

    /**
     * Método auxiliar que obtiene el contenido de texto de una etiqueta dentro de un elemento XML.
     * @param elem     Elemento padre (por ejemplo, <persona>)
     * @param etiqueta Nombre de la etiqueta hija (por ejemplo, "telefono")
     * @return Texto contenido en la etiqueta, o cadena vacía si no existe.
     */
    private static String getTexto(Element elem, String etiqueta) {
        NodeList lista = elem.getElementsByTagName(etiqueta);
        if (lista.getLength() > 0) {
            return lista.item(0).getTextContent();
        }
        return "";
    }
}
