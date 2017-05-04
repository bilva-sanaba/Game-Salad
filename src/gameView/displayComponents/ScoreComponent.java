package gameView.displayComponents;


import gameView.UIView;
import gameView.tools.DisplayEnum;
import gamedata.IRestrictedGameData;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

public class ScoreComponent extends UIDisplayComponent { 

	private HBox myScore; 
	private ReadOnlyDoubleProperty myPoints;
	
	public ScoreComponent(String name, IRestrictedGameData gameData){
		super(name, gameData);
	}
	
	@Override
	public Region getDisplay() {
		if (myPoints == null) {
			return null;
		}
		return myScore;
	}

	@Override
	public DisplayEnum getPos() {
		return DisplayEnum.TOP_CENTER;
	}

	@Override
	protected void setID() {
		myPoints = (ReadOnlyDoubleProperty) setValue(getData().getPoints());
		myScore = new HBox();
		myScore.setPrefSize((UIView.DEFAULT_SIZE.width/20)*3, (UIView.DEFAULT_SIZE.width/10)*0.5);
		setLabel();
	}

	
	@Override
	protected void changedValue() {
		setLabel();
	}
	
	private void setLabel() {
		myScore.getChildren().clear();
		Label lbl = new Label("Score: " + myPoints.doubleValue());
		lbl.setFont(new Font("Arial", 30));
		myScore.getChildren().add(lbl);
	}
}
