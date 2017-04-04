package view;

import java.util.*;

import author_interfaces.GUIComponent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * 
 * @author Jonathan
 *
 */
public class GUIBuilder {
	private static final double SCREEN_HEIGHT = 650;
	private static final double SCREEN_WIDTH = 1000;
	private static final double TURTLE_VIEW_HEIGHT_FACTOR = .6;
	private static final double TURTLE_VIEW_WIDTH_FACTOR = .8;
	private static final double TAB_VIEW_HEIGHT_FACTOR = .8;
	private static final double TAB_VIEW_WIDTH_FACTOR = .2;
	private static final double TITLE_PANE_HEIGHT_FACTOR = .2;
	private static final double TITLE_PANE_WIDTH_FACTOR = .375;
	private static final double COMMAND_LINE_HEIGHT_FACTOR = .2;
	private static final String DEFAULT_RESOUCES = "resources/screentext/English";
	private static final String DEFAULT_CSS = "resources/css/slogo.css";
	
	/**
	 * Resource bundle for all text displayed on screen.
	 */
	public static final ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOUCES);

	private Collection<GUIComponent> myComp = new Collection<GUIcomponent>;

	
	/**
	 * Initializes the main Scene and Stage.
	 */
	public GUIBuilder GUIBuilder(){
		GridPane root = new GridPane();
		GUIcomponent grid = new GridView();
		GUIcomponent tab = new TabView();
		GUIcomponent menu = new MenuView();
		GUIcomponent toolbar = new ToolBarView();
		myComp.addAll(grid, tab, menu, toolbar);
	}
	
	public Pane buildPane() {
		Pane myPane = new GridPane();
		myComp.stream().map(myPane::buildComponent);
		return myPane;
	}

	public Scene buildScene() {
		Scene myScene = new Scene(buildPane());
	}
}
