import java.io.Serializable;

public class Coche implements Serializable {
	
	String marca;
	String modelo;
	int año;
	String matricula;
	
	public Coche(String marca, String modelo, int año, String matricula) {
		this.marca = marca;
		this.modelo = modelo;
		this.año = año;
		this.matricula = matricula;
	}
	
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matriula) {
		this.matricula = matriula;
	}

}
