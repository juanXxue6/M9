import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ex5_alumne {

	static Scanner teclado = new Scanner(System.in);
	static String numId = null;
	//Mètode per afegir un node a un element ja triat
	public static void afegirNode(Element elementPosicionat, Document doc) {
		//Definim els valors del node
		Element nodeNou;
		System.out.println("Nom del node:");
		String nom = teclado.next();
		System.out.println("Valor del node:");
		String valor = teclado.next();
		//Afegim el node nou al document
		nodeNou = doc.createElement(nom);
		nodeNou.appendChild(doc.createTextNode(valor));
		elementPosicionat.appendChild(nodeNou);

	}

	//Metode per modificar un node
	public static void modificarNode(Element posicio) {
		//Demanem el node que hem de modificar
		System.out.println("Quin node vols modificar:");
		String node = teclado.next();

		//Demanem el valor del node
		System.out.println("Valor del node:");
		String valor = teclado.next();

		NodeList nodes = posicio.getChildNodes();
		
		//Busquem a la llista de nodes fills si algún coincideix
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeName().equals(node)) {
				nodes.item(i).setTextContent(valor);
			}
		}
	}
	//Mètode per borrar un node
	public static void borrarNode(Element posicio) {
		//Triem el node a borrar
		System.out.println("Quin node vols borrar:");
		String node = teclado.next();

		NodeList nodes = posicio.getChildNodes();

		//Busquem a la llista de nodes fills si algún coincideix
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeName().equals(node)) {
				posicio.removeChild(nodes.item(i));
			}
		}
	}

	//Mètode per borrar un alumne
	public static void borrarAlumne(Element posicio, Node principal) {
		//El busquem si hi es
		principal.removeChild(posicio);
	}

	//Mètode per crear un alumne
	public static void crearAlumne(Node node, Document doc, String id) {
		Element nodeNou;
		//Calcul de la id
		//Primer passem la ID més gran per paràmetre, li fem un cast a int i li sumem 1.
		//Despres, a l'hora de ficar-la, la tornem a pasar a string
		int calculId = Integer.parseInt(numId);

		calculId++;

		numId = String.valueOf(calculId);
		//Demanem les dades del nou alumne
		System.out.println("Nom de l'alumne:");
		String nom = teclado.nextLine();
		System.out.println("Cognom de l'alumne:");
		String cognom = teclado.nextLine();
		System.out.println("Cognom 2 de l'alumne:");
		String cognom2 = teclado.nextLine();
		System.out.println("Nota:");
		String nota = teclado.nextLine();
		
		//Afegim al nou node una id, que hem calculat abans
		nodeNou = doc.createElement("alumne");
		nodeNou.setAttribute("id", numId);
		nodeNou.setIdAttribute("id", true);

		//Creem els nous nodes
		Element nodeNom = doc.createElement("nom");
		Element nodeCognom1 = doc.createElement("cognom1");
		Element nodeCognom2 = doc.createElement("cognom2");
		Element nodeNota = doc.createElement("notaFinal");

		//Li afegim valor a cada node
		nodeNom.appendChild(doc.createTextNode(nom));
		nodeCognom1.appendChild(doc.createTextNode(cognom));
		nodeCognom2.appendChild(doc.createTextNode(cognom2));
		nodeNota.appendChild(doc.createTextNode(nota));

		//Afegim els nodes fills al node pare
		nodeNou.appendChild(nodeNom);
		nodeNou.appendChild(nodeCognom1);
		nodeNou.appendChild(nodeCognom2);
		nodeNou.appendChild(nodeNota);

		//Finalment, afegim el node nou al document
		node.appendChild(nodeNou);
	}
	
	//Mètode per guardar els canvis
	public static void guardarCanvis(Document doc) throws TransformerFactoryConfigurationError, TransformerException{
		//S'ha de guardar després de tots els canvis
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		StreamResult result = new StreamResult(new File("alumnesModificat.xml"));
		DOMSource source = new DOMSource(doc);
		trans.transform(source, result);
	}

	public static void InformacioNodes(Node node, String espais) {
		NodeList child;
		NamedNodeMap atributs;

		// Agafem els nodes fills
		child = node.getChildNodes();

		// Per cada node fill, una volta al loop
		for (int i = 0; i < child.getLength(); i++) {

			if (!child.item(i).getNodeName().equals("#text")) {

				// Ensenyem el nom del node
				System.out.println(espais + "Node: "
						+ child.item(i).getNodeName());

				if (child.item(i).hasAttributes()) {

					atributs = child.item(i).getAttributes();
					// Mostrem els atributs (si en té)
					for (int j = 0; j < atributs.getLength(); j++) {
						System.out.println(espais + "  " + "Attribute name: "
								+ atributs.item(j).getNodeName());
						System.out.println(espais + "  " + "Attribute value: "
								+ atributs.item(j).getNodeValue());
					}
				} else {
					// Mostrem el valor del node (si en té)
					System.out.println(espais + "  " + "Value del node: "
							+ child.item(i).getTextContent());
					System.out.println(" ");
				}
				// Mirem si té nodes fills i cridem a la mateixa funció de form,
				// recursiva
				if (child.item(i).hasChildNodes()) {
					InformacioNodes(child.item(i), espais + "  ");
				}
			}
		}
	}

	public static void main(String[] args) throws SAXException, IOException,
			ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException {
		Document doc;
		boolean sortir = true;
		int opcioTriada;
		

		Element elementPosicionat;
		NodeList child;
		NamedNodeMap atributs;

		// per a carregar en memòria un arxiu xml
		File file = new File("alumnes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(file);

		// per obtenir el node arrel
		Node nodeArrel = doc.getDocumentElement();

		child = nodeArrel.getChildNodes();

		for (int i = 0; i < child.getLength(); i++) {

			if (child.item(i).getNodeName().equals("alumne")
					&& child.item(i).hasAttributes()) {
				atributs = child.item(i).getAttributes();
				for (int j = 0; j < atributs.getLength(); j++) {
					if (atributs.item(j).getNodeName().equals("id")) {
						numId = atributs.item(j).getNodeValue();
						((Element) child.item(i)).setIdAttribute("id", true);
					}
				}
			}
		}

		//Començem amb el menú
		while (sortir) {
			System.out.println("Menú XML:");
			System.out.println("<--------------------->");
			System.out.println("1. Mostrar arxiu");
			System.out.println("2. Modificar alumne existent");
			System.out.println("3. Crear alumne");
			System.out.println("4. Borrar alumne");
			System.out.println("5. Guardar");
			System.out.println("6. Sortir sense guardar");
			System.out.println("7. Guardar i sortir");
			opcioTriada = teclado.nextInt();

			switch (opcioTriada) {
			case 1:
				System.out
						.println("Node Principal: " + nodeArrel.getNodeName());
				InformacioNodes(nodeArrel, "  ");
				System.out.println("Final del node principal");
				break;

			case 2:
				// per cercar elements per un atribut anomenat id
				System.out.println("A quin alumne vols accedir? (id)");
				String id = teclado.next();
				teclado.nextLine();

				elementPosicionat = doc.getElementById(id);
				//Menu modificiacio
				System.out.println("Menú Alumne:");
				System.out.println("<--------------------->");
				System.out.println("1. Modificar nodes");
				System.out.println("2. Afegir nodes");
				System.out.println("3. Eliminar nodes");
				int resposta = teclado.nextInt();
				teclado.nextLine();

				switch (resposta) {
				case 1:
					//Modificació
					modificarNode(elementPosicionat);
					break;

				case 2:
					//Afegir
					afegirNode(elementPosicionat, doc);
					break;

				case 3:
					//Borrar
					borrarNode(elementPosicionat);
					break;

				default:

					break;
				}

				break;

			case 3:
				//Creació
				crearAlumne(nodeArrel, doc, numId);
				break;
			case 4:
				//Borrar alumne
				System.out.println("Quin alumne vols borrar? (id)");
				String id2 = teclado.next();

				elementPosicionat = doc.getElementById(id2);
				borrarAlumne(elementPosicionat, nodeArrel);

				break;

			case 5:
				//Guardar
				guardarCanvis(doc);
				break;

			case 6:
				//Sortir sense guardar
				sortir = false;
				break;

			case 7:
				//Guardar i sortir
				guardarCanvis(doc);
				sortir = false;
				break;

			default:
				System.out.println("Tria una opció vàlida.");
				break;
			}
		}

		teclado.close();
	}
}
