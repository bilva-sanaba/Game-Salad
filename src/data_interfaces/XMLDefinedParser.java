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

import java.io.*;

public class XMLDefinedParser implements Parser {
	
	private static String LINESEPARATOR = System.getProperty("line.separator");
	private static String PREFIX = "games" + File.separator;
	private static String SUFFIX = ".xml";

	private List<Entity> loadFile(String fileName) {
		XStream xs = new XStream(new DomDriver());
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			System.out.println(fileName);
			File f = new File(PREFIX + fileName + SUFFIX);
			System.out.println(f.exists());
			FileReader fr = new FileReader(f);
			System.out.println("right here");
			BufferedReader br = new BufferedReader(fr);
			System.out.println("THIS OCCURSSZZ");
			while ((line = br.readLine()) != null) {
				System.out.println("This happens THISSSSS");
				stringBuilder.append(line);
				stringBuilder.append(LINESEPARATOR);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO add an error here
			System.out.println("Hi i happen");
		} catch (IOException e) {
			System.out.println("hi i happen");
		}
		System.out.println("This happens" + stringBuilder.toString());
		Object o = xs.fromXML(stringBuilder.toString());
		System.out.print("THIS" + o.toString() + "TO THIS");
		System.out.println(o.getClass());
		List<Entity> al = (ArrayList) o;
		
		for (Object e: al) {
			System.out.println(e.getClass());
		}
		
		return al;
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
