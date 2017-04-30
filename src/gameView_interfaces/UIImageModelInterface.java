package gameView_interfaces;

import gameView.UIImageProperty;
import gameView.tools.Coordinate;

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
