package gameView.userManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.sun.javafx.webkit.KeyCodeMap.Entry;
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
	
	public void saveUsers(String fileName, Map<String, UserData> data ) {
		ClassLoader loader = new LocalClassLoader();
		XStream serializer = new XStream(new DomDriver());
		setAlias(serializer);
		String ret;

		serializer.setClassLoader(loader);

		ret = serializer.toXML(data);
		createFile(fileName, ret);
	}
	
	private void setAlias(XStream ser) {
		ser.alias("userdata", UserData.class);
		ser.alias("users", Map.class);
	}

}
