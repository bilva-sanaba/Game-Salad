package view.window;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import actions.IAction;
import exceptions.InputException;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.GUIBuilder;
import view.Input;
import view.UtilityFactory;

public class IActionMakerWindow{
	private UtilityFactory myUtilF;
	private VBox root;
	private Stage myStage = new Stage();
	private Class<?> myAction;
	private List<String> myParams;
	private int listConstructNum = 0;

	public IActionMakerWindow(UtilityFactory utilF, Class<?> absAct) {
		myUtilF = utilF;
		myAction = absAct;
		myParams = new ArrayList<String>();
		myStage.setScene(buildScene());
	}

	private Scene buildScene() {
		root = new VBox();
		buildIActionMaker();
		Scene myScene = new Scene(root, 200, 200);
		root.getChildren().add(myUtilF.buildButton("EnterInput", e -> closeAndSend()));
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
		return myScene;
	}


	private void closeAndSend() {
		myStage.close();
	}
	
	private void buildIActionMaker() {
		Constructor<?>[] actConst = myAction.getConstructors();
		for(int i = 0; i < actConst.length; i++){
			for (int j = 0; j < actConst[i].getParameterTypes().length; j++){
				if(actConst[i].getParameterTypes()[j].toString().equals("interface java.util.List")){
					listConstructNum = j + 1;
					break;
				}
			}
		}
		Input getInput = new Input(myUtilF, myAction.toString().replace(" ", ""));
		myParams  = getInput.getInput();		
	}
		
	public String toString(){
		return myAction.toString();
	}

	public IAction openWindow() throws InputException {
		myStage.showAndWait();
		try {
	
			return (IAction) myAction.getConstructors()[listConstructNum].newInstance(myParams);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			
		}
		return null;
	}

}
