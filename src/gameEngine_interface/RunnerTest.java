package gameEngine_interface;

import controller.WorldAnimator;
import javafx.stage.Stage;

public class RunnerTest {
	public RunnerTest(Stage s){
		WorldAnimator wa = new WorldAnimator();
		GameEngine ge = new GameEngine();
		ge.dummyLoad();
		wa.start(ge);
	}
}
