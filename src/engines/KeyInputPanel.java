package engines;

import java.util.HashMap;
import java.util.Map;

import components.keyExpressions.ConcreteKeyExpressions;
import components.entityComponents.KeyExpression;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class KeyInputPanel implements IKeyInputPanel{
	private Map<KeyCode, KeyExpression> keyMap = new HashMap<KeyCode, KeyExpression>();
	private KeyCode current; 
	private KeyExpression currentKC;
	private String currentString;
	private Text t = new Text();
	private Scene myScene;
	private ConcreteKeyExpressions[] x = ConcreteKeyExpressions.values();
	private Map<String, String> c = new HashMap<String,String>();
	public KeyInputPanel(){
		
	}

	public Map<KeyCode, KeyExpression> getMap() {
		// TODO Auto-generated method stub
		return keyMap;
	}
	public void openWindow(){
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		Button okayButton = new Button("Close");
		okayButton.setOnAction(e -> {
			//TODO: pass the color to a new entity or something?
			System.out.println("close");
			stage.close();
		});
		Button add = new Button("Add");
		add.setOnAction(e -> {
			if (current!=null){
				keyMap.put(current,currentKC);
			}
		});
		Pane n = new Pane();
		root.setTop(add);
		root.setBottom(n);
		root.setLeft(t);
		n.getChildren().add(okayButton);
		root.setCenter(createKeyInput());
		myScene = new Scene(root, 230, 400);
		createKeyHandle();
		stage.setScene(myScene);
		stage.show();
	}
	private void createKeyHandle(){
		myScene.setOnKeyPressed(e->t.setText(e.getCode().toString()));
	}
	private ComboBox<String> createKeyInput(){
		ComboBox<String> myComboBox = new ComboBox<String>();
		for (ConcreteKeyExpressions y : x){
			myComboBox.getItems().add(y.toString());
		}
		myComboBox.valueProperty().addListener((x, y, newValue) -> currentKC = ConcreteKeyExpressions.valueOf(newValue).getKeyExpression());  
		myComboBox.setPromptText("Fuck");
		return myComboBox;
	}
}