import java.io.IOException;
import java.net.*;

public class TestInetAddress {

	public static void main(String[] args) throws IOException {
		InetAddress dir = null;
		System.out
				.println("=====================================================");
		System.out.println("SORTIDA PER A LOCALHOST");

		try {
			// LOCALHOST
			dir = InetAddress.getByName(args[0]);
			provaTots(dir);

			// URL www.google.com
			System.out
					.println("=====================================================");
			System.out.println("SORTIDA PER A URL");
			dir = InetAddress.getByName("www.google.com");
			provaTots(dir);

			// Array tipus InetAddress amb totes les adreces IP de google.com
			System.out.println("\tAdreces IP per a: " + dir.getHostName());
			InetAddress[] adreces = InetAddress.getAllByName(dir.getHostName());
			for (int i = 0; i < adreces.length; i++)
				System.out.println("\t\t" + adreces[i].toString());
			System.out
					.println("=====================================================");

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

	}

	private static void provaTots(InetAddress dir) throws IOException {

		InetAddress dir2;

		System.out.println("\tMètode getByName(): " + dir);

		try {
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tMètode getLocalHost(): " + dir2);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		// FEM SERVIR MÊTODES DE LA CLASSE
		System.out.println("\tMètode getHostName(): " + dir.getHostName()); //Retorna el nom del Host
		System.out.println("\tMètode getHostAddress(): " + dir.getHostAddress()); //Retorna l'adreça del Host
		System.out.println("\tMètode toString(): " + dir.toString()); //Retorna una string amb el host i l'adreça
		System.out.println("\tMètode getCanonicalHostName(): " + dir.getCanonicalHostName()); //Retorna el nom del domini per l'adreça
		System.out.println("\tMètode isAnyLocalAddress(): " + dir.isAnyLocalAddress()); //Retorna un boolean de si l'adreça es local
		System.out.println("\tMètode isReachable(): " + dir.isReachable(500)); //Retorna un boolean de si hi pot arribar dintre del limit establert
		System.out.println("\tMètode hashCode(): " + dir.hashCode()); //Retorna el hash per aquesta adreça
	}
}