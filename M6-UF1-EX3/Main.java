import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;



public class Main {
	
	static Scanner teclado = new Scanner (System.in);
	 static int i = 0;

	public static void EscribirFicheroAleatorio() throws IOException {
		File fitxer = new File("C:/Users/juan/eclipse-workspace/m6-uf1-ex3/ficha.txt");
		// Crea un flux (stream) d'arxiu d'accés aleatori per llegir
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer,"rw");
		// Les dades per inserir
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("introduzca el nombre: ");
		String nombre = teclado.next();
		
		System.out.println("introduzca el apellido: ");
		String apellido = teclado.next();
		
		System.out.println("introduzca el dni: ");
		String dni = teclado.next();
		
		System.out.println("introduzca el numero de telefono: ");
		int telefono = teclado.nextInt();
		
		// Construeix un buffer (memòria intermèdia) de strings
		StringBuffer buffer = null;
		aleatoriFile.seek(aleatoriFile.length());
		
			aleatoriFile.writeInt(i + 1);
			i++;
			buffer = new StringBuffer(nombre);
			buffer.setLength(25);
			aleatoriFile.writeChars(buffer.toString());
			
			buffer = new StringBuffer(apellido);
			buffer.setLength(25);
			aleatoriFile.writeChars(buffer.toString());
			
		
			buffer = new StringBuffer(dni);
			buffer.setLength(25);
			aleatoriFile.writeChars(buffer.toString());
			// 1 enter ocupa 4 bytes
			
			aleatoriFile.writeInt(telefono);

			System.out.println("Usuario creado correctamente");
			System.out.println("-----------------------");
			System.out.println();
		aleatoriFile.close();
	}

	public static void LeerFichero() throws IOException {
		File fitxer = new File("C:/Users/juan/eclipse-workspace/m6-uf1-ex3/ficha.txt");
		// Crea un flux (stream) d'arxiu d'accés aleatori només lectura
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "r");

		int apuntador = 0;

		int id;
		int telefono;

		char nombre[] = new char[25];
		char apellido[] = new char[25]; 
		char dni[] = new char[25];
		
		char aux;

		// Recorrer el fitxer llibres
		for (;;) {
			aleatoriFile.seek(apuntador);// Apuntar a l'inici de cada llibre al fitxer
			// Llegeix ID
			id = aleatoriFile.readInt();

			for (int i = 0; i < nombre.length; i++) {
				aux = aleatoriFile.readChar();
				nombre[i] = aux;
			}
			String nombres = new String(nombre);
			
			
			for (int i = 0; i < apellido.length; i++) {
				aux = aleatoriFile.readChar();
				apellido[i] = aux;
			}
			String apellidos = new String(apellido);
			
			
			for (int i = 0; i < dni.length; i++) {
				aux = aleatoriFile.readChar();
				dni[i] = aux;
			}
			String dnis = new String(dni);

			telefono = aleatoriFile.readInt();
			
			// Sortida de les dades de cada llibre
			System.out.println("-----------------------");
			System.out.println("ID: " + id + 
					"\nNombre: " + nombres + 
					"\nApellido: " + apellidos + 
					"\nDNI: " + dnis +
					"\nTelefono: " + telefono);
			System.out.println("-----------------------");
			// S'ha de posicionar l'apuntador al següent llibre
			apuntador += 158;
			// Si coincideix on s'està apuntat amb el final del fitxer, sortim
			if (aleatoriFile.getFilePointer() == aleatoriFile.length())
				break;
		}
		aleatoriFile.close();// Tancar el fitxer
	}

	public static void ConsultarFicheroAleatorio() throws IOException {
		File fitxer = new File("C:/Users/juan/eclipse-workspace/m6-uf1-ex3/ficha.txt");
		// Crea un flux (stream) d'arxiu d'accés aleatori només lectura
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "r");
	
		
		int apuntador = 0;
		int seleccio;

		int id;
		int telefono;

		char nombre[] = new char[25];
		char apellido[] = new char[25]; 
		char dni[] = new char[25];
		
		char aux;
		
		
		// Demana a l'usuari que seleccioni el llibre pel seu identificador
		System.out.print("Introduce el ID a consultar: ");
		Scanner stdin = new Scanner(System.in);

		seleccio = stdin.nextInt();
		
		apuntador = (seleccio - 1) * 158;

		if (apuntador >= aleatoriFile.length()) {
			System.out.println("ERROR: ID incorrecto");
			System.out.println();
		} else {
			
			aleatoriFile.seek(apuntador);
			
			id = aleatoriFile.readInt();

			for (int i = 0; i < nombre.length; i++) {
				aux = aleatoriFile.readChar();
				nombre[i] = aux;
			}
			String nombres = new String(nombre);
			
			
			for (int i = 0; i < apellido.length; i++) {
				aux = aleatoriFile.readChar();
				apellido[i] = aux;
			}
			String apellidos = new String(apellido);
			
			
			for (int i = 0; i < dni.length; i++) {
				aux = aleatoriFile.readChar();
				dni[i] = aux;
			}
			String dnis = new String(dni);

			telefono = aleatoriFile.readInt();
			// Sortida de les dades de cada llibre
			System.out.println("ID: " + id + 
					"\nNombre: " + nombres + 
					"\nApellido: " + apellidos + 
					"\nDNI: " + dnis +
					"\nTelefono: " + telefono);
		}
		aleatoriFile.close();// Tancar el fitxer
	}
	
	
	public static void menu() {
		System.out.println("Elija una opcion para ir");
		System.out.println("1. crear una ficha");
		System.out.println("2. ver datos guardados");
		System.out.println("3. buscar por id");
		System.out.println("4. Salir");
	}

	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		boolean menu = true;
		int opcion;
		
		
		while(menu) {
			menu();
			 opcion = teclado.nextInt();
			 
			 if (opcion == 1) {
				EscribirFicheroAleatorio();
			}else if(opcion == 2) {
				LeerFichero();
			}else if (opcion == 3) {
				ConsultarFicheroAleatorio();
			}else if(opcion == 4) {
				menu = false;
			}
			else {
				System.out.println("Opcion incorrecta, elija de nuevo");
			}
			
			
		}
	
	}

}
