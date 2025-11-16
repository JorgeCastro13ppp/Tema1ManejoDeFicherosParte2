package ejercicios;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Ejercicio47 {

    private static final String FILE_PATH = "ej47recetas.json";
    private static ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RecetarioEj47 recetario = cargarRecetario();

        int opcion;

        do {
            System.out.println("\n=== GESTIÓN DE RECETAS ===");
            System.out.println("1. Nueva receta");
            System.out.println("2. Mostrar recetas");
            System.out.println("3. Borrar receta");
            System.out.println("0. Salir");
            System.out.print("Elige opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> nuevaReceta(sc, recetario);
                case 2 -> mostrarRecetas(recetario);
                case 3 -> borrarReceta(sc, recetario);
                case 0 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("⚠ Opción no válida");
            }

        } while (opcion != 0);

        sc.close();
    }

    // ======================
    //      MÉTODOS
    // ======================

    // Cargar JSON o crear fichero vacío
    private static RecetarioEj47 cargarRecetario() {
        try {
            File f = new File(FILE_PATH);
            if (!f.exists()) {
                return new RecetarioEj47();  // si no existe devolvemos uno vacío
            }
            return mapper.readValue(f, RecetarioEj47.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new RecetarioEj47();
        }
    }

    // Guardar JSON
    private static void guardarRecetario(RecetarioEj47 r) {
        try {
            mapper.writeValue(new File(FILE_PATH), r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =====================
    // OPCIÓN 1: Añadir receta
    // =====================
    private static void nuevaReceta(Scanner sc, RecetarioEj47 r) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Tipo: ");
        String tipo = sc.nextLine();

        System.out.print("Tiempo (min): ");
        int tiempo = Integer.parseInt(sc.nextLine());

        r.getRecetas().add(new RecetaEj47(nombre, tipo, tiempo));
        guardarRecetario(r);

        System.out.println("✅ Receta añadida.");
    }

    // =====================
    // OPCIÓN 2: Mostrar recetas
    // =====================
    private static void mostrarRecetas(RecetarioEj47 r) {
        List<RecetaEj47> lista = r.getRecetas();

        if (lista.isEmpty()) {
            System.out.println("⚠ No hay recetas registradas.");
            return;
        }

        System.out.println("\n=== LISTA DE RECETAS ===");
        for (RecetaEj47 rec : lista) {
            System.out.println(rec);
        }
    }

    // =====================
    // OPCIÓN 3: Borrar receta
    // =====================
    private static void borrarReceta(Scanner sc, RecetarioEj47 r) {
        System.out.print("Introduce el nombre de la receta a borrar: ");
        String nombre = sc.nextLine();

        boolean eliminado = r.getRecetas().removeIf(rec -> rec.getNombre().equalsIgnoreCase(nombre));

        if (eliminado) {
            guardarRecetario(r);
            System.out.println("🗑 Receta eliminada.");
        } else {
            System.out.println("⚠ No se encontró esa receta.");
        }
    }
}
