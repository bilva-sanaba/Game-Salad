package gameView.userManagement;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserData {

	public static final List<String> DATA_FIELDS = Arrays.asList("username", "password", "image");
	
	
	private String myName;
	private String myPassword;
	//private String myLastName;
	private ImageView myImage;
	
	public UserData(String name, String password, String image) {
		myName = name;
		myPassword = password;
		//myLastName = lastName;
		myImage = makeImage(image);
	}
	
	public String getName(){
		return myName;
	}
	
//	public String getLastName() {
//		return myLastName;
//	}
	
	public String getPassword() {
		return myPassword;
	}
	public ImageView getProfilePicture() {
		return myImage;
	}
	
	private ImageView makeImage(String s) {
		if (s == null) {
			return null;
		}
		ImageView toAdd = new ImageView(new Image(new File(s).toURI().toString()));
		return toAdd;
	}
}
