package view.editor;

import components.IComponent;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class VelocityEditor extends ComponentEditor{
	private static final String ComponentName = "Velocity";
private static final String VELOCITY = "Velocity : 0 m/s";
	
	private HBox myBox;
	private Text myLabel = new Text(VELOCITY);
	private Slider myVelocitySlider;
	private int myVelocity;
	
	public VelocityEditor() {
		System.out.println("kill yourself");
		myBox = new HBox();
		myVelocitySlider = new Slider();
		System.out.println("kill yourself now asshole");
		myBox.getChildren().add(myLabel);
		myBox.getChildren().add(myVelocitySlider);
		
		myVelocitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			myVelocitySlider.setValue(newValue.intValue());
			myLabel.setText(
					String.format("Velocity : " + Integer.toString(newValue.intValue()) + " m/s"));
			myVelocity = newValue.intValue();
		});
		System.out.println(myBox);
		setInputNode(myBox);
	}
	
	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(ComponentName, myVelocity, 0);
	}
}
