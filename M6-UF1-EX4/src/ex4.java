import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ex4 {

	public static void InformacioNodes(Node node, String espais) {
		NodeList child;
		NamedNodeMap atributs;

		//Agafem els nodes fills
		child = node.getChildNodes();

		//Per cada node fill, una volta al loop
		for (int i = 0; i < child.getLength(); i++) {
			if (!child.item(i).getNodeName().equals("#text")) {

				//Ensenyem el nom del node
				System.out.println(espais + "Node: " + child.item(i).getNodeName());

				if (child.item(i).hasAttributes()) {

					atributs = child.item(i).getAttributes();
					//Mostrem els atributs (si en té)
					for (int j = 0; j < atributs.getLength(); j++) {
						System.out.println(espais + "  " + "Attribute name: "
								+ atributs.item(j).getNodeName());
						System.out.println(espais + "  " + "Attribute value: "
								+ atributs.item(j).getNodeValue());
					}
				} else {
					//Mostrem el valor del node (si en té)
					System.out.println(espais + "  " + "Value del node: " + child.item(i).getTextContent());
					System.out.println(" ");
				}
				//Mirem si té nodes fills i cridem a la mateixa funció de form, recursiva
				if (child.item(i).hasChildNodes()) {
					InformacioNodes(child.item(i), espais + "  ");
				}
			}
		}
	}

	public static void main(String[] args) throws SAXException, IOException,
			ParserConfigurationException {
		Document doc;

		// per a carregar en memòria un arxiu xml
		File file = new File("alumnes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(file);

		// per obtenir el node arrel
		Node nodeArrel = doc.getDocumentElement();


		System.out.println("Node Principal: " + nodeArrel.getNodeName());
		InformacioNodes(nodeArrel, "  ");
		System.out.println("Final del node principal");
	}
}
