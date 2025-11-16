package ejercicios;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonaEj45 {

	@JsonProperty("DNI")
	private String DNI;
	private String nombre;
	private int edad;
	
	public PersonaEj45() {
		super();
	}

	public PersonaEj45(String DNI, String nombre, int edad) {
		super();
		this.DNI = DNI;
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "PersonaEj45 [DNI=" + DNI + ", nombre=" + nombre + ", edad=" + edad + "]";
	}
	
	
}
