package gameView.gameScreen;


import java.util.Collection;

import controller.Controller;
import entity.SplashEntity;
import gameView.AbstractViewer;
import gameView.UIView;
import gameView.commands.AbstractCommand;
import gameView.splashScreen.SplashView;
import gameView.tools.ResourceRetriever;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SpecificGameSplashView extends AbstractViewer {
	
	private static final String myName = SpecificGameSplashView.class.getSimpleName();
	
	private String myBackground;
	private SplashEntity mySplashEntity;
	private Scene myScene;
	private Collection<AbstractCommand> myCommands;
	private BorderPane myBP;
	
	public SpecificGameSplashView(UIView view, SplashEntity se){
		super(view);
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
		addBackground();
		Label lab = makeLabel(mySplashEntity.getDisplayName(), "gamelabel");
		BorderPane.setAlignment(lab, Pos.CENTER);
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		BorderPane.setMargin(hbox, new Insets(10, 10, 30, 10));
		myBP.setTop(lab);
		myCommands.stream()
			.forEach(c -> {
				hbox.getChildren().add(makeButton(c));
		});
		myBP.setBottom(hbox);
		addInstructions();
		myScene = new Scene(myBP, UIView.DEFAULT_SIZE.width, UIView.DEFAULT_SIZE.height); 
		myScene.getStylesheets().add(new ResourceRetriever().getStyleSheets(this,myName));
		
	}
	
	private void addInstructions() {
		Label lab = makeLabel(getInstructions(), "instructions");
		lab.setStyle("-fx-wrap-text: true");
		myBP.setCenter(lab);
	}
	private void addBackground(){
		myBackground = mySplashEntity.getBackgroundFilePath();
		myBP.setBackground(makeBackground(myBackground));
	}
	
	private Background makeBackground(String bg){
		Image background = new Image(getClass().getClassLoader().getResourceAsStream(bg));
		BackgroundSize size = new BackgroundSize(0,0, true, true, true, true);
		BackgroundImage image = new BackgroundImage(background, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
		return new Background(image);
	}
}