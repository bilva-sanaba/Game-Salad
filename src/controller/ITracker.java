package controller;

import javafx.beans.Observable;
/**
 * Interface to manage observable moments (Collision, Movement, etc.)
 * @author Jacob
 *
 */
public interface ITracker extends Observable {
	
	/**
	 * Accesses changed message in the tracker(Type of collision, etc.)
	 * @return
	 */
	public String getMessage();
	
	/**
	 * Changes the message in the tracker(e.g. from no collision to collision)
	 */
	public void changeMessage(String message);
	
}
