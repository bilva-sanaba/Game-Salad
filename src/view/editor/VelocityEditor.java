package view.editor;

import components.IComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.UtilityFactory;

public class VelocityEditor extends ComponentEditor{
	private static final String ComponentName = "Velocity";
	
	private VBox myBox;
	private double myXVelocity;
	private double myYVelocity;
	
	public VelocityEditor(UtilityFactory utilf) {
		myBox = new VBox();
		HBox xvel = utilf.buildSlider(ComponentName+"X", new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myXVelocity = newValue.doubleValue(); 
			}
		});
		HBox yvel = utilf.buildSlider(ComponentName+"Y", new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myYVelocity = newValue.doubleValue(); 
			}
		});
		myBox.getChildren().addAll(xvel,yvel);
		setInputNode(myBox);
	}
	
	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(ComponentName, myXVelocity, myYVelocity);
	}
}
