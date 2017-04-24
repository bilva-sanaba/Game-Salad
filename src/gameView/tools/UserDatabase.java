package gameView.tools;

import java.io.File;

import org.w3c.dom.Element;

import data_interfaces.XMLParser;

public class UserDatabase {
	
	private final String DATA_FILE = "data" + File.separator + "UserData.xml"; 
	
	private Element myData;
	
	public UserDatabase() {
		//myData = new XMLParser().getData(DATA_FILE);
	}
	
	
	public void addUser(UserData user) {
		
	}

}
