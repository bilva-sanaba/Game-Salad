package gameView.userManagement;

import gameView.tools.FrontEndException;

public class UserManager implements IUserManager {

	private UserData myCurrentUser;
	private UserDatabase myDatabase;
	
	public UserManager() {
		myDatabase = new UserDatabase();
	}
	
	@Override
	public UserData getCurrentUser() {
		return myCurrentUser;
	}

	@Override
	public boolean addUser(UserData data) {
		UserData currUser = myDatabase.addNewUser(data);
		System.out.println("NEW USER ADDED IN USER MANAGER");
		System.out.println(data.getName());
		return nullCheck(currUser, "ALREADY A USER");
	}

	@Override
	public boolean selectUser(UserData data) {
		UserData currentUser = myDatabase.getUser(data);
		System.out.println("USER SELECTED IN USER MANAGER");
		System.out.println(data.getName());
		return nullCheck(currentUser, "INCORRECT USERNAME/PASSWORD COMBINATION");
	}

	@Override
	public boolean facebookUser(UserData data) {
		if (myDatabase.getUser(data) == null) {
			return addUser(data);
		} else {
			return selectUser(data);
		}
		
	}
	
	private boolean nullCheck(UserData data, String message) {
		if (data == null) {
			throw new FrontEndException(message);
		} else {
			myCurrentUser = data;
			return true;
		}
	}

}
