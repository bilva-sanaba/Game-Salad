package view.editor;

import components.IComponent;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class HealthEditor extends ComponentEditor {
	private static final String ComponentName = "Health";
	private static final String HEATH = "Health : 0 hp";
	
	private HBox myBox;
	private Text myLabel = new Text(HEATH);
	private Slider myHealthSlider;
	private int myHealth;
	
	public HealthEditor() {
		System.out.println("kill yourself");
		myBox = new HBox();
		myHealthSlider = new Slider();
		System.out.println("kill yourself now asshole");
		myBox.getChildren().add(myLabel);
		myBox.getChildren().add(myHealthSlider);
		
		myHealthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			myHealthSlider.setValue(newValue.intValue());
			myLabel.setText(
					String.format("Health : " + Integer.toString(newValue.intValue()) + " hp"));
			myHealth = newValue.intValue();
		});
		System.out.println(myBox);
		setInputNode(myBox);
	}
	
	public IComponent getComponent() {
		return getCompF().getComponent(ComponentName, myHealth);
	}
	
}