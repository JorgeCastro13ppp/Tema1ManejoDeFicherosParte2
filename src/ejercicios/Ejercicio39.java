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
		// TODO Auto-generated method stub
		 String inputPath = "cotizacion2.txt";   // fichero de entrada
	        String outputPath = "empresas.xml";     // fichero XML de salida

	        try (BufferedReader br = new BufferedReader(
	                new InputStreamReader(new FileInputStream(inputPath), StandardCharsets.UTF_8))) {

	            // 1️⃣ Crear documento DOM vacío
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document doc = builder.newDocument();

	            // Crear nodo raíz <empresas>
	            Element raiz = doc.createElement("empresas");
	            doc.appendChild(raiz);

	            // 2️⃣ Leer línea a línea el fichero
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                linea = linea.trim();
	                if (linea.isEmpty()) continue; // saltar líneas vacías

	                // 3️⃣ Separar nombre y valor por ';'
	                String[] partes = linea.split(";");
	                if (partes.length < 2) {
	                    System.err.println("⚠️ Línea ignorada (formato incorrecto): " + linea);
	                    continue;
	                }

	                String nombre = partes[0].trim();
	                String valorRaw = partes[1].trim();

	                // 4️⃣ Limpiar el valor (quitar símbolos y espacios)
	                valorRaw = valorRaw.replace("€", "").replace(",", ".").trim();

	                // 5️⃣ Crear elementos XML
	                Element empresa = doc.createElement("empresa");

	                Element nombreElem = doc.createElement("nombre");
	                nombreElem.appendChild(doc.createTextNode(nombre));
	                empresa.appendChild(nombreElem);

	                Element valorElem = doc.createElement("valor");
	                valorElem.appendChild(doc.createTextNode(valorRaw));
	                empresa.appendChild(valorElem);

	                raiz.appendChild(empresa);
	            }

	            // 6️⃣ Guardar el DOM en fichero XML
	            TransformerFactory tf = TransformerFactory.newInstance();
	            Transformer transformer = tf.newTransformer();
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File(outputPath));
	            transformer.transform(source, result);

	            System.out.println("✅ Archivo '" + outputPath + "' creado correctamente.");

	        } catch (FileNotFoundException e) {
	            System.err.println("❌ No se encontró el fichero: " + inputPath);
	        } catch (ParserConfigurationException | TransformerException | IOException e) {
	            e.printStackTrace();
	        }
	}

}
