package gamedata;


import achievements.Achievement;
import components.entityComponents.LocationComponent;
import entity.SplashEntity;
import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameData implements IGameData,IRestrictedGameData{
	
	private DoubleProperty points = new SimpleDoubleProperty();
	private DoubleProperty lives = new SimpleDoubleProperty(); 
	private IRestrictedEntityManager restrictedEntityManager; 
	private DoubleProperty level = new SimpleDoubleProperty(); 
	private LocationComponent mainPlayerLocation;
	
	private StringProperty myAchievement = new SimpleStringProperty();

	private StringProperty music = new SimpleStringProperty();
	
	public GameData(double p, double l, IRestrictedEntityManager rem, double lvl, LocationComponent lc, String ac, String m){
		points.setValue(p);
		lives.setValue(l);
		restrictedEntityManager = rem;
		level.setValue(lvl);
		mainPlayerLocation = lc;
		myAchievement.setValue(ac);
		music.setValue(m);
	}
	
	public DoubleProperty getPointsProperty(){
		return points;
	}
	public DoubleProperty getLivesProperty(){
		return lives;
	}
	public IRestrictedEntityManager getRestrictedEntityManager(){
		return restrictedEntityManager;
	}
	public DoubleProperty getLevelProperty(){
		return level; 
	}
	public LocationComponent getMainLocation(){
		return mainPlayerLocation;
	}
	public StringProperty getMusicProperty(){
		return music;
	}
	
	public StringProperty getAchievementProperty(){
		return myAchievement;
	}
	
	
	public ReadOnlyDoubleProperty getPoints(){
		return (ReadOnlyDoubleProperty) points;
	}
	public ReadOnlyDoubleProperty getLives(){
		return (ReadOnlyDoubleProperty) lives;
	}
	
	public ReadOnlyDoubleProperty getLevel(){
		return (ReadOnlyDoubleProperty) level; 
	}

	public String getMusic(){
		return music.get();
	}
	
	public String getAchievement(){
		return myAchievement.get();
	}
	
	public void setPoints(double d){
		if (!checkEquality(d, points.doubleValue())) {
			points.setValue(d);
		}
		
//		points.notify();
	}
	public void setLives(double d){
		if (!checkEquality(d, lives.doubleValue())) {
			lives.setValue(d);
		}
//		lives.notify();
	}
	public void setRestrictedEntityManager(IRestrictedEntityManager rem){
		restrictedEntityManager = rem;
	}
	public void setLevel(double d){
		if (!checkEquality(d, level.doubleValue())) {
			level.setValue(d);
		}
//		level.notify();
	}
	public void setMainLocation(LocationComponent lc){
		mainPlayerLocation = lc;
	}
	public void setMusic(String s){
		music.setValue(s);
//		music.notify();
	}

	
	private boolean checkEquality(double a, double b) {
		return (a == b);
	}
	public void setAchievement(String ac){
		myAchievement.setValue(ac);
	}
}