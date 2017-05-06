// This entire file is part of my masterpiece.
// Jonathan Rub

package view.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import actions.ActionRetriever;
import actions.IAction;
import components.IComponent;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.CollisionComponentsHandler;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.LabelComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.TimeComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;
import exceptions.InputException;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.GUIBuilder;
import view.UtilityFactory;

/**
 * This is a UI where the Users can add Actions to an Entity that they create
 * 
 * @author Jonathan
 *
 */
public class EntityActionWindow {
	private UtilityFactory myUtilF;
	private GridPane root = new GridPane();
	private Stage myStage = new Stage();
	private Entity myEntity;
	private TextField labelType;
	private ListView<EntityType> EntityTypeList;
	private Map<CollisionComponentType, List<String>> allActions;
	private Map<String, Class<?>> allAct = new HashMap<String, Class<?>>();
	private String[] myDur;
	private TimeComponent tc = null;
	private HBox duration;
	private Integer myDuration;
	private boolean addDuration = false;
	private Map<String, String> nametoAct = new HashMap<String, String>();

	
	private static final String LabelActionLabel = "LabelAction";
	private static final String EntityTypeLabel = "EntityType";
	private static final String ChooseLabel = "Choose at Least One: ";
	private static final String CenterLabel = "Center"; //Center is key for utility factory
	private static final String BottomRightLabel = "Bottom Right"; //Bottom right is key for utility factory
	private static final String MakeEntityLabel = "MakeEntity";
	private static final String TimeForActionLabel = "TimeForAction";
	private static final String DurationLabel = "Duration";
	private static final String IActionsLabel = "IActions";
	private static final String ActionLabel = "Action";
	private static final String AddActionsLabel = "AddActions";
	private static final String InvalidParametersAlert = "Invalid Parameters";
	private static final String EMPTY = "";
	private static final String TRUE = "true";
	

	/**
	 * Sets the appropriate fields and opens the window
	 * 
	 * @param utilF
	 *            Utility Factory to make all the JavaFX Nodes
	 * @param myE
	 *            THe Entity to which the actions are getting added
	 */
	// assume entity.getComponent returned the component or made one and added
	// it to the entity
	public EntityActionWindow(UtilityFactory utilF, Entity myE) {
		myUtilF = utilF;
		myEntity = myE;
		myStage.setScene(buildScene());
		myStage.show();
	}

	private Scene buildScene() {
		labelType = myUtilF.buildTextField(LabelActionLabel);
		EntityTypeList = myUtilF.buildListView(EntityType.values(), EntityTypeLabel);
		HBox top = myUtilF.buildHBox(new Text(ChooseLabel), labelType, EntityTypeList, addDuration());
		myUtilF.setOn3x3Grid(myEntity.getImageView(), CenterLabel, root);
		myUtilF.setOn3x3Grid(myUtilF.buildButton(MakeEntityLabel, e -> myStage.close()), BottomRightLabel, root); //Bottom right is key for utility factory
		buildActionMaker();
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
		myDur = (String[]) newval.getUserData(); // String[] is set in the sUtility Factory and is consistent
		tc = (TimeComponent) myEntity.getComponent(ComponentType.Time);
		addDuration = myDur[UtilityFactory.RADIO_INDEX].equalsIgnoreCase(TRUE);
	}

	private void buildActionMaker() {
		ActionRetriever ar = new ActionRetriever();
		allActions = new HashMap<CollisionComponentType, List<String>>();
		for (int j = 0; j < CollisionComponentType.values().length; j++) {
			CollisionComponentType currentType = CollisionComponentType.values()[j];
			List<Class<?>> listofActions = ar.getActionsWithAnnotation(currentType);
			ArrayList<String> actions = new ArrayList<String>();
			populateString(actions, listofActions);
			allActions.put(currentType, actions);
			addListView(actions, currentType, j);
		}
	}

