package ejercicios;

// Librerías necesarias para trabajar con XML mediante DOM y transformarlo a texto
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Ejercicio35 {

	public static void main(String[] args) {
		try {
			// 1️⃣ Crear una instancia del DocumentBuilderFactory
			//    Esta clase proporciona métodos para obtener objetos que permiten construir árboles DOM.
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			// 2️⃣ Crear un objeto DocumentBuilder a partir de la fábrica
			//    El DocumentBuilder es el responsable de crear nuevos documentos o de parsear archivos XML.
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			// 3️⃣ Crear un documento DOM vacío (sin nodos todavía)
			Document doc = builder.newDocument();
			
			// 4️⃣ Crear el nodo raíz <profesores>
			//    Todo documento XML debe tener un solo nodo raíz.
			Element raiz = doc.createElement("profesores");
			
			// 5️⃣ Añadir la raíz al documento
			doc.appendChild(raiz);
			
			// 6️⃣ Crear un nodo hijo <profesor>
			Element profesor = doc.createElement("profesor");
			
			// 7️⃣ Añadir un atributo al nodo <profesor>: grupo="2DAM"
			profesor.setAttribute("grupo", "2DAM");
			
			// 8️⃣ Crear un nodo de texto con el nombre del profesor ("Juan Pérez")
			//    y añadirlo como contenido del elemento <profesor>
			profesor.appendChild(doc.createTextNode("Juan Pérez"));
			
			// 9️⃣ Insertar el nodo <profesor> dentro de la raíz <profesores>
			raiz.appendChild(profesor);
			
			// 🟢 En este punto el árbol DOM completo está así en memoria:
			// <profesores>
			//     <profesor grupo="2DAM">Juan Pérez</profesor>
			// </profesores>
			
			// 🔸 Ahora falta transformarlo en texto XML para mostrarlo o guardarlo.
			
			// 🔟 Crear un TransformerFactory para generar un Transformer
			//     que convierta el documento DOM a texto (XML legible)
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			
			// 11️⃣ Configurar el formato de salida con indentación (sangrías)
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			// Esta propiedad específica de Apache Xalan ajusta el número de espacios por nivel de sangría
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
			// 12️⃣ Crear las fuentes y destinos de la transformación
			// DOMSource: origen → el árbol DOM
			DOMSource source = new DOMSource(doc);
			
			// StreamResult: destino → en este caso la consola (System.out)
			StreamResult console = new StreamResult(System.out);
			
			// 13️⃣ Realizar la transformación: convertir el DOM a XML y mostrarlo
			t.transform(source, console);
			
		} catch (Exception e) {
			// Si ocurre cualquier error (configuración, parseo, o transformación), lo mostramos
			e.printStackTrace();
		}
	}
}
