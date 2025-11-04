package ejercicios;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio38 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//Cargar el fichero xml en un objeto DOM
			File archivo = new File("concesionario.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(archivo);
			
			//Normalizar el documento
			doc.getDocumentElement().normalize();
			
			//Obtener list de nodosa
			NodeList listaCoches = doc.getElementsByTagName("coche");
			
			//Variables para almanacenar el coche más barato 
			String marcaBarata ="";
			String modeloBarato="";
			double precioMinimo = Double.MAX_VALUE;
			
			//Recorrer todos los nodos <coches>
			for(int i = 0; i<listaCoches.getLength();i++){
				Node nodoCoche = listaCoches.item(i);
				
				if(nodoCoche.getNodeType()==Node.ELEMENT_NODE) {
					Element coche = (Element) nodoCoche;
					
					//Obtener valores de cada etiqueta
					String marca = coche.getElementsByTagName("marca").item(0).getTextContent();
					String modelo = coche.getElementsByTagName("modelo").item(0).getTextContent();
					double precio = Double.parseDouble(coche.getElementsByTagName("precio").item(0).getTextContent()); 
					
					//Comparar precios
					if(precio < precioMinimo) {
						precioMinimo = precio;
						marcaBarata = marca;
						modeloBarato = modelo;
					}
				}
			}
			
			System.out.println("El coche más barato es:");
			System.out.println("Marca: "+marcaBarata);
			System.out.println("Modelo: "+modeloBarato);
			System.out.println("Precio: "+precioMinimo);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
