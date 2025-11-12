package ejercicios;

import javax.xml.bind.annotation.XmlElement;

public class DireccionEj44 {

	private String via;
	private int numero;
	private String poblacion;
	private String cp;
	
	
	public DireccionEj44() {
		
	}


	public DireccionEj44(String via, int numero, String poblacion, String cp) {
		super();
		this.via = via;
		this.numero = numero;
		this.poblacion = poblacion;
		this.cp = cp;
	}

	@XmlElement
	public String getVia() {
		return via;
	}


	public void setVia(String via) {
		this.via = via;
	}

	@XmlElement
	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}

	@XmlElement
	public String getPoblacion() {
		return poblacion;
	}


	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	@XmlElement
	public String getCp() {
		return cp;
	}


	public void setCp(String cp) {
		this.cp = cp;
	}
	
	
}
