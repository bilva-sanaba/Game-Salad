package view_interfaces;

import gameView.Coordinate;
import gameView.UIImageProperty;

/**
 * Wrapper class for the UI game objects. Generic wrapper to encompass every
 * possible kind of character
 * 
 * @author Henry
 *
 */
public interface UIImageModelInterface {

	/**
	 * Retrieves location of the Object
	 * 
	 * @return Coordinate (X, Y) of the location
	 */
	public Coordinate getLocation();

	/**
	 * @return UIImageProperty of the object
	 */
	public UIImageProperty getProperties();
}
