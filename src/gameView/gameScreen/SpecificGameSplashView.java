package gameView.gameScreen;


import java.util.Collection;

import entity.SplashData;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.UIViewInterface;
import gameView.commands.AbstractCommand;
import gameView.tools.ResourceRetriever;
import gameView.userInput.IUserInputData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;

/**
 * @author Jacob
 */

public class SpecificGameSplashView extends AbstractViewer {
	
	private static final String myName = SpecificGameSplashView.class.getSimpleName();
	
	private SplashData mySplashEntity;
	private Scene myScene;
	private Collection<AbstractCommand> myCommands;
	private BorderPane myBP;
	private HBox myButtonContainer;
	
	public SpecificGameSplashView(UIViewInterface myGameView, Stage s, IUserInputData input, SplashData se){
		super(myGameView, s, input);
		mySplashEntity = se;
		myCommands = getCommands(myName);
		myBP = new BorderPane();
		buildScene();
	}

	@Override
	public Scene getScene() {
		return myScene;
	}
	
	public String getInstructions() {
		return mySplashEntity.getInstructions();
	}
	
	private void buildScene() {
		addBackground(mySplashEntity.getBackgroundFilePath());
		Label lab = makeLabel(mySplashEntity.getGameTitle(), "gamelabel");
		BorderPane.setAlignment(lab, Pos.CENTER);
		myButtonContainer = new HBox(20);
		myButtonContainer.setAlignment(Pos.CENTER);
		BorderPane.setMargin(myButtonContainer, new Insets(10, 10, 30, 10));
		myBP.setTop(lab);
		myCommands.stream()
			.forEach(c -> {
				myButtonContainer.getChildren().add(makeButton(c));
		});
		setUserCommand();
		myBP.setBottom(myButtonContainer);
		addInstructions();
		myScene = new Scene(myBP, UIView.DEFAULT_SIZE.width, UIView.DEFAULT_SIZE.height); 
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));
		
	}

	private void addInstructions() {
		Label lab = makeLabel(getInstructions(), "instructionslabel");
		lab.setStyle("-fx-wrap-text: true");
		myBP.setCenter(lab);
	}
	
	protected void setBackground(String bg){
		Image background = new Image(getClass().getClassLoader().getResourceAsStream(bg));
		BackgroundSize size = new BackgroundSize(0,0, true, true, true, true);
		BackgroundImage image = new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		myBP.setBackground(new Background(image));
	}

	@Override
	protected Pane getButtonContainer() {
		return myButtonContainer;
	}
	
	@Override 
	public void runGame(){
		getView().runGame();
	}
}