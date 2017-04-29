package gameEngine_interface;

import gameView.UIViewInterface;
import controller.WorldAnimator;
import gameView.UIView;
import javafx.stage.Stage;

public class RunnerTest {
	
	private WorldAnimator myWorld;
	private GameEngine myEngine;
	
	public RunnerTest(Stage s, UIView view){
		myWorld = new WorldAnimator(view);
		myEngine = new GameEngine();
		//ge.dummyLoad();
		//myWorld.start(myEngine.dummyLoad());
	}
	
	public WorldAnimator getAnimator() {
		System.out.println(myWorld);
		return myWorld;
	}
	public GameEngine getEngine() {
		return myEngine;
	}
	
}
