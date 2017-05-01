package gameView.splashScreen;

import java.util.Collection;

import entity.SplashEntity;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import gameView.AbstractViewer;
import gameView.ICommandView;
import gameView.UIView;
import gameView.commands.AbstractCommand;
import gameView.tools.ResourceRetriever;
import gameView.userInput.IUserInputData;

public class SplashView extends AbstractViewer {

	private static final String myName = SplashView.class.getSimpleName();
	private static final String SPLASH_LABEL = "RainDrop Salad";
	private final String NEWLINE = "\n";
	
	protected Scene myScene;
	private BorderPane myPane;
	private Collection<AbstractCommand> myCommands;
	
	
	public SplashView(UIView view, Stage s, IUserInputData input) {
		super(view, s, input);
		myCommands = getCommands(myName);
		myPane = new BorderPane();
		myScene = new Scene(myPane, UIView.DEFAULT_SIZE.width,
				UIView.DEFAULT_SIZE.height);
		addBackground();
		this.buildMainScene();
		//this.transitionToMain();
	}
	
	public Scene getScene() {
		return myScene;
	}
	
	private void addBackground() {
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));
	}

	private void buildMainScene() {
		Label lab = makeLabel(SPLASH_LABEL, "mainlabel");
		myPane.setId("mainpane");
		BorderPane.setMargin(lab, new Insets(10, 10, 10, 10));
		myPane.setCenter(lab);
		VBox box = new VBox(20);
		box.setId("mainbox");
		box.setAlignment(Pos.CENTER);
		BorderPane.setMargin(box, new Insets(10, 10, 30, 10));
		myCommands.stream().forEach(c -> {  
			box.getChildren().add(makeButton(c));
		});
		
		myPane.setBottom(box);
	}
	
	private BorderPane getRoot(){
		return myPane;
	}

	@Override
	protected void setBackground(String s) {
		myPane.getCenter().setStyle(String.format(
				"-fx-background-image: url(\"%s\");"
				+ "-fx-background-repeat: stretch;"
				+ "-fx-background-position: center center;"
				+ "-fx-background-size: cover;", s));
		
	}
}
