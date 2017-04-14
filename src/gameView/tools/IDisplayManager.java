package gameView.tools;

import java.util.Collection;

public interface IDisplayManager {
	
	/**
	 * Add a UIDisplayComponent to active displays, then add the active component to the GameScreen View. 
	 * @param toAdd - string representing the display in the map of all displays
	 */
	public void add(String toAdd);
	
	/**
	 * Remove a UIDisplayComponent from active displays, then remove the non-active component from the GameScreen View. 
	 * @param toAdd - string representing the display in the map of all displays
	 */
	public void remove(String toRemove);
	
	/**
	 * Retrieve a collection of strings representing every display that the manager currently contains
	 * @return
	 */
	public Collection<String> getNames();
	
	/**
	 * Checks to see if a display is currently active. The display is being recognized by a string keyword
	 * @param key - string representing the display
	 * @return
	 */
	public boolean checkIfActive(String key);
	
	/**
	 * Add all active displays to View
	 */
	public void addAllActive();

}
