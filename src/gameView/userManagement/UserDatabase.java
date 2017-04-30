package gameView.userManagement;

import gameView.tools.FrontEndException;
import java.io.File;
import java.util.HashMap;

public class UserDatabase {
	
	private final String DATA_FILE = "data" + File.separator + "UserData.xml"; 
	
	private HashMap<String, UserData> myUsers;
	private UserDataHandler myHandler;
	
	public UserDatabase() {
		myHandler = new UserDataHandler();
		myUsers = myHandler.loadUsers(DATA_FILE);
		System.out.println(myUsers);
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
		if (myUsers != null) {
			myHandler.saveUsers(DATA_FILE, myUsers);
		}
	}
}
