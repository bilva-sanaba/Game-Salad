package gameView.displayComponents;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import gameView.tools.DisplayEnum;
import gamedata.IRestrictedGameData;

public class LevelComponent extends UIDisplayComponent {

	private Label myLabel;
	private ReadOnlyDoubleProperty myLevel;
	
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
		return myLabel;
	}

	@Override
	public DisplayEnum getPos() {
		return DisplayEnum.TOP_CENTER;
	}

	@Override
	protected void setID() {
		myLevel = setValue(getData().getLevel());
		myLabel = new Label();
		setLabel();
		
	}
	
	private void setLabel() {
		myLabel.setText("Level: " + myLevel.doubleValue());
		myLabel.setFont(new Font("Arial", 30));
	}

}
