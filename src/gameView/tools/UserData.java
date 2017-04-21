package gameView.tools;

import javafx.scene.image.Image;

public class UserData {

	private String myFirstName;
	private String myLastName;
	private Image myImage;
	
	public UserData(String firstName, String lastName, Image image) {
		myFirstName = firstName;
		myLastName = lastName;
		myImage = image;
	}
	
	public String getFirstName(){
		return myFirstName;
	}
	
	public String getLastName() {
		return myLastName;
	}
	
	public Image getProfilePicture() {
		return myImage;
	}
}
