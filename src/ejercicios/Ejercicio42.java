package ejercicios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class Ejercicio42 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        // Solicitar datos de la librería
        System.out.println("=== Datos de la librería ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Lugar: ");
        String lugar = sc.nextLine();
        System.out.print("Código postal: ");
        String cp = sc.nextLine();

        // Crear objeto libros vacío
        List<Libro42> libros = new ArrayList<>();

        // Introducir libros hasta que el usuario escriba 'salir'
        System.out.println("\n=== Introducción de libros ===");
        while (true) {
            System.out.print("Título (o 'salir' para terminar): ");
            String titulo = sc.nextLine();
            if (titulo.equalsIgnoreCase("salir")) break;

            System.out.print("Autor: ");
            String autor = sc.nextLine();

            System.out.print("Editorial: ");
            String editorial = sc.nextLine();

            System.out.print("ISBN: ");
            String isbn = sc.nextLine();

            // Añadir libro a la lista
            libros.add(new Libro42(titulo, autor, editorial, isbn));
        }

        // Asignar la lista de libros a la librería
        Libreria42 libreria = new Libreria42(nombre, lugar, cp);
        libreria.setListaLibro(libros);
        sc.close();

        // Crear el archivo XML con JAXB
        try {
            // 1.Crear el contexto JAXB a partir de la clase Libreria
        	
//        	JAXBContext es el punto de entrada principal de JAXB.
//        	carga las anotaciones JAXB de las clases.
//        	Prepara internamente el modelo XML.
//        	Gestiona los convertidores (Marshaller y Unmarshaller)
        	
            JAXBContext context = JAXBContext.newInstance(Libreria42.class);

            // 2.Crear el marshaller (convierte objetos Java en XML)
            Marshaller marshaller = context.createMarshaller();

            // 3.Configurar formato de salida legible
//            Estas líneas son opciones de formato
            
//            Si es true, el XML se genera con sangrías y saltos de línea legibles.
//            Si es false, el XML se genera todo en una línea (compacto, sin formato).
            
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            // 4.Generar el fichero XML
            // Aquí ya convertimos el objeto en un fichero xml
            marshaller.marshal(libreria, new File("libreria.xml"));

            System.out.println("Archivo 'libreria.xml' creado correctamente.");

        } catch (JAXBException e) {
            e.printStackTrace();
        }
	}

}
