package ejercicios;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Ejercicio33 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//Creamos una fábrica de constructores de documentos, (parser DOM)
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			//Creamos el constructor de documentos
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			//Parseamos (leemos) el documento XML y lo convertimos en un árbol DOM
			Document doc = builder.parse(new File("catalogo.xml")); 
			//Normalizamos documento, unimos nodos de texto adyacente, esto es una buena práctica
			doc.getDocumentElement().normalize();
			
			//Obtenemos el nodo raíz (documentElement)
			Element raiz = doc.getDocumentElement();
			
			//Mostramos el nombre del nodo raíz y su número de atributos
			System.out.println("Nombre del nodo raíz: "+raiz.getNodeName()+
								"\nNúmero de atributos del nodo raíz: "+raiz.getAttributes().getLength());
			
			//Obtenemos todos los nodos hijos del nodo raíz
			NodeList hijos = raiz.getChildNodes();
			
			//Recorrer los nodos hijos
			for(int i=0;i<hijos.getLength();i++) {
				Node hijo = hijos.item(i);//Obtenemos cada nodo
				
				//Filtramos solo los nodos que son elementos , ignoramos saltos de línea y texto
				if(hijo.getNodeType()==Node.ELEMENT_NODE) {
					System.out.println("Nodo hijo: "+hijo.getNodeName()+
										", tipo:  "+tipoNodo(hijo.getNodeType())+
										",número de atributos:  "+hijo.getAttributes().getLength());
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Método auxiliar que devuelve una descripción del tipo de nodo según su constante numérica 
	// Node.ELEMENT_NODE = 1, etc
	private static String tipoNodo(short tipo) {
		switch(tipo) {
		
		case Node.ELEMENT_NODE:
			return "ELEMENT_NODE";
		case Node.ATTRIBUTE_NODE:
			return "ATTRIBUTE_NODE";
		case Node.TEXT_NODE:
			return "TEXT_NODE";
		case Node.CDATA_SECTION_NODE:
			return "CDATA_SECTION_NODE";
		case Node.COMMENT_NODE:
			return "COMMENT_NODE";
		default:
			return "otro";
		}
	}
}






