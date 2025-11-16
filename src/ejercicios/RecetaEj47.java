package ejercicios;

public class RecetaEj47 {

	private String nombre;
	private String tipo;
	private int tiempo;
	
	public RecetaEj47() {
		super();
	}

	public RecetaEj47(String nombre, String tipo, int tiempo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.tiempo = tiempo;
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

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	@Override
	public String toString() {
		return "RecetaEj47 [nombre=" + nombre + ", tipo=" + tipo + ", tiempo=" + tiempo + "]";
	}
	
	
	
}
