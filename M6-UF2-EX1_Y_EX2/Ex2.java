import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Ex2 {
	static Scanner teclado = new Scanner(System.in);;

	public static void menu() {
		System.out.println("Elije una opción");
		System.out.println("1. Insertar un registo");
		System.out.println("2. Eliminar un registro");
		System.out.println("3. Enseñar todos los registros");
		System.out.println("4. salir");
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		int opcion;
		boolean programa = true;
		Statement stmt;
		String respuesta;
		ResultSet rs;
		boolean val;
		int id;

		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/actividad1", "usuari",
					"usuari");
			con.setAutoCommit(false);
			
			// con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		while (programa) {
			menu();
			opcion = teclado.nextInt();

			switch (opcion) {
			case 1:

				System.out.println("Introduce el codigo postal");
				String cp = teclado.next();

				System.out.println("Introduce la poblacion");
				String poblacio = teclado.next();

				stmt = (Statement) con.createStatement();
				stmt.executeUpdate("INSERT INTO poblacio(codi_postal, poblacio) "
						+ "VALUES ('"
						+ cp
						+ "', '"
						+ poblacio
						+ "')");

				System.out.println("¿Desea guardar esta modificacion?");
				respuesta = teclado.next();
				
				if (respuesta.equalsIgnoreCase("si")) {
					con.commit();
					System.out.println("Poblacion añadida correctamente");
					System.out.println();
				}else{
					con.rollback();
					System.out.println("Desaciendo cambios");
					System.out.println();
				}
			
				System.out.println();

				break;

			case 2:
				System.out
						.println("Introduce el cp de la poblacion que desea eliminar(Se eliminaran todos los usuarios asociados)");
				id = teclado.nextInt();

				stmt = (Statement) con.createStatement();

				rs = stmt.executeQuery("SELECT * FROM poblacio WHERE codi_postal = '" + id
						+ "'");
				 val = rs.next();

				if (val == false) {
					System.out.println("Poblacion no encontrada");
					System.out.println();
					System.out.println();
				} else {

					stmt = (Statement) con.createStatement();
					stmt.executeUpdate("DELETE FROM poblacio WHERE codi_postal = '" + id
							+ "'");


					System.out.println("¿Desea guardar esta modificacion?");
					respuesta = teclado.next();
					
					if (respuesta.equalsIgnoreCase("si")) {
						con.commit();
						System.out.println("Poblacion eliminada correctamente");
						System.out.println();
					}else{
						con.rollback();
						System.out.println("Desaciendo cambios");
						System.out.println();
					}
				
					System.out.println();
				}

				break;
			case 3:

				stmt = (Statement) con.createStatement();
				rs = stmt.executeQuery("select * from poblacio");
				System.out
						.println("CP ------------- POBLACION");
				while (rs.next())

					System.out.println(rs.getInt(1) + "  " + rs.getString(2));
				System.out.println("---------------------------------------");
				System.out.println();

				break;

			case 4:
				System.out.println("Conexion finalizada");
				con.close();
				programa = false;
			default:
				break;
			}
		}

	}

}
