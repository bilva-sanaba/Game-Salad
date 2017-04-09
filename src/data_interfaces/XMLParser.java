package data_interfaces;


import java.util.Collection;
import java.util.List;
import voogasalad.util.reflection.*;
import entity.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import components.IComponent;

import java.io.*;


import entity.IEntityManager;



public class XMLParser {

	

	private Element loadFile(String fileName) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(fileName);
			if (doc.equals(null)) {
				System.out.println("alert");
			}
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
	 * @return the element of the parsed DOM objects
	 */
	public Element getData(String fileName) {
		return (Element)loadFile(fileName);
	}
	
}
