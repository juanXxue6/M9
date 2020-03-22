package coches;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Coches implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
    @Id @GeneratedValue
    Long id;
    private String modelo;
    private String matricula;
    private int kilometros;
    private Date fechaIntroduccion;
    private boolean necesitaReparacion;
    
	
	
	public Coches( String modelo,
     String matricula,
     int kilometros,
     boolean necesitaReparacion)
	{	
		this.modelo = modelo;
		this.matricula = matricula;
		this.kilometros = kilometros;
		this.fechaIntroduccion = new Date(System.currentTimeMillis());
		this.necesitaReparacion = necesitaReparacion;
	}
	
	
	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	public String getMatricula() {
		return matricula;
	}



	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}



	public int getKilometros() {
		return kilometros;
	}



	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}



	public Date getFechaIntroduccion() {
		return fechaIntroduccion;
	}



	public void setFechaCompra(Date fechaCompra) {
		this.fechaIntroduccion = fechaCompra;
	}



	public String isNecesitaReparacion() {
		if(necesitaReparacion)
		return "Si";
		else {
			return "No";
		}
	}
	
	public long getId() {
		return id;
	}



	public void setNecesitaReparacion(boolean necesitaReparacion) {
		this.necesitaReparacion = necesitaReparacion;
	}


	public String toString() {
		return  " Id " + getId() + " - " + " Modelo: " + getModelo() + " - " + " matricula: " + getMatricula() + " - " + 
			   " fechaIntroduccion " + getFechaIntroduccion()  + " - " + " kilometros " + getKilometros() + " - " + " Necesita reparación? " + isNecesitaReparacion() ;
	}

	
	
}
