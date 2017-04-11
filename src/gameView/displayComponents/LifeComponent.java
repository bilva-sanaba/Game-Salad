package gameView.displayComponents;

import gameView.UIView;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class LifeComponent extends UIDisplayComponent {

	private HBox myLives;
	private final int initial = 3;
	
	public LifeComponent(String name) {
		super(name);
	}

	@Override
	public Region getDisplay() {
		return myLives;
	}
	
	public Pos getPos() {
		return Pos.TOP_CENTER;
	}
	
	protected void setID() {
		myLives = new HBox();
		myLives.setPrefSize((UIView.DEFAULT_SIZE.width/20)*initial, (UIView.DEFAULT_SIZE.width/10)*0.5);
		for (int i = 0; i < initial; i++) {
			myLives.getChildren().add(makeLabel());
		}
		myLives.setId(getName().toLowerCase());
	} 
	 
	private ImageView makeLabel() {
		ImageView lifeLabel = new ImageView();
		lifeLabel.setId("lifelabel");
		lifeLabel.setFitHeight(myLives.getPrefHeight());
		lifeLabel.setFitWidth(myLives.getPrefWidth()/initial);
		return lifeLabel;
	} 

}
