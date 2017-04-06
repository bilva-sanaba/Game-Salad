
import javafx.application.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.GUIBuilder;

public class Main extends Application {

	public void start(Stage arg0) throws Exception {
		GUIBuilder gui = new GUIBuilder(null);
		Scene s = gui.buildScene();
		arg0.setScene(s);
		arg0.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}