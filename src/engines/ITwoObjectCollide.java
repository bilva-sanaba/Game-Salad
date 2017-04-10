package engines;

import components.ImagePropertiesComponent;
import components.LocationComponent;

/**
 * The point of this interface, as opposed to the ICollision interface, is that this interface is specifically to check for collisions between two objects given their locations
 * and image properties. The ICollision interface will use this interface, but the responsibility of that interface is also to send information to other parts of the program that deal with collisions once they happen.
 * The sole responsibility of this interface is, given 2 locations and box widths, find out if those boxes overlap.
 * @author Hamsa
 *
 */
public interface ITwoObjectCollide {

	/**
	 * Determines whether or not two objects collide given their location components and ImagePropertiesComponent (bounding box width and height).
	 * @param location0 LocationComponent of first object
	 * @param location1 LocationComponent of second object
	 * @param image0 ImagePropertiesComponent of first object
	 * @param image1 ImagePropertiesComponent of second object
	 * @return boolean whether or not the two objects collide or overlap
	 */
	public boolean collides(LocationComponent location0, LocationComponent location1, ImagePropertiesComponent image0, ImagePropertiesComponent image1);
	
	
}
