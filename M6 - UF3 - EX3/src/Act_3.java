import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Act_3 {
	
	public static void main (String[] args) throws XMLDBException {
		//Driver per a eXist
		String driver = "org.exist.xmldb.DatabaseImpl";
		//Col·lecció
		Collection col = null;
		//URI col·lecció
		String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/Proves";
		//Usuari
		String usu = "admin";
		//Contrasenya
		String usuPass = "manzana123";
		
		
		try {
			//Carrega el driver
			Class cl = Class.forName(driver);
			//Instància de la BD
			Database database = (Database) cl.newInstance();
			//Registre del driver
			DatabaseManager.registerDatabase(database);
		} catch (Exception e) {
			System.out.println("Error en inicialitzar la base de dades eXist");
			e.printStackTrace();
		}
		
		System.out.println("Escriu un departament:");
		String s = null;
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			s = in.readLine();
		} catch (IOException e) {
			System.out.println("Error en llegir");
			e.printStackTrace();
		}
		
		int departament = Integer.parseInt(s);
		
		col = DatabaseManager.getCollection(URI, usu, usuPass);
		if (col==null) {
			System.out.println("*** LA COL·LECCIÓ NO EXISTEIX ***");
		}
		XPathQueryService servei =
		(XPathQueryService) col.getService("XPathQueryService", "1.0");
		ResourceSet result =
		servei.query("for $em in /EMPLEADOS/EMP_ROW[DEPT_NO=" + departament + "] return $em");
		
		//Recórrer les dades del recurs
		ResourceIterator i;
		i = result.getIterator();
		if (!i.hasMoreResources())
			System.out.println("LA CONSULTA NO TORNA RES");
		while (i.hasMoreResources()) {
			Resource r = i.nextResource();
			System.out.println((String)r.getContent());
		
		}
		//S'esborra
		col.close();
	}

}