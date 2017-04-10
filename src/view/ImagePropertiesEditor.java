package view;

import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ImagePropertiesEditor extends ComponentEditor {
	private static final String ImageProp = "Size";
	
	private HBox myBox;
	private Text myLabel = new Text(ImageProp);
	private Slider mySizeSlider;
	
	public ImagePropertiesEditor() {
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
