package entitiy.restricted;

import java.util.Observer;

import gameView.Coordinate;
import javafx.beans.Observable;
/**
 * Interface for objects which the front end will receive
 * @author Bilva
 *
 */
public interface IRestrictedEntity {
	/**
	 * 
	 * @return Coordinate of Entities location
	 */
	public Coordinate getLocation();
	/**
	 * 
	 * @return String which is the ImagePath of the Entity
	 */
	public String getImagePath();
	
	
	public void addObserver(Observer obs);

}
