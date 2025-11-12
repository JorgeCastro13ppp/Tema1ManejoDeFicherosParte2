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
}
