package gamedata;


import components.entityComponents.LocationComponent;
import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameData implements IGameData,IRestrictedGameData{
	
	private DoubleProperty points = new SimpleDoubleProperty();
	private DoubleProperty lives = new SimpleDoubleProperty(); 
	private IRestrictedEntityManager restrictedEntityManager; 
	private DoubleProperty level = new SimpleDoubleProperty(); 
	private LocationComponent mainPlayerLocation;
	private StringProperty music = new SimpleStringProperty();
	public GameData(double p, double l, IRestrictedEntityManager rem, double lvl, LocationComponent lc, String m){
		points.setValue(p);
		lives.setValue(l);
		restrictedEntityManager = rem;
		level.setValue(lvl);
		mainPlayerLocation = lc;
		music.setValue(m);
		
	}
	
	public DoubleProperty getPoints(){
		return points;
	}
	public DoubleProperty getLives(){
		return lives;
	}
	public IRestrictedEntityManager getRestrictedEntityManager(){
		return restrictedEntityManager;
	}
	public DoubleProperty getLevel(){
		return level; 
	}
	public LocationComponent getMainLocation(){
		return mainPlayerLocation;
	}
	public StringProperty getMusic(){
		return music;
	}
	
	public void setPoints(double d){
		points.setValue(d);
	}
	public void setLives(double d){
		lives.setValue(d);
	}
	public void setRestrictedEntityManager(IRestrictedEntityManager rem){
		restrictedEntityManager = rem;
	}
	public void setLevel(double d){
		level.setValue(d);
	}
	public void setMainLocation(LocationComponent lc){
		mainPlayerLocation = lc;
	}
	public void setMusic(String s){
		music.setValue(s);
	}
}