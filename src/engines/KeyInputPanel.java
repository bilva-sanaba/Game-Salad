package engines;

import java.util.HashMap;
import java.util.Map;

import components.keyExpressions.ConcreteKeyExpressions;
import components.keyExpressions.KeyExpression;
import components.entityComponents.IKeyExpression;
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
	private Map<KeyCode, IKeyExpression> keyMap = new HashMap<KeyCode, IKeyExpression>();
	private KeyCode current; 
	private IKeyExpression currentKC;
	private String currentString;
	private Text t = new Text();
	private Scene myScene;
	private ConcreteKeyExpressions[] x = ConcreteKeyExpressions.values();
	private Map<String, String> c = new HashMap<String,String>();
	public KeyInputPanel(){
		
	}

	public Map<KeyCode, IKeyExpression> getMap() {
		// TODO Auto-generated method stub
		System.out.println(keyMap);
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
			if (current!=null && currentKC!=null){
				System.out.println(current);
				System.out.println(currentKC.getClass());
				keyMap.put(current,currentKC);
			}
			System.out.println(keyMap);
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
		stage.showAndWait();
	}
	private void createKeyHandle(){
		myScene.setOnKeyPressed(e->keypress(e.getCode()));
	}
	private void keypress(KeyCode keyCode) {
		current = keyCode;
		t.setText(keyCode.toString());
	}

	private ComboBox<String> createKeyInput(){
		ComboBox<String> myComboBox = new ComboBox<String>();
		for (ConcreteKeyExpressions y : x){
			myComboBox.getItems().add(y.toString());
		}
		currentKC = new KeyExpression();
		myComboBox.valueProperty().addListener((x, y, newValue) -> currentKC = ConcreteKeyExpressions.valueOf(newValue).getKeyExpression());  
		myComboBox.setPromptText("Pick an Action");
		return myComboBox;
	}
}