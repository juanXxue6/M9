package ejemplo;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Ejercicio {

	public static void main(String[] args) {
		
		
		URL url;
		//asignamos las variables que se pasaron por parametro
		//url
	String direccion =  args[0];
	//puerto
	int puerto = Integer.parseInt(args[1]);
	
		try {
			
//cargamos la url con el protocol, la direccion y el puerto
			url = new URL("http", direccion, puerto, "/");
			Visualitzar (url);
			

		} catch (MalformedURLException e) { System.out.println(e); }
		
	}
	
	//leemos la pagina web por pantalla
	
	private static void Visualitzar(URL url) {
		
		BufferedReader in;
		
		try {
			
			//leemos la url
			InputStream inputStream = url.openStream();
			in = new BufferedReader(new InputStreamReader(inputStream));
			
			String inputLine;
			//mientras haya algo en la linea, sige enseñando por pantalla
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();
			
		} catch (IOException e) {e.printStackTrace(); }

		
	}

}
