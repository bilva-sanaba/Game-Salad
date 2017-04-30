package gameView.userManagement;

import gameView.tools.ImageViewContainer;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserData {

	public static final List<String> DATA_FIELDS = Arrays.asList("username", "password", "image");
	
	
	private String username;
	private String password;
	//private String myLastName;
	private String image;
	private HashMap<String, Double> myGameScores;
	
	public UserData(String name, String passwordString, String imageString) {
		username = name;
		password = passwordString;
		myGameScores = new HashMap<String, Double>();
		//myLastName = lastName;
		if (imageString == null) {
			image = "";
		} else {
			image = imageString;
		}
	}
	
	public String getName(){
		return username;
	}
	
//	public String getLastName() {
//		return myLastName;
//	}
	
	public void addPoints(String game, Double points) {
		if (myGameScores.get(game) == null || myGameScores.get(game) < points) {
			myGameScores.put(game, points);
		}
	}
	
	public Iterator<String> getGames() {
		return myGameScores.keySet().iterator();
	}
	
	public Double getPointValue(String key) {
		return new Double(myGameScores.get(key).doubleValue());
	}
	
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
