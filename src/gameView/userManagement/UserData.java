package gameView.userManagement;

import gameView.tools.ImageViewContainer;
import gamedata.VoogaImmutableObservableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javafx.scene.image.Image;

public class UserData {
	
	private String username;
	private String password;
	private String image;
	private HashMap<String, Double> myGameScores;
	private Set<String> myAchievements;
	private Collection<String> myGames;
	private HashMap<String, String> mySavedGameMap;
	
	public UserData(String name, String passwordString, String imageString) {
		username = name;
		password = passwordString;
		myGameScores = new HashMap<String, Double>();
		mySavedGameMap = new HashMap<String, String>();
		myAchievements = new HashSet<String>();
		myGames = new ArrayList<String>();
		image = imageString == null ? "" : imageString;
	}
	
	/**
	 * @return name of user
	 */
	public String getName(){
		return username;
	}
	
	/**
	 * Adds a new high score to a specific game
	 * @param game - current game being played 
	 * @param points - corresponding score
	 */
	public void addPoints(String game, Double points) {
		if (mySavedGameMap.containsKey(game)) {
			addPoints(mySavedGameMap.get(game), points);
		} else if (myGameScores.get(game) == null || myGameScores.get(game) < points) {
			myGameScores.put(game, points);
		}
	}
	
	/**
	 * Adds new achievements 
	 * @param achieve - list of achievements the user has accrued during playing session
	 */
	public void addAchievement(VoogaImmutableObservableList<String> achieve) {
		Iterator<String> it = achieve.iterator();
		while (it.hasNext()) {
			myAchievements.add(it.next());
		}
	}
	
	/**
	 * @return Iterator of all the games
	 */
	public Iterator<String> getGameScores() {
		return myGameScores.keySet().iterator();
	}
	
	/**
	 * @return iterator of all the achievements
	 */
	public Iterator<String> getAchievements() {
		return myAchievements.iterator();
	}
	
	/**
	 * @param key - a specific game
	 * @return - String value of the number of points for that game
	 */
	public String getPointValue(String key) {
		return new Double(myGameScores.get(key).doubleValue()).toString();
	}
	
	/**
	 * @return - user password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @return ImageViewContainer of users image
	 */
	public ImageViewContainer getProfilePicture() {
		return makeImage(image);
	}
	
	/**
	 * Changes the user's profile picture
	 * @param newImage - new picture
	 */
	public void changePicture(String newImage) {
		image = newImage;
	}
	
	/**
	 * Adds a user-specific game -- if a user saves during gameplay, it will add the current game to allow user to load and pick up 
	 * where they left off
	 * @param file
	 */
	public void addGame(String originalGame, String newGame) {
		myGames.add(newGame);
		mySavedGameMap.put(newGame, originalGame);
	}
	
	/**
	 * @return an unmodifiable list of all the user's games
	 */
	public Collection<String> getGames() {
		return Collections.unmodifiableCollection(myGames);
	}
	
	private ImageViewContainer makeImage(String s) {
		if (s.equals("")) {
			return null;
		}
		ImageViewContainer toAdd = new ImageViewContainer(new Image(image), image);
		return toAdd;
	}
}
