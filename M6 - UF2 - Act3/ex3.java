import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ex3 {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuaris", "usuari", "usuari");
			Statement st = con.createStatement();
			
			System.out.println("Username:");
			String un = teclado.next();
			System.out.println("Password:");
			String pw = teclado.next();
			
			String sql = "select usuari, contrasenya from usuaris where usuari='" + un + "'and contrasenya='" + pw + "'";
			ResultSet rs = st.executeQuery(sql);
			
			int count = 0;
			
			while (rs.next()) {
				count = count + 1;
			}
			if (count == 1) {
				System.out.println("Validat!!!");
			} else if (count > 1) {
				System.out.println("Usuari duplicat!");
			} else {
				System.out.println("L'usuari no existeix!");
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		teclado.close();
	}

}
