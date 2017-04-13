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
	private static final String SPLASH_LABEL = "RainDrop Salad";
	private final String NEWLINE = "\n";
	
	protected Scene myScene;
	private BorderPane myPane;
	private Collection<AbstractCommand> myCommands;
	
	
	public SplashView(UIView view) {
		super(view);
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
	
	public void addBackground() {
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));
	}
//
//	private void setBorderPane() {
//		Label lab = makeLabel(mySplashEntity.getDisplayName(), "splashlabel");
//		VBox vb = new VBox();
//		
//	}

//	private void someshit() {
//		vb.getChildren().add(lab);
//		lab.setFont(new Font("Arial", 30));
//		Label lbl = makeLabel(NEWLINE + se.getInstructions(), "instructions");
//		lbl.setFont(new Font("Verdana", 20));
//		vb.getChildren().add(lbl);
//		BorderPane.setAlignment(vb, Pos.CENTER);
//		myPane.setCenter(vb);
//		VBox playbox = new VBox(20);
//		playbox.setId("playbox");
//		playbox.setAlignment(Pos.CENTER);
//		BorderPane.setMargin(playbox, new Insets(10, 30, 10, 10));
//		Button b = makeButton(myCommands.iterator().next());
//		playbox.getChildren().add(b);
//		b.setOnAction(e->transitionToMain());
//		myPane.setCenter(playbox);
//	}
//	private void transitionToMain() {
//		buildMainScene();
//		PauseTransition pause = new PauseTransition(Duration.seconds(7));
//		pause.setOnFinished(event -> {
//			//myPane = newScene;   
//			myScene.setRoot(myPane);
//		});
//		myScene.setRoot(myPane);
//		//pause.play();
//	}

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
	
	public BorderPane getRoot(){
		return myPane;
	}
}
