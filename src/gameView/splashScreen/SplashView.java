package gameView.splashScreen;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import gameView.UIView;

public class SplashView {
	
	private UIView myView;
	private Scene myScene;
	private BorderPane myPane;
	private static final String SPLASH_BACKGROUND = "/resources/images/background.gif";
	private static final String MAIN_BACKGROUND = "/resources/images/mainpage.gif";
	
	public SplashView(UIView view) {
		myView = view;
		myPane = new BorderPane();
		myPane.setId("splashpane");
		myScene = new Scene(myPane, UIView.DEFAULT_SIZE.width, UIView.DEFAULT_SIZE.height);
		myScene.getStylesheets().add(this.getClass().getResource("/resources/SplashScreen.css").toExternalForm());
		setBorderPane();     
		transitionToMain();     
		
	}
	
	public Scene getSplashScene() {
		return myScene;
	}
	
	private void setBorderPane() {
		Label lab = makeLabel("RainDrop salad", "splashlabel");
		myPane.setCenter(lab);		 
	}
	
	private void transitionToMain() {
		BorderPane newScene = new BorderPane();
		newScene.setId("mainpane");
		createMainScene(newScene);
		PauseTransition pause = new PauseTransition(Duration.seconds(7));
		pause.setOnFinished(event -> {
				System.out.print("what2");
				myPane = newScene;
				myScene.setRoot(myPane);
				runMainScene();
		});
		pause.play();
	}
	
	private void createMainScene(BorderPane pane) {
		Label title = makeLabel("Choose A Mode", "mainlabel");
		BorderPane.setMargin(title, new Insets(10, 10 , 10, 10));
		pane.setTop(title);
		
		
	}
	
	private void runMainScene() {
		
	}
	
	private Image makeImage(String s) {
		return new Image(this.getClass().getResourceAsStream(s));
	}
	
	private Label makeLabel(String label, String id) {
		Label newLabel = new Label(label);
		newLabel.setId(id);
		return newLabel;
	}

}
