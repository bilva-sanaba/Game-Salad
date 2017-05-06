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
	
	/**
	 * Sets the appropriate fields and opens the window
	 * 
	 * @param utilF Utility Factory to make all the JavaFX Nodes 
	 * @param myE THe Entity to which the actions are getting added
	 */
	//assume entity.getcomponent returned the component or made one and added it to the entity
	public EntityActionWindow(UtilityFactory utilF, Entity myE) {
		myUtilF = utilF;
		myEntity = myE;
		myStage.setScene(buildScene());
		myStage.show();
	}
	
	private Scene buildScene() {
		labelType = myUtilF.buildTextField("LabelAction");
		EntityTypeList = myUtilF.buildListView(EntityType.values(), "EntityType");
		HBox top = myUtilF.buildHBox(new Text("Choose at Least One: "), labelType, EntityTypeList, addDuration());
		myUtilF.setOn3x3Grid(myEntity.getImageView(), "Center", root);
		myUtilF.setOn3x3Grid(myUtilF.buildButton("MakeEntity", e -> myStage.close()), "Bottom Right", root); //
		buildActionMaker();
		Scene myScene = new Scene(myUtilF.buildVBox(top, new ScrollPane(root)), UtilityFactory.DEFAULT_STAGE_SIZE,
				UtilityFactory.DEFAULT_STAGE_SIZE);
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
		return myScene;
	}

	private Node addDuration() {
		VBox time = new VBox();
		final ToggleGroup group = myUtilF.buildRadioButtonGroup("TimeForAction", time);
		group.selectedToggleProperty().addListener((obs, oldval, newval) -> change(obs, oldval, newval));
		duration = myUtilF.buildSlider("Duration", (obs, oldval, newval) -> changeDuration(obs, oldval, newval));
		time.getChildren().add(duration);
		return time;
	}

	private void changeDuration(ObservableValue<? extends Number> obs, Number oldval, Number newval) {
		myDuration = Integer.valueOf(newval.intValue());
	}

	private void change(ObservableValue<? extends Toggle> obs, Toggle oldval, Toggle newval) {
		myDur = (String[]) newval.getUserData(); //String[] is set in the Utility Factory and is consistent
		tc = (TimeComponent) myEntity.getComponent(ComponentType.Time); 		
		addDuration = myDur[UtilityFactory.RADIO_INDEX].equalsIgnoreCase("true");
	}

	private void buildActionMaker() {
		ActionRetriever ar = new ActionRetriever();
		allActions = new HashMap<CollisionComponentType, List<String>>();
		for (int j = 0; j < CollisionComponentType.values().length; j++) {
			CollisionComponentType currentType = CollisionComponentType.values()[j];
			List<Class<?>> listofAct = ar.getActionsWithAnnotation(currentType);
			ArrayList<String> actions = new ArrayList<String>();
			populateString(actions, listofAct);
			allActions.put(currentType, actions);
			addListView(actions, currentType, j);
			}
	}

	private void addListView(List<String> actions, CollisionComponentType currentType, int j) {
		ListView<String> viewActs = myUtilF.buildListView(actions, "IActions");
		myUtilF.setImageToolTips(viewActs);
		VBox listandbut = myUtilF.buildVBox(new Text(currentType.name() + "Action"), viewActs,
				myUtilF.buildButton("AddActions", e -> addAction(currentType, viewActs)));
		myUtilF.setOn3x3Grid(listandbut, CollisionComponentType.values()[j].toString(), root);
	}

	private void populateString(List<String> actions, List<Class<?>> listofAct) {
		for (int i = 0; i < listofAct.size(); i++) {
			Class<?> nextAction = listofAct.get(i);
			String act = null;
			act = nextAction.toString();
			try {
				nametoAct.put(myUtilF.getText(act), act);
				actions.add(myUtilF.getText(act));
			} catch (Exception e) {
				// Action not found in the Action retriever not a real issue for
				// user or this class
			}
			allAct.put(act, nextAction);
		}
	}
	

	private void addAction(CollisionComponentType collisionComponentType, ListView<String> viewActs) {
		CollisionComponentsHandler sideCollisionActions = (CollisionComponentsHandler) myEntity.getComponent(ComponentType.CollisionHandler);
		SideCollisionComponent sidecollision = getSideCollsion(collisionComponentType, sideCollisionActions);
		IAction act = makeAction(viewActs);
		boolean validLabel = (!(labelType.getText().toString().equals(labelType.getPromptText().toString()) || labelType.getText().toString().equals("")));
		boolean validType = (EntityTypeList.getSelectionModel().getSelectedIndex() >= 0);
		BiConsumer<LabelComponent, IAction> bcLabel = (lc, ia) -> {
			sidecollision.addActionForLabel(lc, ia);
		};
		BiConsumer<TypeComponent, IAction> bcType = (tc, ia) -> {
			sidecollision.addActionForType(tc, ia);
		};
		checkAndAddAction(validLabel, bcLabel, new LabelComponent(labelType.getText()), act);
		checkAndAddAction(validType, bcType, new TypeComponent(EntityTypeList.getSelectionModel().getSelectedItem()), act);
	}
	
	@SuppressWarnings("rawtypes")
	private void checkAndAddAction(boolean valid, BiConsumer bc, IComponent ic, IAction ia){
		if(valid){
			checkDuration(ia, bc, ic);
		}
	}
	
	private IAction makeAction(ListView<String> viewActs) {
		IAction act = null;
		try {
			act = getAction(allAct.get(nametoAct.get(viewActs.getSelectionModel().getSelectedItem())));
		} catch (InputException x) {
			Alert invalid = new Alert(AlertType.ERROR, "Invalid Parameters");
			invalid.show();
		}
		return act;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void checkDuration(IAction act, BiConsumer bc, IComponent comp){
		if (addDuration) {
			tc.addAction(act, myDuration);
		} else {
			bc.accept(comp, act);
		}
	}
	
	private SideCollisionComponent getSideCollsion(CollisionComponentType collisionComponentType, CollisionComponentsHandler sideCollisionActions) {
		SideCollisionComponent sidecollision = null;
		if (sideCollisionActions.getCollisionComponent(collisionComponentType.toString()) == null) {
			sidecollision = new SideCollisionComponent(collisionComponentType);
			sideCollisionActions.addCollisionComponent(sidecollision);
		} else {
			sidecollision = sideCollisionActions.getCollisionComponent(collisionComponentType.toString());
		}
		return sidecollision;
	}

	private IAction getAction(Class<?> absAct) throws InputException {
		IAction act = null;
		try {
			act = (IAction) absAct.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			IActionMakerWindow actionMaker = new IActionMakerWindow(myUtilF, absAct);
			act = actionMaker.openWindow();
		}
		return act;
	}
}
