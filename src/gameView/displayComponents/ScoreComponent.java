package gameView.displayComponents;

import gameView.UIView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class ScoreComponent extends UIDisplayComponent {

	private HBox myScore;
	private int playerScore=0;
	
	public ScoreComponent(String name){
		super(name);
	}
	@Override
	public Region getDisplay() {
		return myScore;
	}

	@Override
	public Pos getPos() {
		// TODO Auto-generated method stub
		return Pos.TOP_LEFT;
	}

	@Override
	protected void setID() {
		// TODO Auto-generated method stub
		Label lbl = new Label("Score: " + playerScore);
		myScore = new HBox();
		myScore.setPrefSize((UIView.DEFAULT_SIZE.width/20)*3, (UIView.DEFAULT_SIZE.width/10)*0.5);
		myScore.getChildren().add(lbl);
	}
	
	public void increaseScore(){
		playerScore++;
	}

}
