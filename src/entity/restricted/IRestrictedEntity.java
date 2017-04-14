package entity.restricted;

import java.util.Observer;

import components.entityComponents.ImagePropertiesComponent;

import java.util.Observer;

import javafx.geometry.Dimension2D;
import gameView.Coordinate;
/**
 * Interface for objects which the front end will receive
**/

public interface IRestrictedEntity {
	/**
	 * 
	 * @return ID of RestrictedEntity
	 */
	public int getID();
	/**
	 * 
	 * @return Coordinate of Entities location
	 */
	public Dimension2D getLocation();
	/**
	 * 
	 * @return String which is the ImagePath of the Entity
	 */
	public String getRestrictedImagePath();
	/**
	 * 
	 * @return ImagePropertiesComponent
	 */
	public Dimension2D getRestrictedIPComponent();
	
	
	public void addObserver(Observer obs);

}
