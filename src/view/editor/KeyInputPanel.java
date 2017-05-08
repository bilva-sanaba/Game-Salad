package view.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actions.IAction;
import actions.ShootAction;
import components.entityComponents.ConcreteKeyExpressions;
import components.entityComponents.IKeyExpression;
import components.entityComponents.KeyExpression;
import components.keyExpressions.InfiniteJump;
import components.keyExpressions.JumpAction;
import components.keyExpressions.LeftAction;
import components.keyExpressions.RightAction;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.GUIBuilder;

public class KeyInputPanel implements IKeyInputPanel{

	private Map<KeyCode, ArrayList<IAction>> keyMap = new HashMap<KeyCode, ArrayList<IAction>>();
	private KeyCode current; 
	private IAction currentKC;

	private String currentString;
	private Text t = new Text();
	private Scene myScene;
	private List<IAction> x;
	private Map<String, String> c = new HashMap<String,String>();
	public KeyInputPanel(){
		x = new ArrayList<IAction>();
		x.add(new ShootAction());
		x.add(new JumpAction());
		x.add(new RightAction());
		x.add(new LeftAction());
		x.add(new InfiniteJump());
	}


	public Map<KeyCode, ArrayList<IAction>> getMap() {
		// TODO Auto-generated method stub
		return keyMap;
	}
	public void openWindow(){
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		Pane root = new VBox(10);
		root.setPadding(new Insets(10));
		Button okayButton = new Button("Close");
		okayButton.setOnAction(e -> {
			//TODO: pass the color to a new entity or something?
			stage.close();
		});
		Button add = new Button("Add");
		add.setOnAction(e -> {
			if (current!=null && currentKC!=null){
				if (keyMap.containsKey(current)){
					keyMap.get(current).add(currentKC);
				}else{
					ArrayList<IAction> myActions = new ArrayList<IAction>();
					myActions.add(currentKC);
					keyMap.put(current, myActions);
				}
			}
		});
			
		root.getChildren().addAll(t,createKeyInput(),add,okayButton);
		myScene = new Scene(root, 230, 400);
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
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
		for (IAction y : x){
			myComboBox.getItems().add(y.getClass().getName());
		}
		myComboBox.valueProperty().addListener((x, y, newValue) -> {
	try {
		currentKC = (IAction) Class.forName(newValue).newInstance();
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
});  
		myComboBox.setPromptText("Pick an Action");
		return myComboBox;
	}
}