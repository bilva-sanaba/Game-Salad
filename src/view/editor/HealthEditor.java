package view.editor;

import components.IComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import view.UtilityFactory;

public class HealthEditor extends ComponentEditor {
	private EditableComponents componentName = EditableComponents.Health;
	
	private HBox myBox;
	private double myHealth;
	
	public HealthEditor(UtilityFactory utilf) {
		myBox = utilf.buildSlider(componentName.toString(), new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myHealth = newValue.doubleValue(); 
			}
		});
		setInputNode(myBox);
	}
	
	public IComponent getComponent() {
		return getCompF().getComponent(componentName.toString(), myHealth);
	}
	
}