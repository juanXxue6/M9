import java.io.Serializable;

public class Coche implements Serializable {
	
	String marca;
	String modelo;
	int a�o;
	String matricula;
	
	public Coche(String marca, String modelo, int a�o, String matricula) {
		this.marca = marca;
		this.modelo = modelo;
		this.a�o = a�o;
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

	public int getA�o() {
		return a�o;
	}

	public void setA�o(int a�o) {
		this.a�o = a�o;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matriula) {
		this.matricula = matriula;
	}

}
