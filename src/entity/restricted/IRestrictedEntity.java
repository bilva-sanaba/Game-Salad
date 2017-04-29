package entity.restricted;

import java.util.Observer;

import components.entityComponents.ImagePropertiesComponent;
import entity.Entity;

import java.util.Observer;

import javafx.geometry.Dimension2D;
import gameView.tools.Coordinate;
import javafx.geometry.Dimension2D;
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

	public Dimension2D getRestrictedLocation();
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
	public void changed(Object object);
	public Entity clone();

}
