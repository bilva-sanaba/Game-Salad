package view;

import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * @author Jonathan Rub
 * @author Justin Yang
 * @author Jack Bloomfeld
 */
public class GUIBuilder {
	public static final String RESOURCE_PACKAGE = "resources/";
	public static final String STYLESHEET = "AuthoringGUI.css";

	private static final double SCREEN_HEIGHT = 650;
	private static final double SCREEN_WIDTH = 1000;
	private static final int INITIAL_GRID_ROWS = 50;
	private static final int INITIAL_GRID_COLS = 50;

	private Collection<GUIComponent> myComp = new ArrayList<GUIComponent>();
	private HashMap<Integer, GridView> myGrids;
	private TabView tab;
	private ToolBarView toolbar;
	private ViewData myData;
	private ViewController viewController;
	private Pane myBP;

	/**
	 * Initializes the main Scene and Stage.
	 */
	public GUIBuilder(UtilityFactory utilF) {
		myData = new ViewData(INITIAL_GRID_ROWS, INITIAL_GRID_COLS);
		myGrids = new HashMap<Integer, GridView>();
		myGrids.put(1, new GridView(utilF, 1, myData, INITIAL_GRID_ROWS, INITIAL_GRID_COLS));
		tab = new TabView(utilF, myData);
		toolbar = new ToolBarView(utilF, myData);
		viewController = new ViewController(myData, myGrids, tab);
		myData.addObserver(viewController);
		myData.addPresetEntities();

		myComp.add(myGrids.get(1));
		myComp.add(tab);
		myComp.add(toolbar);
		
		myBP = buildPane();
	}

	public Pane buildPane() {
		BorderPane myPane = new BorderPane();
		myPane.setPrefHeight(SCREEN_HEIGHT);
		myPane.setPrefWidth(SCREEN_WIDTH);

		myPane.setTop(toolbar.buildComponent());
		myPane.setRight(tab.buildComponent());
		myPane.setCenter(myGrids.get(1).buildComponent());
		myPane.setId("root");
		return myPane;
	}

	public Scene buildScene() {
		Scene myScene = new Scene(myBP);
		myScene.getStylesheets().add(RESOURCE_PACKAGE + STYLESHEET);
		return myScene;
	}
}
