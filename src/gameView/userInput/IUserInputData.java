package gameView.userInput;

import javafx.beans.property.ReadOnlyIntegerProperty;

public interface IUserInputData {

	public ReadOnlyIntegerProperty getRewind();
	
	public void setRewind(int k);
}
