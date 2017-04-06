
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.GUIBuilder;
import view.UtilityFactory;

public class Main extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		UtilityFactory utilF = new UtilityFactory("English");
		GUIBuilder gui = new GUIBuilder(utilF);
		Scene s = gui.buildScene();
		arg0.setScene(s);
		arg0.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}