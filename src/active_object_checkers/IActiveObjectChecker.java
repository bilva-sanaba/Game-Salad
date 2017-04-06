package active_object_checkers;

import java.util.List;

import gameEngine_interface.Entity;

public interface IActiveObjectChecker {
	
	/**
	 * This method will return all the objects on the screen at a given time. This will stop stack overflow or memory issues with infinite platformers.
	 * @return
	 */
	public List<Entity> getActiveObjects();

}
