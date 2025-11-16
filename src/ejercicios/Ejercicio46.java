package ejercicios;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Ejercicio46 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			//Leer el JSON mapeándolo a la clase recetario
			RecetarioEj46 recetario = mapper.readValue(new File("ej46recetas.json"), RecetarioEj46.class);
			
			System.out.println("=== LISTA DE RECETAS ===");

            for (RecetaEj46 r : recetario.getRecetas()) {
                System.out.println("\nReceta: " + r.getNombre());
                System.out.println("Tipo: " + r.getTipo());
                System.out.println("Origen: " + r.getOrigen().getPais() + " - " + r.getOrigen().getRegion());
                System.out.println("Ingredientes:");
                
                for (IngredienteEj46 ing : r.getIngredientes()) {
                    System.out.println("  - " + ing.getNombre() + ": " + ing.getCantidad());
                }
            }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
