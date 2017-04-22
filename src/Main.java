import gameView.loginScreen.LoginScreen;
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
<<<<<<< HEAD
import utility.DrawingCanvas;
=======
>>>>>>> master

public class Main extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
<<<<<<< HEAD
//		LoginScreen l = new LoginScreen(null);
//		arg0.setScene(l.getScene());
//		arg0.show();
=======
		System.out.println("maybe");
>>>>>>> master
		new Controller(arg0);
		//TODO: Uncomment the line below and comment the line above to enter test mode for worldanimation and gameengine
		//new RunnerTest(arg0);  
		  
		//new DrawingCanvas(arg0);  
		//new PaintWindow();
	}

	public static void main(String[] args) {
		launch(args);
	}
	

}  