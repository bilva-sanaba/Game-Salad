package gameView.userInput;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;

public interface IUserInputData {

	public ReadOnlyDoubleProperty getRewind();
	
	public void setRewind(double d);
}
