package view.editor;

import components.IComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import view.UtilityFactory;

public class StrengthEditor extends ComponentEditor {
	private EditableComponents componentName = EditableComponents.Strength;
	private HBox myBox;
	private double myStrength;

	public StrengthEditor(UtilityFactory utilf) {
		myBox = utilf.buildSlider(componentName.toString(), new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myStrength = newValue.doubleValue(); 
			}
		});
		setInputNode(myBox);
	}

	public IComponent getComponent() {
		return getCompF().getComponent(componentName.toString(), myStrength);
	}

}
