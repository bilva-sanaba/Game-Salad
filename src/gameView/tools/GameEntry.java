package gameView.tools;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class GameEntry extends HBox {

	
	
	public GameEntry(String game, Double points, double size) {
		setPrefWidth(size);
		HBox gameBox = makeBox(game, size/1.2);
		HBox pointBox = makeBox(points.toString(), size - gameBox.getPrefWidth());
		getChildren().addAll(gameBox, pointBox);
	}
	
	public GameEntry getBox() {
		return this;
	}
	
	private HBox makeBox(String game, double size) {
		Label lab = new Label(game);
		HBox gameTitle = new HBox(lab);
		gameTitle.setAlignment(Pos.CENTER_LEFT);
		gameTitle.setPrefWidth(size/1.2);
		return gameTitle;
	}
	
}
