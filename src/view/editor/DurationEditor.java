package view.editor;

import components.IComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import view.UtilityFactory;

public class DurationEditor extends ComponentEditor {
	private static final String ComponentName = "Duration";

	private HBox myBox;
	private double myDuration;

	public DurationEditor(UtilityFactory utilf) {
		myBox = utilf.buildSlider(ComponentName, new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myDuration = newValue.doubleValue(); 
			}
		});
		setInputNode(myBox);
	}

	public IComponent getComponent() {
		return getCompF().getComponent(ComponentName, myDuration);
	}

}
