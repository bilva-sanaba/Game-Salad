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
import components.entityComponents.TypeComponent;
import entity.Entity;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.GUIBuilder;
import view.UtilityFactory;
import view.ViewData;

public class EntityActionWindow extends Window {
	private UtilityFactory myUtilF;
	private GridPane root;
	private Stage myStage = new Stage();
	private Entity myEntity;
	private TextField labelType;
	private ListView<EntityType> EntityTypeList;
	private Map<CollisionComponentType, List<IAction>> allActions;

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
		HBox top = myUtilF.buildHBox(new Text("Choose at Least One: "), labelType, EntityTypeList);
		setOnGrid(myEntity.getImageView(), 1, 1);
		setOnGrid(myUtilF.buildButton("MakeEntity", e -> makeEntity()),2,2);
		buildActionMaker();
		Scene myScene = new Scene(myUtilF.buildVBox(top, new ScrollPane(root)), 600, 600);
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
		return myScene;
	}

	private void makeEntity() { 
		myStage.close();
	}
	
	private void setOnGrid(Node nod, int i, int j){
		GridPane.setConstraints(nod, i, j);
		GridPane.setHalignment(nod, HPos.CENTER);
		root.getChildren().add(nod);
	}
	
	private void buildActionMaker() {
		ActionRetriever ar = new ActionRetriever();
		allActions = new HashMap<CollisionComponentType, List<IAction>>();
		for (int j = 0; j < CollisionComponentType.values().length; j++) {
			CollisionComponentType currentType = CollisionComponentType.values()[j];
			List<Class<?>> listofAct = ar.getActionsWithAnnotation(currentType);
			ArrayList<IAction> actions = new ArrayList<IAction>();
			populatelist(actions, listofAct);
			allActions.put(currentType, actions);
			ListView<IAction> viewActs = myUtilF.buildListView(actions);
			VBox listandbut = myUtilF.buildVBox(new Text(currentType.name() + "Action"), viewActs,
					myUtilF.buildButton("AddAction", e -> addAction(currentType, viewActs)));
			setListView(listandbut, j);
			initalizeListView(viewActs);
		}
	}

	private void addAction(CollisionComponentType collisionComponentType, ListView<IAction> viewActs) {
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
		if (viewActs.getSelectionModel().getSelectedIndex() >= 0) {
			if (!(labelType.getText().toString().equals(labelType.getPromptText().toString())
					|| labelType.getText().toString().equals(""))) {
				System.out.println("add label action");
				sidecollision.addActionForLabel(new LabelComponent(labelType.getText()),
						viewActs.getSelectionModel().getSelectedItem());
			}
			if (EntityTypeList.getSelectionModel().getSelectedIndex() >= 0) {
				System.out.println("add type action");
				sidecollision.addActionForType(new TypeComponent(EntityTypeList.getSelectionModel().getSelectedItem()),
						viewActs.getSelectionModel().getSelectedItem());
			}
		}
	}

	private void initalizeListView(ListView<?> viewActs) {
		viewActs.setId("actions");
		viewActs.setMaxSize(200, 150);
	}

	private void populatelist(List<IAction> actions, List<Class<?>> listofAct) {
		for (int i = 0; i < listofAct.size(); i++) {
			Class<?> nextAction = listofAct.get(i);
			IAction act = null;
			try {
				act = (IAction) nextAction.newInstance();
				actions.add(act);
				System.out.println(nextAction.getName() + " line 59" + this.getClass());
			} catch (InstantiationException | IllegalAccessException e) {
				System.out.println(nextAction.getName() + " line 60" + this.getClass());
				for (int j = 0; j < nextAction.getConstructors().length; j++){
					System.out.println("\t   " + nextAction.getConstructors()[j]);
				}
			}
		}
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
