package gameView.tools;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

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

	
}
