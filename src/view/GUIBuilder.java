package view;

import java.util.Collection;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import view.toolbar.ToolBarView;

/**
 * @author Jonathan Rub
 * @author Justin Yang
 * @author Jack Bloomfeld
 */
public class GUIBuilder {
	public static final String RESOURCE_PACKAGE = "resources/";
	public static final String STYLESHEET = "GridView.css";

	private static final double SCREEN_HEIGHT = 650;
	private static final double SCREEN_WIDTH = 1000;

	private Collection<GUIComponent> myComp = new ArrayList<GUIComponent>();
	private GridView grid;
	private TabView tab;
	private GUIComponent toolbar;
	private ViewData myData;
	private ViewController viewController;

	/**
	 * Initializes the main Scene and Stage.
	 */
	public GUIBuilder(UtilityFactory utilF) {
		myData = new ViewData();
		grid = new GridView(utilF, myData, 10, 10);
		tab = new TabView(utilF, myData);
		toolbar = new ToolBarView(utilF, myData);
		viewController = new ViewController(myData, grid, tab);

		myComp.add(grid);
		myComp.add(tab);
		myComp.add(toolbar);
	}

	public Pane buildPane() {
		BorderPane myPane = new BorderPane();
		myPane.setPrefHeight(SCREEN_HEIGHT);
		myPane.setPrefWidth(SCREEN_WIDTH);

		myPane.setTop(toolbar.buildComponent());
		myPane.setRight(tab.buildComponent());
		myPane.setCenter(grid.buildComponent());
		return myPane;
	}

	public Scene buildScene() {
		Scene myScene = new Scene(buildPane());
		myScene.getStylesheets().add(RESOURCE_PACKAGE + STYLESHEET);
		return myScene;
	}
}
