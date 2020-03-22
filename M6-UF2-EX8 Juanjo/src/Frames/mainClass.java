package Frames;

import java.awt.EventQueue;
import java.awt.Point;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import coches.Coches;

public class mainClass {

	static Scanner teclado = new Scanner(System.in);
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/coches.odb");
	static EntityManager em = emf.createEntityManager();

	public static void EnseñarCoches() {

		TypedQuery<Coches> query = em.createQuery("SELECT p FROM Coches p", Coches.class);
		List<Coches> results = query.getResultList();
		for (Coches p : results) {
			System.out.println(p);
		}

	}
	
	public static void EnseñarCochesId(int id) {

		Coches vehiculo = em.find(Coches.class, id);
			System.out.println(vehiculo);
		

	}


	public static void IntroducirCoches() {

		boolean necesitaReparacion = true;
		boolean correcto = false;
		System.out.println("Introduzca el modelo");
		String modelo = teclado.next();
		System.out.println("Introduzca la matricula");
		String matricula = teclado.next();
		System.out.println("Introduzca los kilometros");
		int kilometro = teclado.nextInt();

		System.out.println("¿Necesita reparación? (si o no)");
		String necesitaReparacionString = teclado.next();
		while (!correcto) {

			if (necesitaReparacionString.equalsIgnoreCase("si") || necesitaReparacionString.equalsIgnoreCase("no")) {

				if (necesitaReparacionString.equalsIgnoreCase("Si")) {
					necesitaReparacion = true;
				} else {
					necesitaReparacion = false;
				}
				correcto = true;

			} else {
				System.out.println("Comando no reconocido, intoduzca si o no por favor");
				System.out.println("¿Necesita reparación? (si o no)");
				necesitaReparacionString = teclado.next();
			}

		}

		em.getTransaction().begin();
		Coches c = new Coches(modelo, matricula, kilometro, necesitaReparacion);
		em.persist(c);
		em.getTransaction().commit();

	}

	public static void menu() {
		System.out.println("1. Enseñar coches");
		System.out.println("2. Añadir coches");
		System.out.println("3. Buscar coches");
		System.out.println("4. Editar coches");
		System.out.println("5. Eliminar coches");
		System.out.println("6. Salir");
	}

	public static void BorrarVehiculo(int id) {

		Coches vehiculo = em.find(Coches.class, id);

		em.getTransaction().begin();
		em.remove(vehiculo);
		em.getTransaction().commit();

	}

	// este metodo modifcica el vehiculo mediante la id
	public static void EditarVehiculo(int id) {
		String valor;
		boolean modificar = true;
		Coches vehiculo = em.find(Coches.class, id);

		em.getTransaction().begin();
		System.out.println("Seleccione el campo que desea modificar (para reparacion, escirba reparacion y si o no)");
		String campo = teclado.next();

		while (modificar) {
			if (campo.equalsIgnoreCase("modelo")) {
				valor = teclado.next();
				vehiculo.setModelo(valor);
			}

			else if (campo.equalsIgnoreCase("matricula")) {
				valor = teclado.next();
				vehiculo.setMatricula(valor);
			}

			else if (campo.equalsIgnoreCase("reparacion")) {
				boolean necesitaReparacion;
				valor = teclado.next();

				if (valor.equalsIgnoreCase("Si")) {
					necesitaReparacion = true;
				} else {
					necesitaReparacion = false;
				}

				vehiculo.setNecesitaReparacion(necesitaReparacion);
			}

			else if (campo.equalsIgnoreCase("matricula")) {
				int valorKm = teclado.nextInt();
				vehiculo.setKilometros(valorKm);
			} else {
				System.out.println("Campo no reconocido");
			}
			System.out.println("¿Desea seguir modificando?");
			valor = teclado.next();

			if (valor.equalsIgnoreCase("Si")) {
				modificar = true;
			} else {
				modificar = false;
			}
		}

		em.getTransaction().commit();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		int id;
		boolean finalizar = false;

		System.out.println("Bienvenido al programa de almacenamiento de coches");
		System.out.println("Introduzca que desea hacer");

		while (!finalizar) {
			menu();
			opcion = teclado.nextInt();

			switch (opcion) {

			case 1:
				EnseñarCoches();
				break;
			case 2:
				IntroducirCoches();
				break;
				
			case 3:
				System.out.println("Seleccione la id");
				id = teclado.nextInt();
				EnseñarCochesId(id);
				break;

			case 4:
				System.out.println("Seleccione la id");
				id = teclado.nextInt();
				EditarVehiculo(id);
				break;

			case 5:
				System.out.println("Seleccione la id");
				id = teclado.nextInt();
				BorrarVehiculo(id);
				break;

			case 6:
				finalizar = true;
				break;

			}
		}

		em.close();
		emf.close();

	}

}
