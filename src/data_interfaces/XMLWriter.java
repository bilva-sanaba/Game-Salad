package data_interfaces;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import components.LocationComponent;
import components.SpriteComponent;
import data_interfaces.LocalClassLoader;
import entity.Entity;

public class XMLWriter extends GameSavingDataTool implements FileSaver {

	@Override
	public void createFile(String fileName, Object data) {
		try {
			File f = new File(getPrefix() + fileName + getSuffix());
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
	public void writeFile(String fileName, Collection gameData) {
		ClassLoader loader = new LocalClassLoader();
        XStream serializer = new XStream(new DomDriver());
        String ret;
        
        serializer.setClassLoader(loader);
        
        ret = serializer.toXML(gameData);
        createFile(fileName, ret);
	}
	
	
	public static void main(String[] args) {
		XMLWriter x = new XMLWriter();
		SpriteComponent s = new SpriteComponent("jin jon");
		SpriteComponent s1 = new SpriteComponent("bobby joe");
		LocationComponent t = new LocationComponent(5,6);
		List l = new ArrayList();
		
		Entity e = new Entity(9);
		e.addComponent(s);
		e.addComponent(t);
		l.add(e);

		Entity e1 = new Entity(10);
		e1.addComponent(s1);
		l.add(e1);
		
		x.writeFile("try4", l);
	}
	
}
