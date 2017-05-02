package gameView.displayComponents;

import gameView.UIView;
import gameView.tools.DisplayEnum;
import gamedata.IRestrictedGameData;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class LifeComponent extends UIDisplayComponent {

	private HBox myLives;
	private ReadOnlyIntegerProperty myLifeNumber;
	
	public LifeComponent(String name, IRestrictedGameData gameData) {
		super(name, gameData);
	}

	@Override
	public Region getDisplay() {
		if (myLifeNumber == null) {
			return null;
		}
		return myLives;
	}
	
	@Override
	public DisplayEnum getPos() {
		return DisplayEnum.TOP_RIGHT;
	}

	@Override
	protected void changedValue() {
		addLifeImages();
	} 
	
	protected void setID() {
		myLifeNumber = (ReadOnlyIntegerProperty) setValue(getData().getLives());
		myLives = new HBox();
		addLifeImages();
		myLives.setId(getName().toLowerCase());
	} 
	
	private void addLifeImages() {
		myLives.getChildren().clear();
		myLives.setPrefSize((UIView.DEFAULT_SIZE.width/20)*myLifeNumber.intValue(), (UIView.DEFAULT_SIZE.width/10)*0.5);
		for (int i = 0; i < myLifeNumber.intValue(); i++) {
			myLives.getChildren().add(makeLabel());
		}
	}

	
	private ImageView makeLabel() {
		ImageView lifeLabel = new ImageView();
		lifeLabel.setId("lifelabel");
		lifeLabel.setFitHeight(myLives.getPrefHeight());
		lifeLabel.setFitWidth(myLives.getPrefWidth()/myLifeNumber.intValue());
		return lifeLabel;
	}

}
