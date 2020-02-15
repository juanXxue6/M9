import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Ex1 {
	static Scanner teclado = new Scanner(System.in);;

	public static void menu() {
		System.out.println("Elije una opción");
		System.out.println("1. Insertar un registo");
		System.out.println("2. Modificar un registro");
		System.out.println("3. Eliminar un registro");
		System.out.println("4. Enseñar todos los registros");
		System.out.println("5. salir");
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		int opcion;
		boolean programa = true;
		Statement stmt;
		ResultSet rs;
		boolean val;
		int id;

		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/actividad1", "usuari",
					"usuari");

			// con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		while (programa) {
			menu();
			opcion = teclado.nextInt();

			switch (opcion) {
			case 1:

				System.out.println("Introduce el nombre");
				String nombre = teclado.next();
				System.out.println("Introduce el DNI");
				String DNI = teclado.next();
				System.out.println("Introduce la fecha (tipo string)");
				String fecha = teclado.next();
				System.out.println("Introduce la poblacion postal");
				String pPostal = teclado.next();
				System.out.println("Introduce el codigo postal (numero)");
				int cp = teclado.nextInt();
				System.out.println("Introduce la poblacion");
				String poblacio = teclado.next();

				stmt = (Statement) con.createStatement();
				stmt.executeUpdate("INSERT INTO alumne(nom, dni, data_naixement, direccio_postal, codi_postal, poblacion) "
						+ "VALUES ('"
						+ nombre
						+ "', '"
						+ DNI
						+ "', '"
						+ fecha
						+ "', '"
						+ pPostal
						+ "', '"
						+ cp
						+ "', '"
						+ poblacio
						+ "')");

				System.out.println("--------------------------------");
				System.out.println("alumno Introducido correctamente");
				System.out.println("--------------------------------");
				System.out.println();

				break;
			case 2:

				System.out
						.println("Introduce la id del alumno que desea modificar");
				id = teclado.nextInt();
				boolean modificar = true;


					stmt = (Statement) con.createStatement();
					rs = stmt.executeQuery("SELECT * FROM alumne WHERE id = '"
							+ id + "'");

					 val = rs.next();

					if (val == false) {
						System.out.println("Alumno no encontrado");
						System.out.println();
						System.out.println();
					} else {
						while (modificar) {

							rs = stmt.executeQuery("SELECT * FROM alumne WHERE id = '"
									+ id + "'");
							
						System.out
								.println("ID-NOM--DNI--DATA_NAIXEMENT--DIRECCIO_POSTAL--CODI_POSTAL---POBLACION");
						while (rs.next())
							System.out.println(rs.getInt(1) + "  "
									+ rs.getString(2) + "  " + rs.getString(3)
									+ " " + rs.getString(4) + " "
									+ rs.getString(5) + " " + rs.getString(6)
									+ " " + rs.getString(7));
						System.out
								.println("---------------------------------------");
						System.out.println();
						System.out.println("Elija un campo a modificar");
						String campo = teclado.next().toLowerCase();
						System.out.println("Introduce el valor nuevo");
						if (campo.equals("campo")) {
							int cpUpdate = teclado.nextInt();
							stmt.executeUpdate("UPDATE alumne SET " + campo
									+ "= '" + cpUpdate + "' WHERE id = '" + id
									+ "'");
						} else {
							String valor = teclado.next();
							stmt.executeUpdate("UPDATE alumne SET " + campo
									+ "= '" + valor + "' WHERE id = '" + id
									+ "'");
						}

						System.out
								.println("¿Deseas modificar algo mas de este registro?");
						String seguir = teclado.next();
						if (seguir.equalsIgnoreCase("no")) {
							modificar = false;
						}
					}
				}

				break;
			case 3:
				System.out
						.println("Introduce la id del alumno que desea eliminar");
				id = teclado.nextInt();

				stmt = (Statement) con.createStatement();

				rs = stmt.executeQuery("SELECT * FROM alumne WHERE id = '" + id
						+ "'");
				 val = rs.next();

				if (val == false) {
					System.out.println("Alumno no encontrado");
					System.out.println();
					System.out.println();
				} else {

					stmt = (Statement) con.createStatement();
					stmt.executeUpdate("DELETE FROM alumne WHERE id = '" + id
							+ "'");

					System.out.println("------------------------------");
					System.out.println("Alumno eliminado correctamente");
					System.out.println("------------------------------");
					System.out.println();
				}

				break;
			case 4:

				stmt = (Statement) con.createStatement();
				rs = stmt.executeQuery("select * from alumne");
				System.out
						.println("ID-NOMBRE---DNI----FECHA------P.POST--CP---POBLACION");
				while (rs.next())

					System.out.println(rs.getInt(1) + "  " + rs.getString(2)
							+ "  " + rs.getString(3) + " " + rs.getString(4)
							+ " " + rs.getString(5) + " " + rs.getString(6)
							+ " " + rs.getString(7));
				System.out.println("---------------------------------------");
				System.out.println();

				break;

			case 5:
				System.out.println("Conexion finalizada");
				con.close();
				programa = false;
			default:
				break;
			}
		}

	}

}
