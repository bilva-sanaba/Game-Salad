package view.editor;

import components.IComponent;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ImagePropertiesEditor extends ComponentEditor {
	private static final String ComponentName = "ImageProperties";
	private static final String ImageProp = "Size : 0 px";
	
	private HBox myBox;
	private Text myLabel = new Text(ImageProp);
	private Slider mySizeSlider;
	private double mySize;
	
	public ImagePropertiesEditor() {
		System.out.println("kill yourself");
		myBox = new HBox();
		mySizeSlider = new Slider();
		System.out.println("kill yourself now asshole");
		myBox.getChildren().add(myLabel);
		myBox.getChildren().add(mySizeSlider);
		
		mySizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			mySizeSlider.setValue(newValue.doubleValue());
			myLabel.setText(
					String.format("Size : " + Double.toString(newValue.doubleValue()) + " px"));
			mySize = newValue.doubleValue();
		});
		System.out.println(myBox);
		setInputNode(myBox);
	}

	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(ComponentName, mySize, mySize);
	}

	
}