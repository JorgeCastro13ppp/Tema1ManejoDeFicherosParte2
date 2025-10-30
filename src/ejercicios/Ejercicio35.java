package ejercicios;

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
		// TODO Auto-generated method stub
		try {
			//Crear una instancia del DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			//Crear el DocumentBuilder a partir de la fábrica
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			//Crear un documento vacío
			Document doc = builder.newDocument();
			
			//Crear el nodo raíz <profesores>
			Element raiz = doc.createElement("profesores");
			doc.appendChild(raiz); //Añadimos la raíz al documento
			
			//Crear el nodo hijo <profesor>
			Element profesor = doc.createElement("profesor");
			
			//Añadir atributo al profesor grupo="2DAM"
			profesor.setAttribute("grupo", "2DAM");
			
			//Crear el nodo de texto y añadirlo dentro "Juan Pérez"
			profesor.appendChild(doc.createTextNode("Juan Pérez"));
			
			//Añadir el nodo profesor dentro de profesores
			raiz.appendChild(profesor);
			
			//Mostrar el resultado por consola
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			
			//Formatear la salida con sangrías
			t.setOutputProperty(OutputKeys.INDENT,"yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
			//Crear la fuente (documento DOM) y el resultado (salida por consola)
			DOMSource source = new DOMSource(doc);
			StreamResult console = new StreamResult(System.out);
			
			//Transformamos, convertimos el DOM en texto xml legible
			t.transform(source, console);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
