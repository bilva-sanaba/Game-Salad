package gameView.userManagement;

import gameView.tools.FrontEndException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import data_interfaces.LocalClassLoader;
import data_interfaces.XMLWriter;

public class UserDataHandler extends XMLWriter {
	
	private XStream mySerializer;
	
	public UserDataHandler() {
		ClassLoader loader = new LocalClassLoader();
		mySerializer = new XStream(new DomDriver());
		setAlias(mySerializer);
		mySerializer.setClassLoader(loader);
	}

	/**
	 * Saves all users using XStream Serializing
	 * @param fileName - name of file to write to
	 * @param data - map of all the users to save
	 */
	public void saveUsers(String fileName, Map<String, UserData> data ) {
		String ret;
		ret = mySerializer.toXML(data);
		createFile(fileName, ret);
	}
	
	/**
	 * Loads user data from a specific file
	 * @param fileName - file to load user data from
	 * @return a map of usernames to userdata
	 */
	public HashMap<String, UserData> loadUsers(String fileName) {
		HashMap<String, UserData> returnMap;
		try {
			InputStream in = new FileInputStream(fileName);
			returnMap = (HashMap<String, UserData>) mySerializer.fromXML(in);
		} catch (Exception e) {
			return new HashMap<String, UserData>();
		}
		return returnMap;
		

	}
	
	private void createFile(String fileName, Object data) {
		try {
			File f = new File(fileName);
			BufferedWriter b = new BufferedWriter(new FileWriter(f));
			b.write(data.toString());
			b.close();
		} catch (Exception e){
			throw new FrontEndException("ERROR WITH MAKING USER DATA FILE");
		}
	}
	
	private void setAlias(XStream ser) {
		ser.alias("userdata", UserData.class);
		ser.alias("users", Map.class);
	}

}
