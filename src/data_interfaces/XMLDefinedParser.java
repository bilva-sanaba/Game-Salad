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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import components.IComponent;

import java.io.*;

public class XMLDefinedParser implements Parser {

	private List<Entity> loadFile(String fileName) {
		XStream xs = new XStream(new DomDriver());
		return (List<Entity>) xs.fromXML(new File(fileName));
	}

	/**
	 * Retrieves the data needed from the file to be sent to the game engine
	 * 
	 * @param fileName
	 *            the name of the file
	 * @return the element of the parsed DOM objects
	 */
	public List getData(String fileName) {
		return loadFile(fileName);
	}

}
