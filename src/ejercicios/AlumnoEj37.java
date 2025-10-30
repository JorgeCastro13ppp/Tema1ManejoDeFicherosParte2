package ejercicios;

public class AlumnoEj37 {

	private int expediente;
	private String nombre;
	private double nota;
	
	public AlumnoEj37() {
		
	}

	public AlumnoEj37(int expediente, String nombre, double nota) {
		this.expediente = expediente;
		this.nombre = nombre;
		this.nota = nota;
	}

	public int getExpediente() {
		return expediente;
	}

	public void setExpediente(int expediente) {
		this.expediente = expediente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "AlumnoEj37 [expediente=" + expediente + ", nombre=" + nombre + ", nota=" + nota + "]";
	}
	
	
}
