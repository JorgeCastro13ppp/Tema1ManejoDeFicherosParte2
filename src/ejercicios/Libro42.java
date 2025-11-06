package ejercicios;

import javax.xml.bind.annotation.XmlType;

import com.sun.xml.txw2.annotation.XmlElement;

/**
 * Clase que representa un libro individual dentro de la librería.
 * 
 * Anotaciones JAXB:
 *  - @XmlType: define el orden de los elementos hijos en el XML.
 *  - @XmlElement: marca los atributos que se serializarán como nodos XML.
 */
@XmlType(propOrder = { "titulo", "autor", "editorial", "isbn" })

public class Libro42 {

	private String titulo;
    private String autor;
    private String editorial;
    private String isbn;

    // Constructor vacío (obligatorio para JAXB)
    public Libro42() {
    	
    }

	public Libro42(String titulo, String autor, String editorial, String isbn) {
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.isbn = isbn;
	}
	
	// Getters y setters con anotaciones JAXB
	@XmlElement

	public String getTitulo() {
		return titulo;
	}
	@XmlElement
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@XmlElement
	public String getAutor() {
		return autor;
	}
	@XmlElement
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
    
    
    
}
