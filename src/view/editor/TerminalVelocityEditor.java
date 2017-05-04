package view.editor;

import components.IComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.UtilityFactory;

public class TerminalVelocityEditor extends ComponentEditor {
	private EditableComponents componentName = EditableComponents.TerminalVelocity;	
	private VBox myBox;
	private double terminalXVelocity;
	private double terminalYVelocity;
	
	public TerminalVelocityEditor(UtilityFactory utilf) {
		myBox = new VBox();
		HBox xValue = utilf.buildSlider(componentName.toString()+"X", new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				terminalXVelocity = newValue.doubleValue(); 
			}
		});
		HBox yValue = utilf.buildSlider(componentName.toString()+"Y", new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				terminalYVelocity = newValue.doubleValue(); 
			}
		});
		myBox.getChildren().addAll(xValue,yValue);
		setInputNode(myBox);
	}
	
	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(componentName.toString(), terminalXVelocity, terminalYVelocity);
	}
}
