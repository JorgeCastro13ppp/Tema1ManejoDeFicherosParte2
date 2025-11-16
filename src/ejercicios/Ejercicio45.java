package ejercicios;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Ejercicio45 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
	            // 1) Crear el String JSON
	            String jsonString = "{ \"DNI\": \"12345678A\", \"nombre\": \"Juan\", \"edad\": 30 }";

	            // 2) Crear el ObjectMapper (Jackson)
	            ObjectMapper mapper = new ObjectMapper();

	            // 3) Convertir el JSON en un objeto Persona
	            PersonaEj45 persona = mapper.readValue(jsonString, PersonaEj45.class);

	            // 4) Mostrar por pantalla
	            System.out.println(persona);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
