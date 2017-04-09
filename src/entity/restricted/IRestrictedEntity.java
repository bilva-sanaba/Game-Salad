package entity.restricted;

import java.util.Observer;

import java.util.Observer;

import gameView.Coordinate;
/**
 * Interface for objects which the front end will receive
 * @author Bilva
 *
 */
<<<<<<< HEAD:src/entity/restricted/IRestrictedEntity.java
public interface IRestrictedEntity extends Observer{
	
	/**
	 * 
	 * @return ID of RestrictedEntity
	 */
	public int getID();
=======
public interface IRestrictedEntity {
>>>>>>> hjt8:src/entitiy/restricted/IRestrictedEntity.java
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
