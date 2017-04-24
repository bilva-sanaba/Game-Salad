package gameView.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import data_interfaces.LocalClassLoader;
import data_interfaces.XMLWriter;

public class UserDataWriter extends XMLWriter {

	private void createFile(String fileName, Object data) {
		try {
			File f = new File(fileName);
			BufferedWriter b = new BufferedWriter(new FileWriter(f));
			b.write(data.toString());
			b.close();
		} catch (Exception e){
			
		}
	}
	
	public void saveUsers(String fileName, Collection<UserData> data ) {
		ClassLoader loader = new LocalClassLoader();
		XStream serializer = new XStream(new DomDriver());
		String ret;

		serializer.setClassLoader(loader);

		ret = serializer.toXML(data);
		createFile(fileName, ret);
	}

}
