package view.editor;

import components.IComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.UtilityFactory;

public class AccelerationEditor extends ComponentEditor {
	
	private EditableComponents componentName = EditableComponents.Acceleration;
	private VBox myBox;
	private double myXAcceleration; 
	private double myYAcceleration; 
	
	public AccelerationEditor(UtilityFactory utilf) {
		myBox = new VBox();
		HBox xaccel = utilf.buildSlider(componentName.toString()+"X", new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myXAcceleration = newValue.doubleValue(); 
			}
		});
		HBox yaccel = utilf.buildSlider(componentName.toString()+"Y", new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myYAcceleration = newValue.doubleValue(); 
			}
		});
		myBox.getChildren().addAll(xaccel,yaccel);
		setInputNode(myBox);
	}
	
	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(componentName.toString(), myXAcceleration, myYAcceleration);
	}
}