package gameView.userInput;

import javafx.beans.property.ReadOnlyDoubleProperty;

/**
 * User input interface to be used by front end
 * @author Henry
 *
 */
public interface IUserInputData {

	/**
	 * Get the rewind value for 
	 * @return
	 */
	public ReadOnlyDoubleProperty getRewind();
	
	/**
	 * set the rewind value
	 * @param d
	 */
	public void setRewind(double d);
}
