package voogasalad.util.paint;

import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChangeColorEvent implements IToolbar {
	
	private DrawingTool drawer;
	
	private static final String TITLESTRING = "Choose Drawing Tool Color";

	public ChangeColorEvent(DrawingTool d) {
		drawer = d;
	}
	
	public void event() {
		Stage s = new Stage();
		Scene scene;
		BorderPane root = new BorderPane();
		ColorPicker t = new ColorPicker(Color.BLACK);
		root.getChildren().add(t);
		
		scene = new Scene(root);
		s.setScene(scene);
		s.setTitle(TITLESTRING);
		s.showAndWait();
		
		drawer.setColor(t.getValue());
	}
}
