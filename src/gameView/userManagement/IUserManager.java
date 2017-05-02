package gameView.userManagement;


import javafx.beans.property.ReadOnlyBooleanProperty;

/**
 * Front end interface for controlling users
 * @author Henry
 *
 */
public interface IUserManager {

	/**
	 * @return current user - null if no current user
	 */
	public UserData getCurrentUser();
	
	/**
	 * Add a user to the database 
	 * @param data - user to add
	 * @return true if added, false if not (the player already exists or has no password
	 */
	public boolean addUser(UserData data);
	
	/**
	 * Select a user already made
	 * @param data - user to select
	 * @return true if successfully selected user, false if not (wrong username/password, not created)
	 */
	public boolean selectUser(UserData data);
	
	/**
	 * Facebook login
	 * @param data - facebook user
	 * @return true if successful, false if not
	 */
	public boolean facebookUser(UserData data);
	
	/**
	 * Goes through all users and saves them to UserData.xml with updated scores, games, etc. 
	 */
	public void saveAllUsers();
	
	/**
	 * Sign out as current user
	 */
	public void signOut();

	/**
	 * @return true if current user exists, false if not
	 */
	public ReadOnlyBooleanProperty hasCurrentUser();
}
