package data_interfaces;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import data_interfaces.LocalClassLoader;

public class XMLWriter implements FileSaver{
	
	private static final String SUFFIX = ".txt";
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
	
	public void writeFile(String fileName, List gameData) {
		LocalClassLoader loader = new LocalClassLoader();
        XStream serializer = new XStream(new DomDriver());
        String ret;
        
        serializer.setClassLoader(loader);
        
        ret = serializer.toXML(gameData);
        createFile(fileName, ret);
	}
	
	public static void main(String[] args) {
		XMLWriter x = new XMLWriter();
		List l = new ArrayList();
		l.add("jonathan");
		l.add(1);
		x.writeFile("rub", l);
	}
	

}
