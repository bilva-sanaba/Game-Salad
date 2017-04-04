package data_interfaces;

import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import groovy.LocalClassLoader;

public class XMLWriter implements FileSaver{

	@Override
	public void createFile(String fileName, String data) {
		
		
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
