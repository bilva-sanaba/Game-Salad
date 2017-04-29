package gameView.userManagement;

import gameView.tools.ImageViewContainer;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserData {

	public static final List<String> DATA_FIELDS = Arrays.asList("username", "password", "image");
	
	
	private String username;
	private String password;
	//private String myLastName;
	private String image;
	
	public UserData(String name, String passwordString, String imageString) {
		username = name;
		password = passwordString;
		//myLastName = lastName;
		image = imageString;
	}
	
	public String getName(){
		return username;
	}
	
//	public String getLastName() {
//		return myLastName;
//	}
	
	public String getPassword() {
		return password;
	}
	public ImageViewContainer getProfilePicture() {
		return makeImage(image);
	}
	
	private ImageViewContainer makeImage(String s) {
		if (s == null) {
			return null;
		}
		ImageViewContainer toAdd = new ImageViewContainer(new Image(image), image);
		return toAdd;
	}
}
