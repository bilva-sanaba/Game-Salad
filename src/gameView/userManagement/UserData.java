package gameView.userManagement;

import gameView.tools.ImageViewContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javafx.scene.image.Image;

public class UserData {

	public static final List<String> DATA_FIELDS = Arrays.asList("username", "password", "image");
	
	
	private String username;
	private String password;
	private String image;
	private HashMap<String, Double> myGameScores;
	private Collection<String> myAchievements;
	private Collection<String> myGames;
	
	public UserData(String name, String passwordString, String imageString) {
		username = name;
		password = passwordString;
		myGameScores = new HashMap<String, Double>();
		myAchievements = new ArrayList<String>();
		myGames = new ArrayList<String>();
		if (imageString == null) {
			image = "";
		} else {
			image = imageString;
		}
	}
	
	public String getName(){
		return username;
	}
	
	public void addPoints(String game, Double points) {
		if (myGameScores.get(game) == null || myGameScores.get(game) < points) {
			myGameScores.put(game, points);
		}
	}
	
	public void addAchievement(String achieve) {
		myAchievements.add(achieve);
	}
	
	public Iterator<String> getGameScores() {
		return myGameScores.keySet().iterator();
	}
	
	public Iterator<String> getAchievements() {
		return myGameScores.keySet().iterator();
		//return myAchievements.iterator();
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
	
	public void addGame(String file) {
		myGames.add(file);
	}
	
	public Collection<String> getGames() {
		return Collections.unmodifiableCollection(myGames);
	}
	
	private ImageViewContainer makeImage(String s) {
		if (s == null) {
			return null;
		}
		ImageViewContainer toAdd = new ImageViewContainer(new Image(image), image);
		return toAdd;
	}
}
