package ejercicios;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.Scanner;

public class Ejercicio41 {

    // Constante con la ruta del fichero XML que contendrá los datos de los libros
    private static final String FILE_PATH = "libros.xml";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        // Bucle principal del menú
        do {
            System.out.println("\n=== GESTIÓN DE LIBROS ===");
            System.out.println("1. Añadir libro");
            System.out.println("2. Mostrar libros");
            System.out.println("3. Modificar libro (por ISBN)");
            System.out.println("4. Borrar libro (por ISBN)");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            
            // Leemos la opción del usuario desde teclado
            opcion = Integer.parseInt(sc.nextLine());

            // Estructura switch con sintaxis moderna de Java (Java 14+)
            // Cada opción ejecuta un método diferente según la acción seleccionada
            switch (opcion) {
                case 1 -> anadirLibro(sc);      // Añadir un nuevo libro
                case 2 -> mostrarLibros();      // Mostrar todos los libros
                case 3 -> modificarLibro(sc);   // Modificar datos de un libro
                case 4 -> borrarLibro(sc);      // Eliminar un libro
                case 0 -> System.out.println("👋 Saliendo del programa...");
                default -> System.out.println("⚠️ Opción no válida.");
            }

        } while (opcion != 0); // El menú se repite hasta que el usuario elige 0 (salir)

