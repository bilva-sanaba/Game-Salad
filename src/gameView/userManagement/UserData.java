package gameView.userManagement;

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
	public ImageView getProfilePicture() {
		return makeImage(image);
	}
	
	private ImageView makeImage(String s) {
		if (s == null) {
			return null;
		}
		ImageView toAdd = new ImageView(new Image(new File(s).toURI().toString()));
		return toAdd;
	}
}
