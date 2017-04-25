package gameView.userManagement;

import gameView.tools.FrontEndException;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlElement;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import data_interfaces.XMLParser;

public class UserDatabase {
	
	private final String DATA_FILE = "data" + File.separator + "UserData.xml"; 
	
	private Element myData;
	private HashMap<String, UserData> myUsers;
	
	public UserDatabase() {
		createUsers(new XMLParser().getData(DATA_FILE));
		System.out.println(myUsers.values());
	}
	
	
	public UserData addNewUser(UserData user) {
		String username = user.getName();
		if (myUsers.containsKey(username)) {
			return null;
		} else {
			myUsers.put(username, user);
			return user;
		}
	}
	
	public UserData getUser(UserData user) {
		if (!myUsers.containsKey(user.getName()) || (!myUsers.get(user.getName()).getPassword().equals(user.getPassword()))){
			return null;
		}
		return myUsers.get(user.getName());
		
	}
	
	public void saveUsers() {
		Collection<UserData> collection = myUsers.values();
		new UserDataWriter().saveUsers(DATA_FILE, myUsers);
	}
	
	private void createUsers(Element data) {
		myUsers = new HashMap<String, UserData>();
		NodeList values = data.getElementsByTagName("user");
		if (values != null) {
			for (int i = 0; i < values.getLength(); i++) {
				Element ele = (Element) values.item(i);
				UserData newUser = getUserData(ele);
				myUsers.put(newUser.getName(), newUser);
			}
		}
	}
	
	private UserData getUserData(Element root) {
		UserData newUser;
		ArrayList<String> information = new ArrayList<String>();
		UserData.DATA_FIELDS.stream()
			.forEach(c -> {
				NodeList values = root.getElementsByTagName((String) c);
				if (values.getLength() != 1) {
					throw new FrontEndException(String.format("ERROR IN USER XML FORMATTING WITH: %s", (String) c));
				} else {
					information.add(values.item(0).getTextContent());
				}
			});
		newUser = new UserData(information.get(0), information.get(1), information.get(2));
		return newUser;
	}

}
