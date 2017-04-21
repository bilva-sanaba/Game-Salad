package gameView.tools;

import javafx.scene.image.Image;

public class UserData {

	private String myName;
	//private String myLastName;
	private Image myImage;
	
	public UserData(String name, Image image) {
		myName = name;
		//myLastName = lastName;
		myImage = image;
	}
	
	public String getName(){
		return myName;
	}
	
//	public String getLastName() {
//		return myLastName;
//	}
	
	public Image getProfilePicture() {
		return myImage;
	}
}
