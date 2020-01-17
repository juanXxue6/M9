import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class main {
	
	static Scanner teclado = new Scanner (System.in);
	

public static void escribirObjeto()throws IOException {
	
	//Camp variable tipus Comarca
	Coche coche;
	//Declaració del fitxer
	File fitxer = new File("C:/Users/juan/eclipse-workspace/M6-UF1-EX2/coches.txt");
	//Crea el flux de sortida
	FileOutputStream fileout = new FileOutputStream(fitxer, true);
	//Connectar el flux de bytes al flux de dades
	ObjectOutputStream dataOutCohe = new ObjectOutputStream(fileout);
	//Les dades per generar els objectes Comarca
	//Recorre els arrays
	
	String marca;
	String modelo;
	int año;
	String matricula;
	
	System.out.println("Introdue la marca del coche");
	marca = teclado.next();
	
	System.out.println("Introdue el modelo del coche");
	modelo = teclado.next();
	
	System.out.println("Introdue el año del coche");
	año = teclado.nextInt();
	
	System.out.println("Introdue la matricula del coche");
	matricula = teclado.next();
	
		coche = new Coche(marca, modelo, año, matricula);
		dataOutCohe.writeObject(coche);//L'escriu al fixer
	
		System.out.println("¡coche guardado!");
		System.out.println("");
		
	dataOutCohe.close();//Tanca el stream de sortida
	
	

}
	

public static void LeerObjeto() throws IOException, ClassNotFoundException {
		ObjectInputStream dataInCoche;
		//Camp variable tipus Comarca
		Coche coche;
		
		//Declaració del fitxer
		File fitxer = new File("C:/Users/juan/eclipse-workspace/M6-UF1-EX2/coches.txt");
		//Crea el flux d'entrada
		FileInputStream filein = new FileInputStream(fitxer);
		//Connectar el flux de bytes al flux de dades

		System.out.println("---------------------");
		try {
			while (true){//Llegeix el fitxer
				
				 dataInCoche = new ObjectInputStream(filein);
				
				coche = (Coche) dataInCoche.readObject();
				
				System.out.println("Marca: " +coche.getMarca()  + 
				" \nModelo: " +coche.getModelo()  + 
				" \nAño: " +coche.getAño()  + 
				" \nMatricula: " +coche.getMatricula());
				System.out.println("---------------------");
			}
		
		} catch (EOFException eo) {}
	
	}


public static void LeerObjetoFiltrado(String campo, String parametro) throws IOException, ClassNotFoundException {
	ObjectInputStream dataInCoche;
	//Camp variable tipus Comarca
	Coche coche;
	boolean enseñar = false;
	boolean noData = true;
	
	//Declaració del fitxer
	File fitxer = new File("C:/Users/juan/eclipse-workspace/M6-UF1-EX2/coches.txt");
	//Crea el flux d'entrada
	FileInputStream filein = new FileInputStream(fitxer);
	//Connectar el flux de bytes al flux de dades

	System.out.println("---------------------");
	try {
		while (true){//Llegeix el fitxer
			
			 dataInCoche = new ObjectInputStream(filein);
			
			coche = (Coche) dataInCoche.readObject();
			
				if(campo.equalsIgnoreCase("marca")) {
					String marca = coche.getMarca();
						if(marca.equalsIgnoreCase(parametro)) {
							enseñar = true;
							noData = false;
						}
						
				}
				
				if(campo.equalsIgnoreCase("modelo")) {
					String modelo = coche.getModelo();
					if(modelo.equalsIgnoreCase(parametro)) {
						enseñar = true;
						noData = false;
					}
					
				}
				if(campo.equalsIgnoreCase("matricula")) {
					String matricula = coche.getMatricula();
					if(matricula.equalsIgnoreCase(parametro)) {
						enseñar = true;
						noData = false;
					}
					
					
				}
				if(campo.equalsIgnoreCase("año")) {
					int año = coche.getAño();
					int añoParametro = Integer.parseInt(parametro);
	
					if(año == añoParametro) {
						enseñar = true;
						noData = false;
					}
				}
						
				if(enseñar) {
				System.out.println("Marca: " +coche.getMarca()  + 
				" \nModelo: " +coche.getModelo()  + 
				" \nAño: " +coche.getAño()  + 
				" \nMatricula: " +coche.getMatricula());
				System.out.println("---------------------");
				enseñar = false;
				}
				
			}
		

			
	} catch (EOFException eo) {}

	if(noData == true) {
		System.out.println("Datos no encontrados");
		System.out.println("---------------------");
	}
}



	
public static void menu() {
	System.out.println("Elige una opcion (introducir el numero)");
	System.out.println("1. Crear coche y guardar");
	System.out.println("2. enseñar coches");
	System.out.println("3. buscar coches por un campo");
	System.out.println("4. salir");
}

public static void campo() {
	System.out.println("Introduzca el campo por el cual buscar");
	System.out.println("marca");
	System.out.println("modelo");
	System.out.println("año");
	System.out.println("matricula");
}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
			
		boolean programa = true;
		
		while(programa) {
		menu();
		int opcion = teclado.nextInt();
		
			if(opcion == 1) {
				escribirObjeto();
			}else if(opcion == 2) {
				LeerObjeto();
			}else if(opcion == 3) {
				campo();
				String campo = teclado.next();
				System.out.println("Introduzca el parametro");
				String parametro = teclado.next();
				LeerObjetoFiltrado(campo, parametro);
				
			}else if(opcion == 4) {
				programa = false;
				System.out.println("Programa finalizado");
			}else {
				System.out.println("Elija una opcion correcta");
			}
		}
		
	}

	

	
	
}
