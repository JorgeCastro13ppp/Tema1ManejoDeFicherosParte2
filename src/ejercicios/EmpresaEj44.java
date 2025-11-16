package ejercicios;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * Clase que representa una empresa energética.
 * 
 * Incluye información general, su dirección y la lista de trabajadores.
 */
@XmlRootElement(name = "empresa")
@XmlType(propOrder = {"direccion", "trabajadores"})

public class EmpresaEj44 {

	private String nie;
	private String nombre;
	private DireccionEj44 direccion;
	private List<TrabajadorEj44> trabajadores;
	
	public EmpresaEj44() {
		this.trabajadores=new ArrayList<>();
	}

	public EmpresaEj44(String nie, String nombre, DireccionEj44 direccion, List<TrabajadorEj44> trabajadores) {
		super();
		this.nie = nie;
		this.nombre = nombre;
		this.direccion = direccion;
		this.trabajadores = trabajadores;
	}

	@XmlAttribute
	public String getNie() {
		return nie;
	}

	public void setNie(String nie) {
		this.nie = nie;
	}

	@XmlAttribute
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@XmlElement(name="direccion")
	public DireccionEj44 getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionEj44 direccion) {
		this.direccion = direccion;
	}

	@XmlElementWrapper(name="trabajadores")
	@XmlElement(name="trabajador")
	public List<TrabajadorEj44> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<TrabajadorEj44> trabajadores) {
		this.trabajadores = trabajadores;
	}
	
	//Métodos de gestión de trabajadores
	public void addTrabajador(TrabajadorEj44 t) {
		trabajadores.add(t);
	}
	
	public TrabajadorEj44 buscarPorNif(String nif) {
		for(TrabajadorEj44 t : trabajadores) {
			if(t.getNif().equalsIgnoreCase(nif)) {
				return t;
			}
		}
		return null;
	}
	
	public boolean eliminarPorNif(String nif) {
		return trabajadores.removeIf(t->t.getNif().equalsIgnoreCase(nif));
	}
	
}
