package gameView.userManagement;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import gameView.tools.FrontEndException;

public class UserManager implements IUserManager {

	private UserData myCurrentUser;
	private BooleanProperty myHasUser;
	private UserDatabase myDatabase;
	
	public UserManager() {
		myDatabase = new UserDatabase();
		myHasUser = new SimpleBooleanProperty(false);
	}
	
	@Override
	public UserData getCurrentUser() {
		return myCurrentUser;
	}

	@Override
	public boolean addUser(UserData data) {
		UserData currUser = myDatabase.addNewUser(data);
		return nullCheck(currUser, "ALREADY A USER");
	}

	@Override
	public boolean selectUser(UserData data) {
		UserData currentUser = myDatabase.getUser(data);
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
	
	@Override
	public void saveAllUsers() {
		myDatabase.saveUsers();
	}
	
	@Override
	public void signOut() {
		myCurrentUser = null;
		myHasUser.set(false);
	}
	
	public ReadOnlyBooleanProperty hasCurrentUser() {
		return myHasUser;
	}
	
	private boolean nullCheck(UserData data, String message) {
		if (data == null) {
			throw new FrontEndException(message);
		} else {
			myCurrentUser = data;
			myHasUser.set(true);
			return true;
		}
	}

}
