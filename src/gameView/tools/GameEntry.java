package gameView.tools;

import java.util.ArrayList;
import java.util.Collection;

import javafx.beans.property.SimpleStringProperty;

public class GameEntry {

	private final SimpleStringProperty myFirstValue;
	private final SimpleStringProperty mySecondValue;
	
	
	/**
	 * Used to create a table view in the profile screen. To add a potential new value, add a private final value (such as myFirstValue),
	 * then add the correct getters/setters in the extact same format as below
	 * @param game - firstvalue	
	 * @param value - secondvalue
	 */
	public GameEntry(String game, String value) {
		this.myFirstValue = new SimpleStringProperty(game);
		this.mySecondValue = new SimpleStringProperty(value);
	}
	
	public String getMyFirstValue() {
		return myFirstValue.get();
	}
	
	public void setMyFirstValue(String myFirstValue) {
		this.myFirstValue.set(myFirstValue);
	}
	
	public String getMySecondValue() {
		return mySecondValue.get();
	}
	
	public void setMySecondValue(String mySecondValue) {
		this.mySecondValue.set(mySecondValue);
	}

	public Collection<String> getValues() {
		return new ArrayList<String>(){{ add(myFirstValue.getValue()); add(mySecondValue.getValue());}};
	}
	
}
