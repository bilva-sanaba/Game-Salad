import controller.WorldAnimator;
import gameEngine_interface.GameEngine;
import javafx.stage.Stage;

public class RunnerTest {
	public RunnerTest(Stage arg){
		WorldAnimator w = new WorldAnimator();
		GameEngine g = new GameEngine();
		g.dummyLoad();
		w.start(g);
		arg.setScene(w.getScene());
		arg.show();
	}
}
