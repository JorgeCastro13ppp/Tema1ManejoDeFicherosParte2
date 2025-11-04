package ejercicios;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.Scanner;

public class Ejercicio41 {

    private static final String FILE_PATH = "libros.xml";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== GESTIÓN DE LIBROS ===");
            System.out.println("1. Añadir libro");
            System.out.println("2. Mostrar libros");
            System.out.println("3. Modificar libro (por ISBN)");
            System.out.println("4. Borrar libro (por ISBN)");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> anadirLibro(sc);
                case 2 -> mostrarLibros();
                case 3 -> modificarLibro(sc);
                case 4 -> borrarLibro(sc);
                case 0 -> System.out.println("👋 Saliendo del programa...");
                default -> System.out.println("⚠️ Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    // ==========================
    //  MÉTODOS PRINCIPALES
    // ==========================

    // 🟢 Añadir un libro nuevo
    private static void anadirLibro(Scanner sc) {
        try {
            Document doc = cargarDocumento();

            Element raiz = doc.getDocumentElement();

            System.out.print("ISBN: ");
            String isbn = sc.nextLine().trim();

            // Comprobar si ya existe
            if (buscarLibroPorISBN(doc, isbn) != null) {
                System.out.println("⚠️ Ya existe un libro con ese ISBN.");
                return;
            }

            System.out.print("Título: ");
            String titulo = sc.nextLine();

            System.out.print("Autor: ");
            String autor = sc.nextLine();

            System.out.print("Número de ejemplares: ");
            String ejemplares = sc.nextLine();

            // Crear elementos
            Element libro = doc.createElement("libro");
            libro.setAttribute("isbn", isbn);

            Element tituloElem = doc.createElement("titulo");
            tituloElem.appendChild(doc.createTextNode(titulo));

            Element autorElem = doc.createElement("autor");
            autorElem.appendChild(doc.createTextNode(autor));

            Element ejemplaresElem = doc.createElement("ejemplares");
            ejemplaresElem.appendChild(doc.createTextNode(ejemplares));

            // Estructura final
            libro.appendChild(tituloElem);
            libro.appendChild(autorElem);
            libro.appendChild(ejemplaresElem);
            raiz.appendChild(libro);

            guardarDocumento(doc);
            System.out.println("✅ Libro añadido correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔵 Mostrar todos los libros
    private static void mostrarLibros() {
        try {
            File archivo = new File(FILE_PATH);
            if (!archivo.exists()) {
                System.out.println("⚠️ No existe el fichero 'libros.xml'.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);

            NodeList lista = doc.getElementsByTagName("libro");
            System.out.println("\n=== LISTADO DE LIBROS ===");

            for (int i = 0; i < lista.getLength(); i++) {
                Element libro = (Element) lista.item(i);
                System.out.println("ISBN: " + libro.getAttribute("isbn"));
                System.out.println("Título: " + libro.getElementsByTagName("titulo").item(0).getTextContent());
                System.out.println("Autor: " + libro.getElementsByTagName("autor").item(0).getTextContent());
                System.out.println("Ejemplares: " + libro.getElementsByTagName("ejemplares").item(0).getTextContent());
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🟡 Modificar un libro por ISBN
    private static void modificarLibro(Scanner sc) {
        try {
            Document doc = cargarDocumento();

            System.out.print("Introduce el ISBN del libro a modificar: ");
            String isbn = sc.nextLine().trim();

            Element libro = buscarLibroPorISBN(doc, isbn);
            if (libro == null) {
                System.out.println("⚠️ No se encontró ningún libro con ese ISBN.");
                return;
            }

            System.out.print("Nuevo título (deja vacío para no cambiar): ");
            String nuevoTitulo = sc.nextLine();
            if (!nuevoTitulo.isEmpty()) {
                libro.getElementsByTagName("titulo").item(0).setTextContent(nuevoTitulo);
            }

            System.out.print("Nuevo autor (deja vacío para no cambiar): ");
            String nuevoAutor = sc.nextLine();
            if (!nuevoAutor.isEmpty()) {
                libro.getElementsByTagName("autor").item(0).setTextContent(nuevoAutor);
            }

            System.out.print("Nuevo número de ejemplares (deja vacío para no cambiar): ");
            String nuevoEjemplares = sc.nextLine();
            if (!nuevoEjemplares.isEmpty()) {
                libro.getElementsByTagName("ejemplares").item(0).setTextContent(nuevoEjemplares);
            }

            guardarDocumento(doc);
            System.out.println("✅ Libro modificado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔴 Borrar un libro por ISBN
    private static void borrarLibro(Scanner sc) {
        try {
            Document doc = cargarDocumento();

            System.out.print("Introduce el ISBN del libro a borrar: ");
            String isbn = sc.nextLine().trim();

            Element libro = buscarLibroPorISBN(doc, isbn);
            if (libro == null) {
                System.out.println("⚠️ No se encontró ningún libro con ese ISBN.");
                return;
            }

            libro.getParentNode().removeChild(libro);
            guardarDocumento(doc);
            System.out.println("🗑️ Libro eliminado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ==========================
    //  MÉTODOS AUXILIARES
    // ==========================

    // Cargar documento (crearlo si no existe)
    private static Document cargarDocumento() throws Exception {
        File archivo = new File(FILE_PATH);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        if (!archivo.exists()) {
            // Crear nuevo documento vacío
            Document nuevoDoc = builder.newDocument();
            Element raiz = nuevoDoc.createElement("libros");
            nuevoDoc.appendChild(raiz);
            guardarDocumento(nuevoDoc);
            System.out.println("📁 Fichero 'libros.xml' creado.");
            return nuevoDoc;
        } else {
            return builder.parse(archivo);
        }
    }

    // Guardar documento DOM a fichero
    private static void guardarDocumento(Document doc) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(FILE_PATH));
        transformer.transform(source, result);
    }

    // Buscar libro por ISBN
    private static Element buscarLibroPorISBN(Document doc, String isbn) {
        NodeList lista = doc.getElementsByTagName("libro");
        for (int i = 0; i < lista.getLength(); i++) {
            Element libro = (Element) lista.item(i);
            if (isbn.equals(libro.getAttribute("isbn"))) {
                return libro;
            }
        }
        return null;
    }
}
