package view.editor;

import components.IComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import view.UtilityFactory;

public class GoalEditor extends ComponentEditor{
	private EditableComponents componentName = EditableComponents.Goal;
	private String[] goal = {"false"}; // Initialize array
	private HBox myBox;
	private boolean myGoal;


	public GoalEditor(UtilityFactory utilf) {
		System.out.println("made goal edior");
		myBox = new HBox();
		final ToggleGroup group = utilf.buildRadioButtonGroup("SelectGoalType", myBox);
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				goal = (String[]) new_toggle.getUserData();
				myGoal = goal[0].equals("true");
			}
		});			
		setInputNode(myBox);
	}
	
	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(componentName.toString(), myGoal);
	}
}
