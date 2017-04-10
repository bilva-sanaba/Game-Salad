package view.editor;

import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ImagePropertiesEditor extends ComponentEditor {
	private static final String ComponentName = "ImageProperties";
	private static final String ImageProp = "Size : 0 percent";
	
	private HBox myBox;
	private Text myLabel = new Text(ImageProp);
	private Slider mySizeSlider;
	private int mySize;
	
	public ImagePropertiesEditor() {
		System.out.println("kill yourself");
		myBox = new HBox();
		mySizeSlider = new Slider();
		System.out.println("kill yourself now asshole");
		myBox.getChildren().add(myLabel);
		myBox.getChildren().add(mySizeSlider);
		
		mySizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			mySizeSlider.setValue(newValue.intValue());
			myLabel.setText(
					String.format("Size : " + Integer.toString(newValue.intValue()) + " percent"));
			mySize = newValue.intValue();
		});
		System.out.println(myBox);
		setInputNode(myBox);
	}
	
	public IComponent getValue(){
		return mySize;
	}
	
}