package gameView.displayComponents;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import gameView.tools.DisplayEnum;
import gamedata.IRestrictedGameData;

public class LevelComponent extends UIDisplayComponent {

	private HBox myBox;
	private Label myLabel;
	private ReadOnlyIntegerProperty myLevel;
	
	public LevelComponent(String name, IRestrictedGameData gameData) {
		super(name, gameData);
	}

	@Override
	protected void changedValue() {
		setLabel();
	}

	@Override
	public Region getDisplay() {
		if (myLevel == null) {
			return null;
		}
		return myBox;
	}

	@Override
	public DisplayEnum getPos() {
		return DisplayEnum.TOP_LEFT;
	}

	@Override
	protected void setID() {
		myLevel = (ReadOnlyIntegerProperty) setValue(getData().getLevel());
		myLabel = new Label();
		setLabel();
		myBox = new HBox(myLabel);
		
	}
	
	private void setLabel() {
		myLabel.setText("Level: " + myLevel.doubleValue());
		myLabel.setFont(new Font("Arial", 30));
	}

}
