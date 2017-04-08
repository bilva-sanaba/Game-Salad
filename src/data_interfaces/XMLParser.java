package data_interfaces;


import java.util.List;
import voogasalad.util.reflection.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;



import java.io.*;


import entity.IEntityManager;



public class XMLParser implements FileLoader {

	

	public Object loadFile(String fileName) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(fileName);
			return doc.getDocumentElement();
		} catch (ParserConfigurationException e) {
			// TODO Solve this	
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Retrieves the data needed from the file
	 * to be sent to the game engine
	 * @param fileName the name of the file
	 * @return the list of reflected objects
	 */
	public Document getData(String fileName) {
		return (Document)loadFile(fileName);
	}
	/*
	public static void main(String [] args) {
		XMLParser x = new XMLParser();
		System.out.println(x.getData("games/try2.xml"));
	}*/
}
