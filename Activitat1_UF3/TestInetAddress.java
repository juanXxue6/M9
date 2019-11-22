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

		System.out.println("\tM�tode getByName(): " + dir);

		try {
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tM�tode getLocalHost(): " + dir2);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		// FEM SERVIR M�TODES DE LA CLASSE
		System.out.println("\tM�tode getHostName(): " + dir.getHostName()); //Retorna el nom del Host
		System.out.println("\tM�tode getHostAddress(): " + dir.getHostAddress()); //Retorna l'adre�a del Host
		System.out.println("\tM�tode toString(): " + dir.toString()); //Retorna una string amb el host i l'adre�a
		System.out.println("\tM�tode getCanonicalHostName(): " + dir.getCanonicalHostName()); //Retorna el nom del domini per l'adre�a
		System.out.println("\tM�tode isAnyLocalAddress(): " + dir.isAnyLocalAddress()); //Retorna un boolean de si l'adre�a es local
		System.out.println("\tM�tode isReachable(): " + dir.isReachable(500)); //Retorna un boolean de si hi pot arribar dintre del limit establert
		System.out.println("\tM�tode hashCode(): " + dir.hashCode()); //Retorna el hash per aquesta adre�a
	}
}