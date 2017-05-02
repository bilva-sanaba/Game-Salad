package gameView.userManagement;


import javafx.beans.property.ReadOnlyBooleanProperty;

public interface IUserManager {

	
	public UserData getCurrentUser();
	
	public boolean addUser(UserData data);
	
	public boolean selectUser(UserData data);
	
	public boolean facebookUser(UserData data);
	
	public void saveAllUsers();
	
	public void signOut();

	public ReadOnlyBooleanProperty hasCurrentUser();
}
