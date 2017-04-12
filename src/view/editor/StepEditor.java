package view.editor;

import components.IComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import view.UtilityFactory;

public class StepEditor extends ComponentEditor {
	private static final String ComponentName = "Step";
	
	private HBox myBox;
	private int mySteps;
	
	public StepEditor(UtilityFactory utilf) {
		myBox = utilf.buildSlider(ComponentName, new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				mySteps = newValue.intValue(); 
			}
		});
		setInputNode(myBox);
	}
	
	public IComponent getComponent() {
		return getCompF().getComponent(ComponentName, mySteps);
	}
	
}