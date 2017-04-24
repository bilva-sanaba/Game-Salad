package engines;

import java.util.List;
import java.util.Map;

import components.IComponent;
import components.entityComponents.ComponentType;
import entity.IEntity;


/**
 * The point of this interface, as opposed to the ICollision interface, is that this interface is specifically to check for collisions between two objects given their locations
 * and image properties. The ICollision interface will use this interface, but the responsibility of that interface is also to send information to other parts of the program that deal with collisions once they happen.
 * The sole responsibility of this interface is, given 2 locations and box widths, find out if those boxes overlap.
 * @author Hamsa
 *
 */
public interface ITwoObjectCollide {
	
	public static final String LEFT = "Left";
	public static final String RIGHT = "Right";
	public static final String TOP = "Top";
	public static final String BOTTOM = "Bottom";
	public static final String NONE = "No Collision";





	/**
	 * Determines whether or not two objects collide given the Components that are specified in the neededComponentsMethod().
	 * @param obj0 Map of components for first object
	 * @param obj1 Map of components for second object
	 * @return String specifying which side of object1 that object0 collides with. Use the public static finals for this returned string. 
	 */
	public String collides(IEntity entityOne, IEntity entityTwo);
	
	

}
