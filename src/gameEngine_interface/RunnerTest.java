package gameEngine_interface;

import gameView.UIViewInterface;
import controller.WorldAnimator;
import gameView.UIView;
import gameView.userInput.IRestrictedUserInputData;
import gameView.userInput.IUserInputData;
import gameView.userInput.UserInputData;
import javafx.stage.Stage;

public class RunnerTest {
	
	private WorldAnimator myWorld;
	private GameEngine myEngine;
	private IUserInputData myInput;
	
	public RunnerTest(Stage s, UIView view){
		myWorld = new WorldAnimator(view);
		myInput = new UserInputData();
		myEngine = new GameEngine((IRestrictedUserInputData) myInput);
		//ge.dummyLoad();
		//myWorld.start(myEngine.dummyLoad());
	}
	
	public WorldAnimator getAnimator() {
		return myWorld;
	}
	public GameEngine getEngine() {
		return myEngine;
	}
	
	public IUserInputData getUserInput() {
		return myInput;
	}
}
