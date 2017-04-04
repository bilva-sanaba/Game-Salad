package data_interfaces;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import data_interfaces.LocalClassLoader;

public class XMLWriter implements FileSaver{

	@Override
	public void createFile(String fileName, String data) {
		try {
			File f = new File(fileName);
			BufferedWriter b = new BufferedWriter(new FileWriter(f));
			b.write(data);
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
	
	

}
