package gameView.tools;

import java.util.ArrayList;
import java.util.Collection;

import javafx.beans.property.SimpleStringProperty;

public class GameEntry {

	private final SimpleStringProperty myFirstValue;
	private final SimpleStringProperty mySecondValue;
	
	public GameEntry(String game, Double points) {
		this.myFirstValue = new SimpleStringProperty(game);
		this.mySecondValue = new SimpleStringProperty(points.toString());
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
