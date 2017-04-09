package entitiy.restricted;

import java.util.Observer;

import gameView.Coordinate;
/**
 * Interface for objects which the front end will receive
 * @author Bilva
 *
 */
public interface IRestrictedEntity extends Observer{
	
	/**
	 * 
	 * @return ID of RestrictedEntity
	 */
	public int getID();
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

}
