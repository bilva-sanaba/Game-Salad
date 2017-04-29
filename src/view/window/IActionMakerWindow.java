package view.window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import actions.IAction;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.ActionFactory;
import view.GUIBuilder;
import view.UtilityFactory;

public class IActionMakerWindow extends Window {
	private UtilityFactory myUtilF;
	private VBox root;
	private Stage myStage = new Stage();
	private Class<?> myAction;
	private ActionFactory actFac;

	public IActionMakerWindow(UtilityFactory utilF, Class<?> act) {
		myUtilF = utilF;
		myAction = act;
		myStage.setScene(buildScene());
	}

	private Scene buildScene() {
		root = new VBox();
		buildIActionMaker();
		Scene myScene = new Scene(root, 200, 200);
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
		return myScene;
	}

	private void buildIActionMaker() {
		
	}
		
	private void changeParam(TextField text, Integer i) {
		try{
			
		}catch(NullPointerException x){
			
		}
	}
	
	public String toString(){
		return myAction.toString();
	}

	@Override
	public void openWindow() {
		myStage.show();
	}

}
