package gameView.userInput;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class UserInputData implements IRestrictedUserInputData, IUserInputData {

	private IntegerProperty myRewind;
	
	public UserInputData() {
		myRewind = new SimpleIntegerProperty(0);
	}

	@Override
	public ReadOnlyIntegerProperty getRewind() {
		return (ReadOnlyIntegerProperty) myRewind;
	}

	@Override
	public void setRewind(int k) {
		myRewind.setValue(k);
	}
	
	
}
