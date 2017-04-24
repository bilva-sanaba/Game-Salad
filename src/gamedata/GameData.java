package gamedata;


import components.entityComponents.LocationComponent;
import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class GameData {
	private DoubleProperty points = new SimpleDoubleProperty();
	private DoubleProperty lives = new SimpleDoubleProperty(); 
	private IRestrictedEntityManager restrictedEntityManager; 
	private DoubleProperty level = new SimpleDoubleProperty(); 
	private LocationComponent mainPlayerLocation;
	
	public GameData(double p, double l, IRestrictedEntityManager rem, double lvl, LocationComponent lc){
		points.setValue(p);
		System.out.println(points.getValue());
		lives.setValue(l);
		restrictedEntityManager = rem;
		level.setValue(lvl);
		mainPlayerLocation = lc;
		
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
	public LocationComponent mainLocation(){
		return mainPlayerLocation;
	}
}