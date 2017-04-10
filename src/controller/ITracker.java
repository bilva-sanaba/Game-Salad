package controller;

/**
 * Interface to manage observable moments (Collision, Movement, etc.)
 * 
 * @author Jacob
 *
 */
public interface ITracker {

	/**
	 * Accesses changed message in the tracker(Type of collision, etc.)
	 * 
	 * @return
	 */
	public String getMessage();

	/**
	 * Changes the message in the tracker(e.g. from no collision to collision)
	 */
	public void changeMessage(String message);

}
