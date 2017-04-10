import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		//new Controller(arg0);
		Controller c = new Controller(arg0);
		c.loadNewGame("");
		c.makeGame();
		c.run();
	}

	public static void main(String[] args) {
		launch(args);
	}

}