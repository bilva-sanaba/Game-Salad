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
import components.entityComponents.TypeComponent;
import entity.Entity;
import exceptions.InputException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.UtilityFactory;

/**
 * This is a UI where the Users can add Actions to an Entity that they create
 * 
 * I split up the original class into two parts a class that makes the stage and
 * set up miscellaneous FrontEnd of the window and the logic behind actually
 * getting and making the IActions
 * 
 * This class holds the logic behind actually populating the different ListViews
 * for each side of the Component. It also trys to instantiate the Action or
 * gets the necessary information from an Input.
 * 
 * All the method and variable names are descriptive and and give insight to the
 * purpose of the code.
 * 
 * All instance variables are private and can only be accessed through getters
 * which encapsulates the information very well. Also the Label and EnityType 
 * are only available through the EntityActionStage class the information is 
 * encapsulated and cannot be changed by this class.
 * 
 * Also the use of Lambdas is a very effective way to reduce duplicated code.
 * 
 * @author Jonathan Rub
 *
 */
public class EntityActionWindow implements Window {
	private UtilityFactory myUtilF;
	private GridPane root = new GridPane();
	private Stage myStage = new Stage();
	private Entity myEntity;
	private Map<CollisionComponentType, List<String>> allActions;
	private Map<String, Class<?>> allAct = new HashMap<String, Class<?>>();
	private Map<String, String> nametoAct = new HashMap<String, String>();
	private EntityActionStage myActionStage;
	private static final String IActionsLabel = "IActions";
	private static final String ActionLabel = "Action";
	private static final String AddActionsLabel = "AddActions";
	private static final String InvalidParametersAlert = "Invalid Parameters";
	private static final String EMPTY = "";

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
		myActionStage = new EntityActionStage(myUtilF, myEntity, myStage, root);
		buildActionMaker();
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
		TextField labelType = myActionStage.getLabelType();
		ListView<EntityType> EntityTypeList = myActionStage.getEntityTypeList();
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
		checkAndAddAction(validType, functionToAddType,
				new TypeComponent(EntityTypeList.getSelectionModel().getSelectedItem()), action);
	}

	@SuppressWarnings("rawtypes")
	private void checkAndAddAction(boolean valid, BiConsumer addActionFunction, IComponent iComponent,
			IAction iAction) {
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
		if (myActionStage.isAddDuration()) {
			myActionStage.getTimeComponent().addAction(action, myActionStage.getMyDuration());
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

	/**
	 * opens the window
	 */
	@Override
	public void openWindow() {
		myStage.show();
	}

}
