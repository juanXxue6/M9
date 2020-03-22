package coches;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Propietarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id @GeneratedValue
    Long id;
    private String nombre;
    private int edad;
    

    public Propietarios(String nombre, int edad) {
    	this.nombre = nombre;
    	this.edad = edad;
    }
    
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
    
	
	
	
}
