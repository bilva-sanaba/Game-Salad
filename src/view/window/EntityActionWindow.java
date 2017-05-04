package view.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actions.ActionRetriever;
import actions.IAction;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
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
import view.ViewData;

public class EntityActionWindow implements Window {
	private UtilityFactory myUtilF;
	private GridPane root;
	private Stage myStage = new Stage();
	private Entity myEntity;
	private TextField labelType;
	private ListView<EntityType> EntityTypeList;
	private Map<CollisionComponentType, List<String>> allActions;
	private Map<String, Class<?>> allAct = new  HashMap<String, Class<?>>();
	private String[] myDur;
	private TimeComponent tc = null;
	private HBox duration;
	private Integer myDuration;
	private boolean addDuration = false;
	private Map<String, String> nametoAct = new HashMap<String, String>();


	public EntityActionWindow(UtilityFactory utilF, ViewData entityData, Entity myE) {
		myUtilF = utilF;
		myEntity = myE;
		myStage.setScene(buildScene());
		openWindow();
	}

	private Scene buildScene() {
		root = new GridPane();
		labelType = myUtilF.buildTextField("LabelAction");
		EntityTypeList = myUtilF.buildListView(EntityType.values());
		EntityTypeList.setMinSize(200, 100);
		EntityTypeList.setMaxSize(200, 100);
		HBox top = myUtilF.buildHBox(new Text("Choose at Least One: "), labelType, EntityTypeList, addDuration());
		setOnGrid(myEntity.getImageView(), 1, 1);
		setOnGrid(myUtilF.buildButton("MakeEntity", e -> makeEntity()), 2, 2);
		buildActionMaker();
		Scene myScene = new Scene(myUtilF.buildVBox(top, new ScrollPane(root)), 600, 600);
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
		return myScene;
	}

	private Node addDuration() {
		VBox time = new VBox();
		final ToggleGroup group = myUtilF.buildRadioButtonGroup("TimeForAction", time);
		group.selectedToggleProperty().addListener((obs, oldval, newval) -> change(obs, oldval, newval));
		duration = myUtilF.buildSlider("Duration",  (obs, oldval, newval) -> changeDuration(obs, oldval, newval));
		time.getChildren().add(duration);
		return time;
	}

	private void changeDuration(ObservableValue<? extends Number> obs, Number oldval, Number newval) {
		myDuration = Integer.valueOf(newval.intValue()); 
	}

	private void change(ObservableValue<? extends Toggle> obs, Toggle oldval, Toggle newval) {
		myDur = (String[]) newval.getUserData();
		if (myDur[0].equalsIgnoreCase("true")){ // boolean if true
			if (myEntity.hasComponent(ComponentType.Type)){
				tc = (TimeComponent) myEntity.getComponent(ComponentType.Time);
			}else{
				tc = new TimeComponent();
				myEntity.addComponent(tc);
			}
			addDuration  = true;
		}else{
			addDuration = false;
		}
	}

	private void makeEntity() {
		myStage.close();
	}

	private void setOnGrid(Node nod, int i, int j) {
		GridPane.setConstraints(nod, i, j);
		GridPane.setHalignment(nod, HPos.CENTER);
		root.getChildren().add(nod);
	}

	private void buildActionMaker() {
		ActionRetriever ar = new ActionRetriever();
		allActions = new HashMap<CollisionComponentType, List<String>>();
		for (int j = 0; j < CollisionComponentType.values().length; j++) {
			CollisionComponentType currentType = CollisionComponentType.values()[j];
			List<Class<?>> listofAct = ar.getActionsWithAnnotation(currentType);
			ArrayList<String> actions = new ArrayList<String>();
			try {
				populateString(actions, listofAct);
			} catch (InputException e1) {
				System.out.println("fuck");
			}
			allActions.put(currentType, actions);
			ListView<String> viewActs = myUtilF.buildListView(actions);
			VBox listandbut = myUtilF.buildVBox(new Text(currentType.name() + "Action"), viewActs,
					myUtilF.buildButton("AddActions", e -> addAction(currentType, viewActs)));
			setListView(listandbut, j);
			initalizeListView(viewActs);
		}
	}

	private void populateString(List<String> actions, List<Class<?>> listofAct) throws InputException {
		for (int i = 0; i < listofAct.size(); i++) {
			Class<?> nextAction = listofAct.get(i);
			String act = null;
			act = nextAction.toString();
			nametoAct.put(myUtilF.getText(act), act);
			actions.add(myUtilF.getText(act));
			allAct.put(act, nextAction);
		}
	}

	private void addAction(CollisionComponentType collisionComponentType, ListView<String> viewActs) {
		CollisionComponentsHandler sideCollisionActions = null;
		if (myEntity.getComponent(ComponentType.CollisionHandler) == null) {
			sideCollisionActions = new CollisionComponentsHandler();
			System.out.println("Create handler actionwindow");
			myEntity.addComponent(sideCollisionActions);
		} else {
			System.out.println("use handler actionwindow");
			sideCollisionActions = (CollisionComponentsHandler) myEntity.getComponent(ComponentType.CollisionHandler);
		}
		SideCollisionComponent sidecollision = null;
		if (sideCollisionActions.getCollisionComponent(collisionComponentType.toString()) == null) {
			sidecollision = new SideCollisionComponent(collisionComponentType);
			sideCollisionActions.addCollisionComponent(sidecollision);
			System.out.println("Create collisioncomp actionwindow");
		} else {
			System.out.println("use collisioncomp actionwindow");
			sidecollision = sideCollisionActions.getCollisionComponent(collisionComponentType.toString());
		}
		try {
			System.out.println(allAct.get(viewActs.getSelectionModel().getSelectedItem()));
			IAction act = getAction(allAct.get(nametoAct.get(viewActs.getSelectionModel().getSelectedItem())));
			if (!(labelType.getText().toString().equals(labelType.getPromptText().toString())
					|| labelType.getText().toString().equals(""))) {
				System.out.println("add label action");
				if(addDuration){
					tc.addAction(act, myDuration);
				}else{
					sidecollision.addActionForLabel(new LabelComponent(labelType.getText()), act);
				}
			}
			if (EntityTypeList.getSelectionModel().getSelectedIndex() >= 0) {
				System.out.println("add type action");
				if(addDuration){
					tc.addAction(act, myDuration);
				}else{
				sidecollision.addActionForType(new TypeComponent(EntityTypeList.getSelectionModel().getSelectedItem()),
						act);
				}
			}
		} catch (InputException e) {
			//ALERT
		}

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

	private void initalizeListView(ListView<?> viewActs) {
		viewActs.setId("actions");
		viewActs.setMaxSize(200, 150);
	}

	private void setListView(Node viewActs, int j) {
		if (CollisionComponentType.values()[j].toString().equals("Bottom")) {
			GridPane.setConstraints(viewActs, 1, 2);
		} else if (CollisionComponentType.values()[j].toString().equals("Top")) {
			GridPane.setConstraints(viewActs, 1, 0);
		} else if (CollisionComponentType.values()[j].toString().equals("Left")) {
			GridPane.setConstraints(viewActs, 0, 1);
		} else {
			GridPane.setConstraints(viewActs, 2, 1);
		}
		root.getChildren().add(viewActs);
	}

	@Override
	public void openWindow() {
		myStage.show();
	}

}
