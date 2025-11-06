package ejercicios;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Ejercicio43 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			Creamos el contexto java con la clase raíz
			JAXBContext context = JAXBContext.newInstance(Libreria42.class);
			
//			Creamos el unmarshaller para convertir de xml a java
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
//			Leer el fichero xml
			
			File archivo = new File("libreria.xml");
			if(!archivo.exists()) {
				System.out.println("El archivo no existe o no se encotró");
				return;
			}
			
//			Aquí convertimos el xml en un objeto java Libreria42
			Libreria42 libreria = (Libreria42) unmarshaller.unmarshal(archivo);
			
//			Mostrar los datos de librería por pantalla
			
			System.out.println("Nombre de la librería: " +libreria.getNombre());
			System.out.println("Nombre del lugar: "+libreria.getLugar());
			System.out.println("Código postal: "+libreria.getCp());
			System.out.println("Libros disponibles: ");
			
//			Recorrer la librería para mostrar los libros
			for(Libro42 l: libreria.getListaLibro()) {
				System.out.println("---------");
				System.out.println("Nombre: "+l.getTitulo());
				System.out.println("Autor: "+l.getAutor());
				System.out.println("Editorial: "+l.getEditorial());
				System.out.println("ISBN: "+l.getIsbn());
			}
			
		}catch(JAXBException e) {
			e.printStackTrace();
		}
	}

}
