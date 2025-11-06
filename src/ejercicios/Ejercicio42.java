package ejercicios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Ejercicio 42 - Crear fichero XML con JAXB
 * Asignatura: Acceso a Datos (2º DAM)
 *
 * Descripción:
 * El programa solicita al usuario los datos de una librería (nombre, lugar, CP)
 * y los de sus libros (título, autor, editorial, ISBN). 
 * Luego genera un fichero XML con la estructura solicitada utilizando JAXB.
 * 
 * Autor: Jorge Leonardo Castro Chiluisa
 * Fecha: 5 de Noviembre de 2025
 */

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
            // 1️⃣ Crear el contexto JAXB a partir de la clase Libreria
            JAXBContext context = JAXBContext.newInstance(Libreria42.class);

            // 2️⃣ Crear el marshaller (convierte objetos Java en XML)
            Marshaller marshaller = context.createMarshaller();

            // 3️⃣ Configurar formato de salida legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            // 4️⃣ Generar el fichero XML
            marshaller.marshal(libreria, new File("libreria.xml"));

            System.out.println("✅ Archivo 'libreria.xml' creado correctamente.");

        } catch (JAXBException e) {
            e.printStackTrace();
        }
	}

}
