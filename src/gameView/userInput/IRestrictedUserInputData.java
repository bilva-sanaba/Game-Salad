package gameView.userInput;

import javafx.beans.property.ReadOnlyDoubleProperty;

/**
 * User Input Data interface to be passed to backend
 * @author Henry
 *
 */
public interface IRestrictedUserInputData {

	/**
	 * Contains the value for the rewind function
	 * @return
	 */
	public ReadOnlyDoubleProperty getRewind();
	
	
}
