package view.editor;

import java.util.ArrayList;
import components.IComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import view.UtilityFactory;

public class MonsterEditor  extends ComponentEditor {
	private EditableComponents componentName = EditableComponents.MonsterType;
	private String[] myMMPString = {"UpAndDown"}; // Initialize array
	private ArrayList<Node> nodeList = new ArrayList<Node>();
		
		private HBox myBox;
		
		public MonsterEditor(UtilityFactory utilf) {
			myBox = new HBox();
			final ToggleGroup group = utilf.buildRadioButtonGroup("SelectMonsterMovementType", myBox);
			group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
					myMMPString = (String[]) new_toggle.getUserData();
				}
			});			
			myBox.getChildren().addAll(nodeList);
			setInputNode(myBox);
		}
		
		@Override
		public IComponent getComponent() {
			return getCompF().getComponent(componentName.toString(), myMMPString[0]);
		}
}
