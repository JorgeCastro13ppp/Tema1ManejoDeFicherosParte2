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
	
	// Usamos los getters igual que antes
	// Ahora convertimos un camnpo en una atributo XML en lugar de un nodo hijo
	// JAXB lo detecta porque está en el getter, y el nombre del atributo XML se genera igual que el nombre del campo 
	// (a menos que se especifique lo contrario
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
	// Agrupa una colección (una lista, en este caso List<Libro42>) dentro de un nodo contenedor.
	// Sin esta anotación, JAXB generaría todos los libros directamente dentro de <libreria>
	// Con @XmlElementWrapper(name = "ListaLibro"), se añade una etiqueta envolvente
	 
    @XmlElementWrapper(name = "ListaLibro")
    
//    Define el nombre de los elementos individuales dentro de la lista.
//    Por defecto JAXB usaría <listaLibro> (el nombre del campo),
//    pero con @XmlElement(name = "Libro") lo convierte en <Libro> (con mayúscula inicial).
    
    @XmlElement(name = "Libro")
	public List<Libro42> getListaLibro() {
		return listaLibro;
	}

	public void setListaLibro(List<Libro42> listaLibro) {
		this.listaLibro = listaLibro;
	}
    
    
}
