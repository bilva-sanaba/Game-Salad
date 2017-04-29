package view.window;

import actions.IAction;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.GUIBuilder;
import view.UtilityFactory;

public class IActionMakerWindow extends Window {
	private UtilityFactory myUtilF;
	private VBox root;
	private Stage myStage = new Stage();
	private IAction myAction;

	public IActionMakerWindow(UtilityFactory utilF, IAction act) {
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
		// TODO Auto-generated method stub

	}
	
	public String toString(){
		return myAction.toString();
	}

	@Override
	public void openWindow() {
		myStage.show();
	}

}
