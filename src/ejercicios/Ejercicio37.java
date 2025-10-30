package ejercicios;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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

public class Ejercicio37 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Integer,AlumnoEj37> alumnos= new TreeMap<>();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------Introducción de alumnos-------");
		
		while(true) {
			System.out.println("Número de expediente o 'salir' para terminar");
			String entrada = sc.nextLine();
			
			//Si el usuario escribe salir termina el bucle
			if(entrada.equalsIgnoreCase("salir")) {
				System.out.println("Has terminado.");
				break;
			}
			
			try {
				int expediente = Integer.parseInt(entrada);
				//Comprobamos duplicados
				if(alumnos.containsKey(expediente)) {
					System.out.println("Ya existe un alumno con ese expediente.");
					continue;
				}
				
				//Pedir el resto de datos
				System.out.println("Introduce el nombre:");
				String nombreAlumno = sc.nextLine();
				
				System.out.println("Introduce la nota: ");
				double nota = Double.parseDouble(sc.nextLine().replace(",", "."));
				
				//Guardar en el mapa
				alumnos.put(expediente, new AlumnoEj37(expediente,nombreAlumno,nota));
				System.out.println("Alumno añadido correctamente.");
				
			}catch(NumberFormatException n) {
				n.printStackTrace();
			}
		}
		
		sc.close();
		
		//Creamos el documento DOM
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			//Nodo raíz <alumnos>
			Element raiz = doc.createElement("alumnos");
			doc.appendChild(raiz);
			
			//Recorrer el mapa ordenado y crear nodos
			for(AlumnoEj37 a: alumnos.values()) {
				Element alumnoE = doc.createElement("alumno");
				
				Element expE = doc.createElement("numExpediente");
				expE.appendChild(doc.createTextNode(String.valueOf(a.getExpediente())));
				alumnoE.appendChild(expE);
				
				Element nombreE = doc.createElement("nombre");
				nombreE.appendChild(doc.createTextNode(a.getNombre()));
				alumnoE.appendChild(nombreE);
				
				Element notaE = doc.createElement("nota");
				notaE.appendChild(doc.createTextNode(String.valueOf(a.getNota())));
				alumnoE.appendChild(notaE);
				
				//Añadimos alumno al nodo raíz
				raiz.appendChild(alumnoE);
			}
			
			
			//Ahora guardamos el DOM en un fichero XML
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("notasAlumno.xml"));
			t.transform(source, result);
            System.out.println(" Archivo 'notasAlumno.xml' creado correctamente.");

			
		}catch(ParserConfigurationException | TransformerException pt) {
			pt.printStackTrace();
		}
	}

}






