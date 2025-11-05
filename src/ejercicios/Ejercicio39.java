package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio39 {

    public static void main(String[] args) {
        // Ruta del fichero de entrada (.txt) y salida (.xml)
        String inputPath = "cotizacion2.txt";   // Fichero de texto con datos
        String outputPath = "empresas.xml";     // Fichero XML de salida

        try (
            // 1️⃣ Abrir el fichero de texto con codificación UTF-8
            BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputPath), StandardCharsets.UTF_8))
        ) {

            // 2️⃣ Crear un documento DOM vacío en memoria
            // DocumentBuilderFactory y DocumentBuilder son clases necesarias para crear el árbol XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // 3️⃣ Crear el nodo raíz <empresas>
            Element raiz = doc.createElement("empresas");
            doc.appendChild(raiz);

            // 4️⃣ Leer el fichero línea por línea
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue; // Saltar líneas vacías

                // 5️⃣ Separar nombre y valor por el carácter ';'
                // Cada línea debe tener formato: "Empresa;Valor"
                String[] partes = linea.split(";");
                if (partes.length < 2) {
                    System.err.println("⚠️ Línea ignorada (formato incorrecto): " + linea);
                    continue;
                }

                // Asignamos el nombre y el valor a variables independientes
                String nombre = partes[0].trim();
                String valorRaw = partes[1].trim();

                // 6️⃣ Limpiar el valor para eliminar símbolos no deseados y normalizar formato
                valorRaw = valorRaw.replace("€", "").replace(",", ".").trim();
                // Ejemplo: "4,32€" → "4.32"

                // 7️⃣ Crear elementos XML correspondientes
                // <empresa>
                //     <nombre>Telefónica</nombre>
                //     <valor>4.32</valor>
                // </empresa>
                Element empresa = doc.createElement("empresa");

                Element nombreElem = doc.createElement("nombre");
                nombreElem.appendChild(doc.createTextNode(nombre));
                empresa.appendChild(nombreElem);

                Element valorElem = doc.createElement("valor");
                valorElem.appendChild(doc.createTextNode(valorRaw));
                empresa.appendChild(valorElem);

                // Añadimos el bloque <empresa> completo al nodo raíz
                raiz.appendChild(empresa);
            }

            // 8️⃣ Configurar el transformador para guardar el DOM en un archivo XML
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            // Configuración de formato de salida
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // activar sangrías
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // codificación
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // 4 espacios por nivel

            // 9️⃣ Definir el origen (el documento en memoria) y el destino (el archivo físico)
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(outputPath));

            // 10️⃣ Realizar la transformación → escribir el XML
            transformer.transform(source, result);

            System.out.println("✅ Archivo '" + outputPath + "' creado correctamente.");

        } catch (FileNotFoundException e) {
            // Error si el fichero de texto no existe
            System.err.println("❌ No se encontró el fichero: " + inputPath);
        } catch (ParserConfigurationException | TransformerException | IOException e) {
            // Captura de errores generales: parser, escritura, lectura, etc.
            e.printStackTrace();
        }
    }
}
