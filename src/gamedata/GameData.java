package gamedata;


import java.util.List;

import components.entityComponents.LocationComponent;
import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameData implements IGameData,IRestrictedGameData{

	private DoubleProperty points = new SimpleDoubleProperty();
	private IntegerProperty lives = new SimpleIntegerProperty(); 
	private IRestrictedEntityManager restrictedEntityManager; 
	private IntegerProperty level = new SimpleIntegerProperty(); 
	private LocationComponent mainPlayerLocation;
	private VoogaObservableList<String> myAchievements;
	private StringProperty music = new SimpleStringProperty();
	private boolean myCamera;

	public GameData(double p, double l, IRestrictedEntityManager rem, double lvl, LocationComponent lc, List<String> ac, String m,boolean camera){
		myAchievements = new VoogaObservableList<String>(ac);
		points.setValue(p);
		lives.setValue(l);
		restrictedEntityManager = rem;
		level.setValue(lvl);
		mainPlayerLocation = lc;
		//		observableList.add(ac);
		music.setValue(m);
		myCamera = camera;
	}

	public boolean getCamera() {
		return myCamera;
	}
	public DoubleProperty getPointsProperty(){
		return points;
	}
	public IntegerProperty getLivesProperty(){
		return lives;
	}
	public IRestrictedEntityManager getRestrictedEntityManager(){
		return restrictedEntityManager;
	}
	public IntegerProperty getLevelProperty(){
		return level; 
	}
	public LocationComponent getMainLocation(){
		return mainPlayerLocation;
	}
	public ReadOnlyStringProperty getMusicProperty(){
		return music;
	}

	public ReadOnlyDoubleProperty getPoints(){
		return (ReadOnlyDoubleProperty) points;
	}
	public ReadOnlyIntegerProperty getLives(){
		return (ReadOnlyIntegerProperty) lives;
	}

	public ReadOnlyIntegerProperty getLevel(){
		return (ReadOnlyIntegerProperty) level; 
	}

	public ReadOnlyStringProperty getMusic(){
		return music;
	}

	public VoogaImmutableObservableList<String> getAchievement(){
		return myAchievements;
	}

	public void setPoints(double d){
		if (!checkEquality(d, points.doubleValue())) {
			points.setValue(d);
		}
	}
	public void setLives(double d){
		if (!checkEquality(d, lives.doubleValue())) {
			lives.setValue(d);
		}
	}
	public void setRestrictedEntityManager(IRestrictedEntityManager rem){
		restrictedEntityManager = rem;
	}
	public void setLevel(double d){
		if (!checkEquality(d, level.doubleValue())) {
			level.setValue(d);
		}
	}
	public void setMainLocation(LocationComponent lc){
		mainPlayerLocation = lc;
	}
	public void setMusic(String s){
		music.setValue(s);
	}


	private boolean checkEquality(double a, double b) {
		return (a == b);
	}

	public void setAchievement(String ac){
		myAchievements.add(ac);
	}



}