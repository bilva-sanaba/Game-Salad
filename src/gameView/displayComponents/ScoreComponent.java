package gameView.displayComponents;


import gameView.UIView;
import gameView.tools.DisplayEnum;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

public class ScoreComponent extends UIDisplayComponent {

	private HBox myScore;
	
	public ScoreComponent(String name){
		super(name);
		//playerScore = getConfig().getScore();
	}
	@Override
	public Region getDisplay() {
		return myScore;
	}

	@Override
	public DisplayEnum getPos() {
		return DisplayEnum.TOP_CENTER;
	}

	@Override
	protected void setID() {
		// TODO Auto-generated method stub
		Label lbl = new Label("Score: " + getConfig().getScore());
		lbl.setFont(new Font("Arial", 30));
		myScore = new HBox();
		myScore.setPrefSize((UIView.DEFAULT_SIZE.width/20)*3, (UIView.DEFAULT_SIZE.width/10)*0.5);
		myScore.getChildren().add(lbl);
	}
}
