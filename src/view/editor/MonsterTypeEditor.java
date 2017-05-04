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

public class MonsterTypeEditor extends ComponentEditor {
	private EditableComponents componentName = EditableComponents.MonsterType;
	private String[] myMMPString = {"LeftAndRight"}; // Initialize array
		
		private HBox myBox;
		
		public MonsterTypeEditor(UtilityFactory utilf) {
			myBox = new HBox();
			System.out.println("made");
			final ToggleGroup group = utilf.buildRadioButtonGroup("SelectMonsterTypeType", myBox);
			group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
					myMMPString = (String[]) new_toggle.getUserData();
				}
			});			
			setInputNode(myBox);
		}
		
		@Override
		public IComponent getComponent() {
			System.out.println(componentName.toString());
			return getCompF().getComponent(componentName.toString(), myMMPString[0]);
		}
}
