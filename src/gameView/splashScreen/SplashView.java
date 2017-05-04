package gameView.splashScreen;

import java.util.Collection;

import javafx.geometry.Insets;
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
import gameView.commands.AbstractCommand;
import gameView.gameScreen.GameScreen;
import gameView.tools.ResourceRetriever;
import gameView.userInput.IUserInputData;
//THIS CODE IS PART OF MY MASTERPIECE
//hjt8

/**
* I am including this example as a part of my masterpiece because it is an example of an implementation of AbstractViewer. In addition,
* it shows the implementation of one of the major parts to the platform, the splashScreen, which is hugely important from a UserExperience
* standpoint.I think this class shows good design because it is highly encapsulated, and demonstrates a good example of how powerful you can make an extended viewer. 
* @author Henry
*
*/
public class SplashView extends AbstractViewer {

	private static final String SPLASH_LABEL = "RainDrop Salad";
	
	protected Scene myScene;
	private BorderPane myPane;
	private VBox myCommandContainer;
	
	
	public SplashView(UIViewInterface view, Stage s, IUserInputData input) {
		super(view, s, input);
		myPane = new BorderPane();
		myScene = new Scene(myPane, UIView.DEFAULT_SIZE.width,
				UIView.DEFAULT_SIZE.height);
		addBackground();
		this.buildMainScene();
	}
	
	public Scene getScene() {
		return myScene;
	}
	@Override
	protected String getName() {
		return SplashView.class.getSimpleName();	
	}
	
	private void addBackground() {
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,getName()));
	}

	private void buildMainScene() {
		Label lab = makeLabel(SPLASH_LABEL, "mainlabel");
		myPane.setId("mainpane");
		BorderPane.setMargin(lab, new Insets(10, 10, 10, 10));
		myPane.setCenter(lab);
		setButtonContainer();
		myPane.setBottom(myCommandContainer);
	}
	
	private void setButtonContainer() {
		myCommandContainer = new VBox(20);
		myCommandContainer.setId("mainbox");
		myCommandContainer.setAlignment(Pos.CENTER);
		BorderPane.setMargin(myCommandContainer, new Insets(10, 10, 30, 10));
		getCommands(getName()).stream().forEach(c -> {  
			myCommandContainer.getChildren().add(makeButton(c));
		});
		setUserCommand();
	}

	@Override
	protected void setBackground(String s) {
		myPane.getCenter().setStyle(String.format(
				"-fx-background-image: url(\"%s\");"
				+ "-fx-background-repeat: stretch;"
				+ "-fx-background-position: center center;"
				+ "-fx-background-size: cover;", s));
		
	}

	@Override
	protected Pane getButtonContainer() {
		return myCommandContainer;
	}
}
