package ejercicios;

public class EmpresaEj48 {
	
	private String nombre;
	private double precio;
	
	public EmpresaEj48() {
		super();
	}

	public EmpresaEj48(String nombre, double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "nombre:" + nombre + ", precio:" + precio + "";
	}
	
	
	
}
