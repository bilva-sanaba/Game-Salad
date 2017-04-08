package data_interfaces;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import components.LocationComponent;
import components.SpriteComponent;
import data_interfaces.LocalClassLoader;
import entity.Entity;

public class XMLWriter implements FileSaver{
	
	private static final String SUFFIX = ".xml";
	private static final String PREFIX = "games/";

	@Override
	public void createFile(String fileName, Object data) {
		try {
			File f = new File(PREFIX + fileName + SUFFIX);
			BufferedWriter b = new BufferedWriter(new FileWriter(f));
			b.write(data.toString());
			b.close();
		}
		catch (IOException e) {
			//TODO call the alert that they built
		}
		
		
	}
	
	/**
	 * writes an XML file which saves game data
	 * @param fileName the desired name for the file
	 * @param gameData the data which should be saved
	 */
	public void writeFile(String fileName, List gameData) {
		ClassLoader loader = new LocalClassLoader();
        XStream serializer = new XStream(new DomDriver());
        String ret;
        
        serializer.setClassLoader(loader);
        
        ret = serializer.toXML(gameData);
        createFile(fileName, ret);
	}
	
	/*
	public static void main(String[] args) {
		XMLWriter x = new XMLWriter();
		SpriteComponent s = new SpriteComponent("jin jon");
		LocationComponent t = new LocationComponent(5,6);
		List l = new ArrayList();
		
		Entity e = new Entity(9);
		e.addComponent(s);
		Entity e1 = new Entity(9);
		e1.addComponent(s);
		l.add(e);
		e1.addComponent(t);
		l.add(e1);
		
		x.writeFile("try4", l);
	}*/
	
}
