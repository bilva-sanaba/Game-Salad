package controller;

import java.util.Observer;

/**
 * Observable properties to be implemented by list in controller
 * @author Jacob
 *
 */
public interface Subject {
	
	/**
	 * Add an observer
	 * @param o
	 */
	public void register(Observer o);
	
	/**
	 * delete an observer
	 * @param o
	 */
	public void unregister(Observer o);
	
	/**
	 * Notify observer
	 */
	public void notifyObserver();
}
