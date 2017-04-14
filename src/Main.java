import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import utility.DrawingCanvas;

public class Main extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		//new Controller(arg0);
		//TODO: Uncomment the line below and comment the line above to enter test mode for worldanimation and gameengine
		new RunnerTest(arg0);
		//new DrawingCanvas(arg0);
	}

	public static void main(String[] args) {
		launch(args);
	}

}