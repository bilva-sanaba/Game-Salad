package view;

import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class VelocityEditor extends ComponentEditor{
private static final String VELOCITY = "Velocity";
	
	private HBox myBox;
	private Text myLabel = new Text(VELOCITY);
	private Slider mySizeSlider;
	
	public VelocityEditor() {
		System.out.println("kill yourself");
		myBox = new HBox();
		mySizeSlider = new Slider();
		System.out.println("kill yourself now asshole");
		myBox.getChildren().add(myLabel);
		myBox.getChildren().add(mySizeSlider);
		System.out.println(myBox);
		setInputNode(myBox);
	}

}
