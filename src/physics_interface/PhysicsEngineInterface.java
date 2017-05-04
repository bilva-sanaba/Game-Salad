package physics_interface;

import gameView.tools.Coordinate;

/**
 * Engine for the physics of the game. This will be used for certain
 * circumstances, such as a jump that needs gravity, or a bounce that needs to
 * calculate certain forces. Superclass that will be extended to other
 * subclasses (such as gravity or bounce).
 * 
 * @author Henry
 *
 */
public interface PhysicsEngineInterface {

	/**
	 * Given the properties of the object, calculate the next position
	 * incorporating appropriate physics
	 * 
	 * @param props
	 *            - properties of the UIObject
	 * @return
	 */
	//public Coordinate calcNextPosition(UIImageProperty props);
}
