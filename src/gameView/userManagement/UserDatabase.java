package gameView.userManagement;

import gameView.tools.FrontEndException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import data_interfaces.XMLParser;

public class UserDatabase {
	
	private final String DATA_FILE = "data" + File.separator + "UserData.xml"; 
	
	private Element myData;
	private HashMap<String, UserData> myUsers;
	
	public UserDatabase() {
		createUsers(new XMLParser().getData(DATA_FILE));
	}
	
	
	public UserData selectUser(UserData user, boolean newUser) {
		String username = user.getName();
		if (!newUser) {
			if ((!myUsers.containsKey(username) || user.getPassword().equals(myUsers.get(username).getPassword()))) {
				throw new FrontEndException("Wrong Username and Password Combination");
			} else {
				return myUsers.get(username);
			}
		} else {
			if (myUsers.containsKey(username)) {
				throw new FrontEndException("User already registered");
			} else {
				myUsers.put(username, user);
				return user;
			}
		}
	}
	
	public void saveUsers() {
		new UserDataWriter().saveUsers(DATA_FILE, myUsers.values());
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
