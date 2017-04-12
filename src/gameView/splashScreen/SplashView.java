package gameView.splashScreen;

import java.util.Collection;

import entity.SplashEntity;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import gameView.AbstractViewer;
import gameView.ICommandView;
import gameView.UIView;
import gameView.commands.AbstractCommand;

public class SplashView extends AbstractViewer {

	private static final String myName = SplashView.class.getSimpleName();
	protected Scene myScene;
	private BorderPane myPane;
	private Collection<AbstractCommand> myCommands;
	private SplashEntity se;
	

	public SplashView(UIView view, SplashEntity se) {
		super(view);
		myCommands = getCommands(myName);
		myPane = new BorderPane();
		this.se=se;
		myPane.setId("splashpane");
		myScene = new Scene(myPane, UIView.DEFAULT_SIZE.width,
				UIView.DEFAULT_SIZE.height);
		addBackground();
		this.setBorderPane();
		this.transitionToMain();
	}

	public SplashEntity getSE() {
		return se;
	}
	public Scene getScene() {
		return myScene;
	}
	
	public void addBackground() {
		myScene.getStylesheets().add(getStyleSheets(this, myName));
	}

	private void setBorderPane() {
		Label lab = makeLabel(se.getDisplayName(), "splashlabel");
		myPane.setCenter(lab);
	}

	private void transitionToMain() {
		buildMainScene();
		PauseTransition pause = new PauseTransition(Duration.seconds(7));
		pause.setOnFinished(event -> {
			//myPane = newScene;
			myScene.setRoot(myPane);
		});
		pause.play();
	}

	private void buildMainScene() {
		myPane = new BorderPane();
		myPane.setId("mainpane");
		Label title = makeLabel("Choose A Mode", "mainlabel");
		BorderPane.setMargin(title, new Insets(10, 10, 10, 10));
		myPane.setTop(title);
		Label lbl = makeLabel(se.getInstructions(), "instructions");
		myPane.setBottom(lbl); //Reposition label
		VBox box = new VBox(20);
		box.setId("mainbox");
		box.setAlignment(Pos.CENTER);
		BorderPane.setMargin(box, new Insets(10, 30, 10, 10));
		myCommands.stream().forEach(c -> {
			box.getChildren().add(makeButton(c));
		});
		myPane.setRight(box);
	}
	
	public BorderPane getRoot(){
		return myPane;
	}
}
