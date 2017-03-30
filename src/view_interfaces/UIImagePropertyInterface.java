package view_interfaces;

import gameView.Coordinate;

/**
 * Wrapper class to contain all the relevant information of the UIImageModel to be passed back to the 
 * back end on collision
 * @author Henry
 *
 */
public interface UIImagePropertyInterface {

	/**
	 * @return <X, Y> location of UIImageModel
	 */
	public Coordinate getLocation();
	
	/**
	 * @return current X direction the UIImageModel
	 */
	public double getXDirection();
	
	/**
	 * @return current Y direction of the UIImageModel
	 */
	public double getYDirection();
	
	/**
	 * @return type of object that UIImageModel represents
	 */
	public String getType();
}
