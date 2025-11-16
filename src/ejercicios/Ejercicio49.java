package ejercicios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Ejercicio49 {

    private static final String FILE_NAME = "datosPaqui.json";
    private static ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 1️⃣ Cargar datos si existe el JSON
        List<LibroEj49> libros = cargarDatos();

        int opcion;

        do {
            System.out.println("\n=== GESTIÓN LIBRERÍA PAQUI ===");
            System.out.println("1. Nuevo libro");
            System.out.println("2. Modificar libro");
            System.out.println("3. Borrar libro");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> nuevoLibro(sc, libros);
                case 2 -> modificarLibro(sc, libros);
                case 3 -> borrarLibro(sc, libros);
                case 4 -> {
                    guardarDatos(libros);
                    System.out.println("📁 Datos guardados. Saliendo...");
                }
                default -> System.out.println("⚠️ Opción no válida");
            }

        } while (opcion != 4);

        sc.close();
    }

    // ============================================================
    //                 MÉTODOS DE PERSISTENCIA JSON
    // ============================================================

    private static List<LibroEj49> cargarDatos() {
        try {
            File f = new File(FILE_NAME);
            if (!f.exists()) {
                return new ArrayList<>();
            }
            return mapper.readValue(f, mapper.getTypeFactory().constructCollectionType(List.class, LibroEj49.class));

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void guardarDatos(List<LibroEj49> libros) {
        try {
            mapper.writeValue(new File(FILE_NAME), libros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ============================================================
    //                     OPCIÓN 1: NUEVO LIBRO
    // ============================================================

    private static void nuevoLibro(Scanner sc, List<LibroEj49> libros) {
        System.out.println("=== NUEVO LIBRO ===");
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Editorial: ");
        String editorial = sc.nextLine();
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();   // Se permiten duplicados

        libros.add(new LibroEj49(titulo, autor, editorial, isbn));
        System.out.println("📚 Libro añadido correctamente.");
    }

    // ============================================================
    //                OPCIÓN 2: MODIFICAR LIBRO POR ISBN
    // ============================================================

    private static void modificarLibro(Scanner sc, List<LibroEj49> libros) {
        System.out.print("Introduce el ISBN a modificar: ");
        String isbn = sc.nextLine();

        // Buscar libro
        LibroEj49 libro = libros.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);

        if (libro == null) {
            System.out.println("❌ No existe un libro con ese ISBN.");
            return;
        }

        System.out.println("Introduce los nuevos datos (Enter para no cambiar):");

        System.out.print("Nuevo título: ");
        String nuevoTitulo = sc.nextLine();
        if (!nuevoTitulo.isEmpty()) libro.setTitulo(nuevoTitulo);

        System.out.print("Nuevo autor: ");
        String nuevoAutor = sc.nextLine();
        if (!nuevoAutor.isEmpty()) libro.setAutor(nuevoAutor);

        System.out.print("Nueva editorial: ");
        String nuevaEditorial = sc.nextLine();
        if (!nuevaEditorial.isEmpty()) libro.setEditorial(nuevaEditorial);

        System.out.println("✏️ Libro modificado.");
    }

    // ============================================================
    //                OPCIÓN 3: BORRAR LIBRO POR ISBN
    // ============================================================

    private static void borrarLibro(Scanner sc, List<LibroEj49> libros) {
        System.out.print("Introduce el ISBN del libro a borrar: ");
        String isbn = sc.nextLine();

        boolean eliminado = libros.removeIf(lib -> lib.getIsbn().equals(isbn));

        if (eliminado) {
            System.out.println("🗑 Libro eliminado correctamente.");
        } else {
            System.out.println("❌ No se encontró ningún libro con ese ISBN.");
        }
    }
}
