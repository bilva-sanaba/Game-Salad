package gameView.userManagement;


import java.io.File;
import java.util.HashMap;

public class UserDatabase {
	
	private final String DATA_FILE = "data" + File.separator + "UserData.xml"; 
	
	private HashMap<String, UserData> myUsers;
	private UserDataHandler myHandler;
	
	public UserDatabase() {
		myHandler = new UserDataHandler();
		myUsers = myHandler.loadUsers(DATA_FILE);
	}
	
	
	/**
	 * Adds a new user to database
	 * @param user user to add
	 * @return user if successful, null if user already exists or doesn't contain password
	 */
	public UserData addNewUser(UserData user) {
		String username = user.getName();
		if (myUsers.containsKey(username) || user.getPassword() == null) {
			return null;
		} else {
			myUsers.put(username, user);
			return user;
		}
	}
	
	/**
	 * Get a specific user on log-in
	 * @param user - user to get
	 * @return user if succesful, null if user doesn't exist or incorrect username/password
	 */
	public UserData getUser(UserData user) {
		if (!myUsers.containsKey(user.getName()) || (!myUsers.get(user.getName()).getPassword().equals(user.getPassword()))){
			return null;
		}
		return myUsers.get(user.getName());
		
	}
	
	/**
	 * Save all users
	 */
	public void saveUsers() {
		if (myUsers != null) {
			myHandler.saveUsers(DATA_FILE, myUsers);
		}
	}
}
