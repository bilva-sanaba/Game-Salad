package engines;

import java.util.HashMap;
import java.util.Map;

import components.KeyExpression;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class KeyInputPanel implements IKeyInputPanel{
	private Map<KeyCode, KeyExpression> keyMap = new HashMap<KeyCode, KeyExpression>();
	private KeyCode current; 
	private KeyExpression currentKeyExpression;
	public KeyInputPanel(){
		
	}

	@Override
	public Map<KeyCode, KeyExpression> getMap() {
		// TODO Auto-generated method stub
		return keyMap;
	}
	public void openWindow(){
		Stage stage = new Stage();
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		Button okayButton = new Button("Close");
		okayButton.setOnAction(e -> {
			//TODO: pass the color to a new entity or something?
			stage.close();
		});
		Button add = new Button("Add");
		okayButton.setOnAction(e -> {
			if (current!=null){
				keyMap.put(current,currentKeyExpression);
			}
		});
		Node n = Pane()
		root.setBottom(arg0);
		root.getChildren().add(okayButton);
		Scene scene = new Scene(root, 230, 400);
		stage.setScene(scene);
		stage.show();
	}
}
