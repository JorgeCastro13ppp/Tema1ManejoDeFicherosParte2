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

// La anotación @XmlType le dice a JAXB cómo debe estructurar el contenido interno del XML,
// es decir, en qué orden deben aparecer los elementos dentro de la etiqueta principal.

 @XmlType(propOrder = { "titulo", "autor", "editorial", "isbn" })
 
// Si no especificas propOrder, JAXB genera los elementos en orden alfabético por defecto, 
// no en el orden en que los declaras en tu clase.
 
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
	// JAXB accede a los valores a través de los objetos públicos getters and setters
	// En este caso solo debemos añadirlas a los getters porque JAXB usa el getter para pasar Java->XML
	// y usa setter para pasar de XML a Java
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
