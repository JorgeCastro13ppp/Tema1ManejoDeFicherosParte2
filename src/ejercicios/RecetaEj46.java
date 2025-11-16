package ejercicios;

import java.util.List;

public class RecetaEj46 {

	private String nombre;
	private String tipo;
	private OrigenEj46 origen;
	private List<IngredienteEj46> ingredientes;
	
	public RecetaEj46() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public OrigenEj46 getOrigen() {
		return origen;
	}

	public void setOrigen(OrigenEj46 origen) {
		this.origen = origen;
	}

	public List<IngredienteEj46> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<IngredienteEj46> ingredientes) {
		this.ingredientes = ingredientes;
	}

	@Override
	public String toString() {
		return "RecetaEj46 [nombre=" + nombre + ", tipo=" + tipo + ", origen=" + origen + ", ingredientes="
				+ ingredientes + "]";
	}
	
	
	
}
