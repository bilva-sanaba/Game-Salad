// This entire file is part of my masterpiece.
// Jonathan Rub

package view.window;

import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.TimeComponent;
import entity.Entity;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.GUIBuilder;
import view.UtilityFactory;

/**
 * This Class works to make the frame work of the Entity Action Window It makes
 * the top part of the window where the user can input the label or pick the
 * type Component and the Duration
 * 
 * This class takes advantage of the Factory pattern by using the Utility
 * Factory to make all of the nodes used. All the method and variable names are
 * descriptive and and give insight to the purpose of the code.
 * 
 * All instance variables are private and can only be accessed through getters
 * which encapsulates the information very well.
 * 
 * Also the use of change listeners effectively updates the necessary instance
 * to keep them updated.
 * 
 * 
 * @author Jonathan
 *
 */
public class EntityActionStage {
	private UtilityFactory myUtilF;
	private Pane root;
	private Stage myStage;
	private TextField labelType;
	private ListView<EntityType> EntityTypeList;
	private Entity myEntity;
	private String[] myDur;
	private TimeComponent timeComponent = null;
	private HBox duration;
	private Integer myDuration;
	private boolean addDuration = false;

	private static final String LabelActionLabel = "LabelAction";
	private static final String EntityTypeLabel = "EntityType";
	private static final String ChooseLabel = "Choose at Least One: ";
	private static final String CenterLabel = "Center"; // Center is key for
														// utility factory
	private static final String BottomRightLabel = "Bottom Right"; // Bottom
																	// right is
																	// key for
																	// utility
																	// factory
	private static final String MakeEntityLabel = "MakeEntity";
	private static final String TimeForActionLabel = "TimeForAction";
	private static final String DurationLabel = "Duration";
	private static final String TRUE = "true";

	/**
	 * Adds the correct Nodes to the Stage
	 * 
	 * @param utilF
	 *            a factory to make JavaFX nodes
	 * @param entity
	 *            the entity to which the actions are getting added
	 * @param stage
	 *            stage of the entity action window
	 * @param rot
	 *            root of the entity action window
	 */
	// assume entity.getComponent returned the component or made one and added
	// it to the entity
	public EntityActionStage(UtilityFactory utilF, Entity entity, Stage stage, Pane rot) {
		myUtilF = utilF;
		myEntity = entity;
		myStage = stage;
		root = rot;
		myStage.setScene(buildScene());
	}

	public TextField getLabelType() {
		return labelType;
	}

	public ListView<EntityType> getEntityTypeList() {
		return EntityTypeList;
	}

	public TimeComponent getTimeComponent() {
		return timeComponent;
	}

	public Integer getMyDuration() {
		return myDuration;
	}

	public boolean isAddDuration() {
		return addDuration;
	}

	private Scene buildScene() {
		labelType = myUtilF.buildTextField(LabelActionLabel);
		EntityTypeList = myUtilF.buildListView(EntityType.values(), EntityTypeLabel);
		HBox top = myUtilF.buildHBox(new Text(ChooseLabel), labelType, EntityTypeList, addDuration());
		myUtilF.setOn3x3Grid(myEntity.getImageView(), CenterLabel, root);
		myUtilF.setOn3x3Grid(myUtilF.buildButton(MakeEntityLabel, e -> myStage.close()), BottomRightLabel, root); // Bottom
																													// right
																													// is
																													// key
																													// for
																													// utility
																													// factory
		Scene myScene = new Scene(myUtilF.buildVBox(top, new ScrollPane(root)), UtilityFactory.DEFAULT_STAGE_SIZE,
				UtilityFactory.DEFAULT_STAGE_SIZE);
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
		return myScene;
	}

	private Node addDuration() {
		VBox time = new VBox();
		final ToggleGroup group = myUtilF.buildRadioButtonGroup(TimeForActionLabel, time);
		group.selectedToggleProperty().addListener((obs, oldval, newval) -> change(obs, oldval, newval));
		duration = myUtilF.buildSlider(DurationLabel, (obs, oldval, newval) -> changeDuration(obs, oldval, newval));
		time.getChildren().add(duration);
		return time;
	}

	private void changeDuration(ObservableValue<? extends Number> obs, Number oldval, Number newval) {
		myDuration = Integer.valueOf(newval.intValue());
	}

	private void change(ObservableValue<? extends Toggle> obs, Toggle oldval, Toggle newval) {
		myDur = (String[]) newval.getUserData(); // String[] is set in the
													// sUtility Factory and is
													// consistent
		timeComponent = (TimeComponent) myEntity.getComponent(ComponentType.Time);
		addDuration = myDur[UtilityFactory.RADIO_INDEX].equalsIgnoreCase(TRUE);
	}

}