	private void addListView(List<String> actions, CollisionComponentType currentType, Integer index) {
		ListView<String> viewActions = myUtilF.buildListView(actions, IActionsLabel);
		myUtilF.setImageToolTips(viewActions);
		VBox listandbutton = myUtilF.buildVBox(new Text(currentType.name() + ActionLabel), viewActions,
				myUtilF.buildButton(AddActionsLabel, e -> addAction(currentType, viewActions)));
		myUtilF.setOn3x3Grid(listandbutton, CollisionComponentType.values()[index].toString(), root);
	}

	private void populateString(List<String> actions, List<Class<?>> listofAct) {
		for (int i = 0; i < listofAct.size(); i++) {
			Class<?> nextAction = listofAct.get(i);
			String actionName = nextAction.toString();
			try {
				nametoAct.put(myUtilF.getText(actionName), actionName);
				actions.add(myUtilF.getText(actionName));
			} catch (Exception e) {
				// Action not found in the Action retriever not a real issue for
				// user or this class
			}
			allAct.put(actionName, nextAction);
		}
	}

	private void addAction(CollisionComponentType collisionComponentType, ListView<String> viewActions) {
		CollisionComponentsHandler sideCollisionActions = (CollisionComponentsHandler) myEntity
				.getComponent(ComponentType.CollisionHandler);
		SideCollisionComponent sidecollision = getSideCollsion(collisionComponentType, sideCollisionActions);
		IAction action = makeAction(viewActions);
		boolean validLabel = (!(labelType.getText().toString().equals(labelType.getPromptText().toString())
				|| labelType.getText().toString().equals(EMPTY)));
		boolean validType = (EntityTypeList.getSelectionModel().getSelectedIndex() >= 0);
		BiConsumer<LabelComponent, IAction> functiontoAddLabel = (labelComponent, iAction) -> {
			sidecollision.addActionForLabel(labelComponent, iAction);
		};
		BiConsumer<TypeComponent, IAction> functionToAddType = (typeComponet, iAction) -> {
			sidecollision.addActionForType(typeComponet, iAction);
		};
		checkAndAddAction(validLabel, functiontoAddLabel, new LabelComponent(labelType.getText()), action);
		checkAndAddAction(validType, functionToAddType, new TypeComponent(EntityTypeList.getSelectionModel().getSelectedItem()),
				action);
	}

	@SuppressWarnings("rawtypes")
	private void checkAndAddAction(boolean valid, BiConsumer addActionFunction, IComponent iComponent, IAction iAction) {
		if (valid) {
			checkDuration(iAction, addActionFunction, iComponent);
		}
	}

	private IAction makeAction(ListView<String> viewActions) {
		IAction action = null;
		try {
			action = getAction(allAct.get(nametoAct.get(viewActions.getSelectionModel().getSelectedItem())));
		} catch (InputException x) {
			Alert invalid = new Alert(AlertType.ERROR, InvalidParametersAlert);
			invalid.show();
		}
		return action;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void checkDuration(IAction action, BiConsumer addActionLambda, IComponent component) {
		if (addDuration) {
			tc.addAction(action, myDuration);
		} else {
			addActionLambda.accept(component, action);
		}
	}

	private SideCollisionComponent getSideCollsion(CollisionComponentType collisionComponentType,
			CollisionComponentsHandler sideCollisionActions) {
		SideCollisionComponent sidecollision = null;
		if (sideCollisionActions.getCollisionComponent(collisionComponentType.toString()) == null) {
			sidecollision = new SideCollisionComponent(collisionComponentType);
			sideCollisionActions.addCollisionComponent(sidecollision);
		} else {
			sidecollision = sideCollisionActions.getCollisionComponent(collisionComponentType.toString());
		}
		return sidecollision;
	}

	private IAction getAction(Class<?> abstractAction) throws InputException {
		IAction action = null;
		try {
			action = (IAction) abstractAction.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			IActionMakerWindow actionMaker = new IActionMakerWindow(myUtilF, abstractAction);
			action = actionMaker.openWindow();
		}
		return action;
	}

}
