package view.editor;

import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class VelocityEditor extends ComponentEditor{
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
	
	public int getValue(){
		return myVelocity;
	}
}

//private void createSpeedChooser() {
//slider.valueProperty().addListener((observable, oldValue, newValue) -> {
//	slider.setValue(newValue.intValue());
//	speedLabel.setText(String.format("Animation Speed : " + Integer.toString(newValue.intValue()) + " milliseconds"));
//	speed = newValue.intValue();
//});
//}