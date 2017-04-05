package gameView.splashScreen;

import java.util.Collection;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import gameView.UIView;
import gameView.commands.AbstractCommand;

public class SplashView {
	
	private UIView myView;
	private Scene myScene;
	private BorderPane myPane;
	private Collection<AbstractCommand> myCommands;

//	private static final String SPLASH_BACKGROUND = "/resources/images/background.gif";
//	private static final String MAIN_BACKGROUND = "/resources/images/mainpage.gif";
	
	public SplashView(UIView view, Collection<AbstractCommand> buttons) {
		myCommands = buttons;
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
		});  
		pause.play();
	}
	
	private void createMainScene(BorderPane pane) {
		Label title = makeLabel("Choose A Mode", "mainlabel");
		BorderPane.setMargin(title, new Insets(10, 10 , 10, 10));
		pane.setTop(title);
		VBox box = new VBox(20);
		box.setId("mainbox");    
		box.setAlignment(Pos.CENTER);
		BorderPane.setMargin(box, new Insets(10, 30 , 10, 10));
		myCommands.stream()
			.forEach( c -> {
				box.getChildren().add(makeButton(c));
			});
		pane.setRight(box);       
	}
	 
	private Label makeLabel(String label, String id) {
		Label newLabel = new Label(label);
		newLabel.setId(id);
		return newLabel;
	}
	
	private Button makeButton(AbstractCommand command) { 
		Button button = new Button(command.getName());
		button.setId(command.getName().toLowerCase()); 
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {	
				command.execute(myView.getStage());
			}
		});
		return button;
	}

}
