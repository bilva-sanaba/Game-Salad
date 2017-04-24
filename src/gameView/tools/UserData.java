package gameView.tools;

import javafx.scene.image.ImageView;

public class UserData {

	private String myName;
	private String myPassword;
	//private String myLastName;
	private ImageView myImage;
	
	public UserData(String name, String password, ImageView image) {
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
	
	public ImageView getProfilePicture() {
		return myImage;
	}
}