        sc.close(); // Cerramos el Scanner
    }

    // ===========================================================
    //  MÉTODOS PRINCIPALES (añadir, mostrar, modificar, borrar)
    // ===========================================================

    // 🟢 Añadir un nuevo libro al fichero XML
    private static void anadirLibro(Scanner sc) {
        try {
            // 1️⃣ Cargamos el documento XML existente o lo creamos si no existe
            Document doc = cargarDocumento();

            // Obtenemos el nodo raíz <libros>
            Element raiz = doc.getDocumentElement();

            // 2️⃣ Pedimos los datos al usuario
            System.out.print("ISBN: ");
            String isbn = sc.nextLine().trim(); // Eliminamos posibles espacios en blanco

            // Comprobamos si el libro con ese ISBN ya existe
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

            // 3️⃣ Creamos el nodo principal <libro> con su atributo isbn
            Element libro = doc.createElement("libro");
            libro.setAttribute("isbn", isbn);

            // Creamos los elementos hijos: <titulo>, <autor> y <ejemplares>
            Element tituloElem = doc.createElement("titulo");
            tituloElem.appendChild(doc.createTextNode(titulo)); // Añadimos el texto dentro del nodo

            Element autorElem = doc.createElement("autor");
            autorElem.appendChild(doc.createTextNode(autor));

            Element ejemplaresElem = doc.createElement("ejemplares");
            ejemplaresElem.appendChild(doc.createTextNode(ejemplares));

            // 4️⃣ Construimos la estructura completa dentro del nodo <libro>
            // Ejemplo final:
            // <libro isbn="9783161484100">
            //     <titulo>El Quijote</titulo>
            //     <autor>Miguel de Cervantes</autor>
            //     <ejemplares>5</ejemplares>
            // </libro>
            libro.appendChild(tituloElem);
            libro.appendChild(autorElem);
            libro.appendChild(ejemplaresElem);

            // Añadimos este nuevo libro al nodo raíz <libros>
            raiz.appendChild(libro);

            // 5️⃣ Guardamos los cambios en el fichero XML físico
            guardarDocumento(doc);
            System.out.println("✅ Libro añadido correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔵 Mostrar todos los libros almacenados en el XML
    private static void mostrarLibros() {
        try {
            // Comprobamos si el fichero existe antes de intentar leerlo
            File archivo = new File(FILE_PATH);
            if (!archivo.exists()) {
                System.out.println("⚠️ No existe el fichero 'libros.xml'.");
                return;
            }

            // 1️⃣ Cargamos el XML en memoria
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);

            // 2️⃣ Obtenemos todos los nodos <libro>
            NodeList lista = doc.getElementsByTagName("libro");
            System.out.println("\n=== LISTADO DE LIBROS ===");

            // 3️⃣ Recorremos cada nodo <libro> y mostramos su información
            for (int i = 0; i < lista.getLength(); i++) {
                Element libro = (Element) lista.item(i);

                // Accedemos al atributo y a los nodos hijos
                String isbn = libro.getAttribute("isbn");
                String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
                String autor = libro.getElementsByTagName("autor").item(0).getTextContent();
                String ejemplares = libro.getElementsByTagName("ejemplares").item(0).getTextContent();

                // Mostramos la información formateada
                System.out.println("ISBN: " + isbn);
                System.out.println("Título: " + titulo);
                System.out.println("Autor: " + autor);
                System.out.println("Ejemplares: " + ejemplares);
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🟡 Modificar los datos de un libro existente (buscando por ISBN)
    private static void modificarLibro(Scanner sc) {
        try {
            // Cargar documento
            Document doc = cargarDocumento();

            System.out.print("Introduce el ISBN del libro a modificar: ");
            String isbn = sc.nextLine().trim();

            // Buscar el libro dentro del XML
            Element libro = buscarLibroPorISBN(doc, isbn);
            if (libro == null) {
                System.out.println("⚠️ No se encontró ningún libro con ese ISBN.");
                return;
            }

            // Si el usuario deja vacío el campo, el valor no se cambia
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

            // Guardamos los cambios
            guardarDocumento(doc);
            System.out.println("✅ Libro modificado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔴 Eliminar un libro del XML buscando por ISBN
    private static void borrarLibro(Scanner sc) {
        try {
            // Cargar el documento DOM
            Document doc = cargarDocumento();

            System.out.print("Introduce el ISBN del libro a borrar: ");
            String isbn = sc.nextLine().trim();

            // Buscar el libro correspondiente
            Element libro = buscarLibroPorISBN(doc, isbn);
            if (libro == null) {
                System.out.println("⚠️ No se encontró ningún libro con ese ISBN.");
                return;
            }

            // Eliminamos el nodo del DOM con removeChild()
            libro.getParentNode().removeChild(libro);

            // Guardamos los cambios actualizados en el fichero XML
            guardarDocumento(doc);
            System.out.println("🗑️ Libro eliminado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===========================================================
    //  MÉTODOS AUXILIARES (cargar, guardar y buscar libros)
    // ===========================================================

    /**
     * 📘 Carga el documento XML desde el fichero.
     * Si el fichero no existe, lo crea automáticamente con una raíz <libros>.
     */
    private static Document cargarDocumento() throws Exception {
        File archivo = new File(FILE_PATH);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        if (!archivo.exists()) {
            // Crear un nuevo documento vacío
            Document nuevoDoc = builder.newDocument();

            // Crear la raíz <libros>
            Element raiz = nuevoDoc.createElement("libros");
            nuevoDoc.appendChild(raiz);

            // Guardar el nuevo fichero vacío
            guardarDocumento(nuevoDoc);
            System.out.println("📁 Fichero 'libros.xml' creado.");
            return nuevoDoc;
        } else {
            // Si ya existe, simplemente se carga
            return builder.parse(archivo);
        }
    }

    /**
     * 💾 Guarda el documento DOM en el archivo físico 'libros.xml'.
     * Utiliza Transformer para convertir el DOM en texto XML.
     */
    private static void guardarDocumento(Document doc) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();

        // Propiedades para formatear el XML de salida
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // activa la indentación
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // 4 espacios por nivel
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // codificación de salida

        // Origen (el DOM en memoria)
        DOMSource source = new DOMSource(doc);

        // Destino (archivo físico)
        StreamResult result = new StreamResult(new File(FILE_PATH));

        // Escribimos el XML
        transformer.transform(source, result);
    }

    /**
     * 🔍 Busca un libro por su atributo ISBN.
     * Devuelve el elemento <libro> si lo encuentra, o null si no existe.
     */
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
