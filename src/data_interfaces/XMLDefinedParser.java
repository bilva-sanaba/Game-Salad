package data_interfaces;

import java.util.Collection;
import java.util.ArrayList;
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

import alerts.VoogaError;
import components.IComponent;
import controller.VoogaAlert;

import java.io.*;

public class XMLDefinedParser extends GameSavingDataTool implements Parser {
	
	private final static String ALERTMESSAGE = "This preset is corrupted!";

	private List<Entity> loadFile(String fileName) {
		XStream xs = new XStream(new DomDriver());
		try {
			return (ArrayList) xs.fromXML(getFileToString(fileName));
		} catch (Exception e) {
			VoogaAlert a = new VoogaAlert(ALERTMESSAGE);
			a.showAlert();
			return null;
		}
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
