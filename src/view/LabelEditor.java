package view;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LabelEditor extends ComponentEditor {

	private static final String LABEL = "Label";
	
	private HBox myBox;
	private Text myLabel = new Text(LABEL);
	private TextField myTextInputField;
	
	public LabelEditor() {
		System.out.println("kill yourself");
		myBox = new HBox();
		myTextInputField = new TextField();
		System.out.println("kill yourself now asshole");
		myBox.getChildren().add(myLabel);
		myBox.getChildren().add(myTextInputField);
		System.out.println(myBox);
		setInputNode(myBox);
	}
}
