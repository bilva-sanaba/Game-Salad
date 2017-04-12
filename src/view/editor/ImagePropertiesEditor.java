package view.editor;

import components.IComponent;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ImagePropertiesEditor extends ComponentEditor {
	private static final String ComponentName = "ImageProperties";
	private static final String ImageProp = "Size : 0  0 px";
	
	private HBox myBox;
	private Text myLabel = new Text(ImageProp);
	private Slider myXSlider;
	private double myX;
	private Slider myYSlider;
	private double myY;
	
	public ImagePropertiesEditor() {
		System.out.println("kill yourself");
		myBox = new HBox();
		myXSlider = new Slider(10,100,1);
		myYSlider = new Slider(10,100,1);
		System.out.println("kill yourself now asshole");
		myBox.getChildren().add(myLabel);
		myBox.getChildren().add(myXSlider);
		myBox.getChildren().add(myYSlider);
		
		myXSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			myXSlider.setValue(newValue.doubleValue());
			myLabel.setText(
					String.format("Size : " + Integer.toString(newValue.intValue()) + " " + Double.valueOf(myY).intValue() + " px"));
			myX = newValue.doubleValue();
		});
		
		myYSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			myYSlider.setValue(newValue.doubleValue());
			myLabel.setText(
					String.format("Size : " +  Double.valueOf(myX).intValue() + " "+ Integer.toString(newValue.intValue()) + " px"));
			myY = newValue.doubleValue();
		});
		System.out.println(myBox);
		setInputNode(myBox);
	}

	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(ComponentName, myX, myY);
	}

	
}