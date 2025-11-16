package ejercicios;

public class LibroEj49 {

	private String titulo;
	private String autor;
	private String editorial;
	private String isbn;
	
	public LibroEj49() {
		
	}

	public LibroEj49(String titulo, String autor, String editorial, String isbm) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.isbn = isbm;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "LibroEj49 [titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial + ", isbn=" + isbn + "]";
	}
	
	
	
}
