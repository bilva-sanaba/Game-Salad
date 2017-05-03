package gameView.endScreen;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.UIViewInterface;
import gameView.tools.ResourceRetriever;
import gameView.userInput.IUserInputData;

public class EndScreen extends AbstractViewer {

	private static final String myName = EndScreen.class.getSimpleName();
	
	private Scene myScene;
	private BorderPane myBP;
	
	
	public EndScreen(UIViewInterface view, Stage s, IUserInputData userInput, String endMessage, Double points) {
		super(view, s, userInput);
		buildScene(endMessage, points.toString());
	}

	@Override
	public Scene getScene() {
		return myScene;
	}
	
	@Override
	protected void setBackground(String s) {
		myBP.getCenter().setStyle(String.format(
				"-fx-background-image: url(\"%s\");"
				+ "-fx-background-repeat: stretch;"
				+ "-fx-background-position: center center;"
				+ "-fx-background-size: cover;", s));
	}

	@Override
	protected Pane getButtonContainer() {
		return myBP;
	}

	
	private void buildScene(String message, String points) {
		myBP = new BorderPane(buildMain(message, points), null, null, null,
				null);
		myScene = new Scene(myBP, UIView.DEFAULT_SIZE.width, UIView.DEFAULT_SIZE.height);
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName)); 
	}

	private VBox buildMain(String message, String points) {
		VBox box = new VBox(30);   
		box.setAlignment(Pos.CENTER);
		Label main = makeLabel(message, "ending");
		Label score = makeLabel(String.format("Score: %s", points),"score");
		box.getChildren().addAll(main, score);
		getCommands(myName).stream()  
		.forEach(c -> {
			box.getChildren().add(makeButton(c));
		});
		return box;
	}
	
}
