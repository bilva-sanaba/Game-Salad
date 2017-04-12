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
import javafx.util.Duration;
import gameView.AbstractViewer;
import gameView.ICommandView;
import gameView.UIView;
import gameView.commands.AbstractCommand;
import gameView.tools.ResourceRetriever;

public class SplashView extends AbstractViewer {

	private static final String myName = SplashView.class.getSimpleName();
	private final String NEWLINE = "\n";
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
		
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));//getStyleSheets(this, myName));
		this.setBorderPane();
		//this.transitionToMain();
	}

	public SplashEntity getSE() {
		return se;
	}
	public Scene getScene() {
		return myScene;
	}
	
	public void addBackground() {
		myScene.getStylesheets().add(
				this.getClass().getResource("/resources/SplashScreen.css")
						.toExternalForm());
	}

	private void setBorderPane() {
		Label lab = makeLabel(se.getDisplayName(), "splashlabel");
		VBox vb = new VBox();
		vb.getChildren().add(lab);
		lab.setFont(new Font("Arial", 30));
		Label lbl = makeLabel(NEWLINE + se.getInstructions(), "instructions");
		lbl.setFont(new Font("Verdana", 20));
		vb.getChildren().add(lbl);
		BorderPane.setAlignment(vb, Pos.CENTER);
		myPane.setCenter(vb);
		VBox playbox = new VBox(20);
		playbox.setId("playbox");
		playbox.setAlignment(Pos.CENTER);
		BorderPane.setMargin(playbox, new Insets(10, 30, 10, 10));
		Button b = makeButton(myCommands.iterator().next());
		playbox.getChildren().add(b);
		b.setOnAction(e->transitionToMain());
		myPane.setCenter(playbox);
	}

	private void transitionToMain() {
		buildMainScene();
		/*PauseTransition pause = new PauseTransition(Duration.seconds(7));
		pause.setOnFinished(event -> {
			//myPane = newScene;
			myScene.setRoot(myPane);
		});*/
		myScene.setRoot(myPane);
		//pause.play();
	}

	private void buildMainScene() {
		myPane = new BorderPane();
		myPane.setId("mainpane");
		Label title = makeLabel("Choose A Mode", "mainlabel");
		BorderPane.setMargin(title, new Insets(10, 10, 10, 10));
		myPane.setTop(title);
		//Reposition label
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
