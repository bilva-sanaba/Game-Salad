package view.window;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public interface IWindow {
	Stage myStage = new Stage();
	VBox myRoot = new VBox();
	public void openWindow();
}
