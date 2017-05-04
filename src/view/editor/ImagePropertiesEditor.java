package view.editor;

import components.IComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.UtilityFactory;

public class ImagePropertiesEditor extends ComponentEditor {
	private EditableComponents componentName = EditableComponents.ImageProperties;
	
	private VBox myBox;
	private double myX = 40;
	private double myY = 40;
	
	public ImagePropertiesEditor(UtilityFactory utilf) {
		myBox = new VBox();
		HBox xsize = utilf.buildSlider(componentName.toString()+"X", new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myX = newValue.doubleValue(); 
			}
		});
		HBox ysize = utilf.buildSlider(componentName.toString()+"Y", new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myY = newValue.doubleValue(); 
			}
		});
		myBox.getChildren().addAll(xsize,ysize);
		setInputNode(myBox);
	}

	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(componentName.toString(), myY, myX);
	}

	
}