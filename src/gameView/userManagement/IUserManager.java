package gameView.userManagement;

public interface IUserManager {

	
	public UserData getCurrentUser();
	
	public boolean addUser(UserData data);
	
	public boolean selectUser(UserData data);
	
	public boolean facebookUser(UserData data);
}
