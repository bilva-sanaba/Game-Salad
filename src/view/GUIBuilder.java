package view;

import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * @author Jonathan Rub
 * @author Justin Yang
 * @author Jack Bloomfeld
 * 
 */
public class GUIBuilder {
	public static final String RESOURCE_PACKAGE = "resources/";
	public static final String STYLESHEET = "AuthoringGUI.css";

	private static final double SCREEN_HEIGHT = 700;
	private static final double SCREEN_WIDTH = 1000;
	public static final int INITIAL_GRID_ROWS = 50;
	public static final int INITIAL_GRID_COLS = 70;

	private Collection<GUIComponent> myComp = new ArrayList<GUIComponent>();
	private LevelTabView levelTabs;
	private TabView tab;
	private ToolBarView toolbar;
	private ViewData myData;
	private ViewController viewController;
	private Pane myBP;
	private UtilityFactory utilF;

	/**
	 * Initializes the main Scene and Stage.
	 */
	public GUIBuilder(UtilityFactory utilIn) {
		utilF = utilIn;
		myData = new ViewData(INITIAL_GRID_ROWS, INITIAL_GRID_COLS);
		GridView grid1 = new GridView(utilF, 1, myData, INITIAL_GRID_ROWS, INITIAL_GRID_COLS);
		tab = new TabView(utilF, myData);

		toolbar = new ToolBarView(utilF, myData);

		levelTabs = new LevelTabView(grid1, myData);

		viewController = new ViewController(myData, levelTabs, tab, utilF);
		myData.addObserver(viewController);

		myComp.add(levelTabs);
		myComp.add(tab);
		myComp.add(toolbar);

		myBP = buildPane();
		tab.addPresetEntities();
	}

	public Pane buildPane() {
		BorderPane myPane = new BorderPane();
		myPane.setPrefHeight(SCREEN_HEIGHT);
		myPane.setPrefWidth(SCREEN_WIDTH);

		myPane.setTop(toolbar.buildComponent());
		myPane.setRight(tab.buildComponent());
		myPane.setCenter(levelTabs.buildComponent());
		HBox buttons = new HBox(10);
		buttons.setPadding(new Insets(10));
		Button newLevel = utilF.buildButton("newLevelLabel", e-> {
			myData.addLevel();
			levelTabs.addNewTab(new GridView(utilF, myData.getMaxLevel(), myData, INITIAL_GRID_ROWS, INITIAL_GRID_COLS));
		});
		Button deleteLevel = utilF.buildButton("deleteLevelLabel", e -> {
			if(myData.getCurrentLevel() == 1){
				//TODO: Error
			}
			else{
				myData.removeLevel();
				levelTabs.removeTab(myData.getCurrentLevel());
			}
		});
		Button shiftLevelLeft = utilF.buildButton("ShiftLeftLabel", e ->{
			
		});
		Button shiftLevelRight = utilF.buildButton("ShiftRightLabel", e ->{
			
		});
		buttons.getChildren().addAll(newLevel, deleteLevel, shiftLevelLeft, shiftLevelRight);
		myPane.setBottom(buttons);
		myPane.setId("root");
		return myPane;
	}

	public Scene buildScene() {
		Scene myScene = new Scene(myBP);
		myScene.getStylesheets().add(RESOURCE_PACKAGE + STYLESHEET);
		return myScene;
	}
}
