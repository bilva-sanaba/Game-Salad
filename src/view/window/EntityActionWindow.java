package view.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actions.ActionRetriever;
import actions.IAction;
import components.entityComponents.CollisionComponentType;
import components.entityComponents.ComponentType;
import components.entityComponents.EntityType;
import components.entityComponents.LabelComponent;
import components.entityComponents.SideCollisionComponent;
import components.entityComponents.SpriteComponent;
import components.entityComponents.TypeComponent;
import entity.Entity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.ComponentFactory;
import view.GUIBuilder;
import view.UtilityFactory;
import view.ViewData;
import view.editor.ComponentEditor;

public class EntityActionWindow extends Window {
	private UtilityFactory myUtilF;
	private ViewData myData;
	private ComponentFactory myCompF;
	private GridPane root;
	private Stage myStage = new Stage();
	private Entity myEntity;
	private TextArea labelType;
	private ListView<EntityType> EntityTypeList;
	private Map<CollisionComponentType, List<IAction>> allActions;
	private Map<CollisionComponentType, IAction> sideActions;

	public EntityActionWindow(UtilityFactory utilF, ViewData entityData, Entity myE) {
		// myCompF = new ComponentFactory();
		myUtilF = utilF;
//	    myData = entityData;
		 myEntity = myE;
		// myData.setUserSelectedEntity(myEntity);
		myStage.setScene(buildScene());
		openWindow();
	}

	private Scene buildScene() {
		root = new GridPane();
		HBox top = new HBox();
		labelType = new TextArea();
		labelType.setMaxHeight(20);
		ObservableList<EntityType> entTy = FXCollections.observableArrayList(EntityType.values());
		EntityTypeList = new ListView<EntityType>(entTy);
		EntityTypeList.setMinSize(200, 100);
		EntityTypeList.setMaxSize(200, 100);
		top.getChildren().addAll(labelType, EntityTypeList);
		ImageView ent = myEntity.getImageView();
		GridPane.setConstraints(ent, 1,1);
		root.getChildren().add(ent);
		buildActionMaker();
		VBox full = new VBox();
		full.getChildren().addAll(top, root);
		Scene myScene = new Scene(full, 600, 600);
		myScene.getStylesheets().add(GUIBuilder.RESOURCE_PACKAGE + GUIBuilder.STYLESHEET);
		return myScene;
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
			ObservableList<IAction> obsActs = FXCollections.observableArrayList(actions);
			ListView<IAction> viewActs = new ListView<IAction>(obsActs);
			VBox listandbut = new VBox();
			listandbut.getChildren().add(viewActs);
			listandbut.getChildren().add(myUtilF.buildButton("AddAction", e -> addAction(currentType, viewActs)));
			setListView(listandbut, j);
			initalizeListView(viewActs);
		}
	}

	private void addAction(CollisionComponentType collisionComponentType, ListView<IAction> viewActs) {
		SideCollisionComponent sideCollisionActions = null;
		try {
			sideCollisionActions = (SideCollisionComponent) myEntity.getComponent(ComponentType.CollisionSide);
		} catch (Exception e) {
			sideCollisionActions = new SideCollisionComponent(collisionComponentType);
		}

		sideCollisionActions.addActionForLabel(new LabelComponent(labelType.getText()),
				viewActs.getSelectionModel().getSelectedItem());
		sideCollisionActions.addActionForType(new TypeComponent(EntityTypeList.getSelectionModel().getSelectedItem()),
				viewActs.getSelectionModel().getSelectedItem());

		if (!myEntity.containsComponent(ComponentType.CollisionSide)) {
			myEntity.addComponent(sideCollisionActions);
		}
	}

	private void initalizeListView(ListView<?> viewActs) {
		viewActs.setId("actions");
		viewActs.setMinSize(200, 150);
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
			}
		}
	}

	private void setListView(Node viewActs, int j) {
		if (CollisionComponentType.values()[j].toString().equals("Right")) {
			GridPane.setConstraints(viewActs, 1, 2);
		} else if (CollisionComponentType.values()[j].toString().equals("Left")) {
			GridPane.setConstraints(viewActs, 1, 0);
		} else if (CollisionComponentType.values()[j].toString().equals("Top")) {
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
