package gameView.userInput;

import javafx.beans.property.ReadOnlyDoubleProperty;

public interface IUserInputData {

	public ReadOnlyDoubleProperty getRewind();
	
	public void setRewind(double d);
}
