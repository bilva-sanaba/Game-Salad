package gameView.splashScreen;

import java.util.Collection;

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
import gameView.tools.ResourceRetriever;

public class SplashView extends AbstractViewer {

	private static final String myName = SplashView.class.getSimpleName();
	private Scene myScene;
	private BorderPane myPane;
	private Collection<AbstractCommand> myCommands;
	

	public SplashView(UIView view) {
		super(view);
		myCommands = getCommands(myName);
		myPane = new BorderPane();
		myPane.setId("splashpane");
		myScene = new Scene(myPane, UIView.DEFAULT_SIZE.width,
				UIView.DEFAULT_SIZE.height);
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));//getStyleSheets(this, myName));
		this.setBorderPane();
		this.transitionToMain();
	}

	public Scene getScene() {
		return myScene;
	}

	private void setBorderPane() {
		Label lab = makeLabel("RainDrop Salad", "splashlabel");
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
		VBox box = new VBox(20);
		box.setId("mainbox");
		box.setAlignment(Pos.CENTER);
		BorderPane.setMargin(box, new Insets(10, 30, 10, 10));
		myCommands.stream().forEach(c -> {
			box.getChildren().add(makeButton(c));
		});
		myPane.setRight(box);
	}
}
