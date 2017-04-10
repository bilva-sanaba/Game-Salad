package view;

import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AccelerationEditor extends ComponentEditor {

	private static final String HEATH = "Health";
	
	private HBox myBox;
	private Text myLabel = new Text(HEATH);
	private Slider myAccelerationSlider;
	
	public AccelerationEditor() {
		System.out.println("kill yourself");
		myBox = new HBox();
		myAccelerationSlider = new Slider();
		System.out.println("kill yourself now asshole");
		myBox.getChildren().add(myLabel);
		myBox.getChildren().add(myAccelerationSlider);
		System.out.println(myBox);
		setInputNode(myBox);
	}
}
