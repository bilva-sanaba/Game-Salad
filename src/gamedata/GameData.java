package gamedata;


import components.movementcomponents.LocationComponent;
import entity.restricted.IRestrictedEntityManager;
import javafx.beans.property.DoubleProperty;

public class GameData {
	private DoubleProperty points;
	private DoubleProperty lives; 
	private IRestrictedEntityManager restrictedEntityManager; 
	private DoubleProperty level; 
	private LocationComponent mainPlayerLocation;
	
	public GameData(double p, double l, IRestrictedEntityManager rem, double lvl, LocationComponent lc){
		points.set(p);
		lives.set(l);
		restrictedEntityManager = rem;
		level.set(lvl);
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