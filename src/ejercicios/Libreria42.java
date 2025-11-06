package ejercicios;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Clase raíz que representa la librería completa.
 * 
 * Anotaciones JAXB:
 *  - @XmlRootElement: define la raíz del documento XML.
 *  - @XmlAttribute: convierte atributos del objeto en atributos del nodo raíz.
 *  - @XmlElementWrapper: agrupa una lista dentro de un nodo contenedor.
 *  - @XmlElement: marca los elementos de la lista como subnodos.
 */
@XmlRootElement(name = "libreria")
public class Libreria42 {
	
	private String nombre;
    private String lugar;
    private String cp;
    private List<Libro42> listaLibro;
    
	public Libreria42() {
		this.listaLibro = new ArrayList<>();;
	}

	public Libreria42(String nombre, String lugar, String cp) {
		this.nombre = nombre;
		this.lugar = lugar;
		this.cp = cp;
		this.listaLibro = new ArrayList<>();;
	}

	@XmlAttribute
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlAttribute
	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	@XmlAttribute
	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	// Lista de libros agrupada en <ListaLibro>
    @XmlElementWrapper(name = "ListaLibro")
    @XmlElement(name = "Libro")
	public List<Libro42> getListaLibro() {
		return listaLibro;
	}

	public void setListaLibro(List<Libro42> listaLibro) {
		this.listaLibro = new ArrayList<>();;
	}
    
	
    
}
