package ejercicios;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Clase que representa a un trabajador dentro de una empresa.
 * 
 * Anotaciones JAXB:
 * - @XmlType: define el orden en que aparecen los elementos dentro del XML.
 * - @XmlElement: marca los atributos que se serializan como elementos XML.
 */
@XmlType(propOrder = {"nif", "nombre", "cargo"})

public class TrabajadorEj44 {

	private String nif;
	private String nombre;
	private String cargo;
	
	public TrabajadorEj44() {
		super();
	}

	public TrabajadorEj44(String nif, String nombre, String cargo) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.cargo = cargo;
	}
	
	@XmlElement
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}
	@XmlElement
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	
	
}
