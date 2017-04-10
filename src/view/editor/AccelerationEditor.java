package view.editor;

import components.IComponent;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AccelerationEditor extends ComponentEditor {
	private static final String ComponentName = "Acceleration";
	private static final String ACCELERATION = "Acceleration : 0 m/s/s";
	
	private HBox myBox;
	private Text myLabel = new Text(ACCELERATION);
	private Slider myAccelerationSlider;
	private int myAcceleration; 
	
	public AccelerationEditor() {
		System.out.println("kill yourself");
		myBox = new HBox();
		myAccelerationSlider = new Slider();
		System.out.println("kill yourself now asshole");
		myBox.getChildren().add(myLabel);
		myBox.getChildren().add(myAccelerationSlider);
		
		myAccelerationSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			myAccelerationSlider.setValue(newValue.intValue());
			myLabel.setText(
					String.format("Acceleration : " + Integer.toString(newValue.intValue()) + " m/s/s"));
			myAcceleration = newValue.intValue();
		});
		System.out.println(myBox);
		setInputNode(myBox);
	}
	
	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(ComponentName, myAcceleration, myAcceleration);
	}
}