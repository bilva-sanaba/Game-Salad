package view.editor;

import components.IComponent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LabelEditor extends ComponentEditor {
	private static final String ComponentName = "Label";
	private static final String LABEL = "Name : ";
	
	private HBox myBox;
	private Text myLabel = new Text(LABEL);
	private TextField myTextInputField;
	
	public LabelEditor() {
		myBox = new HBox();
		myTextInputField = new TextField();
		myBox.getChildren().add(myLabel);
		myBox.getChildren().add(myTextInputField);
		System.out.println(myBox);
		setInputNode(myBox);
	}
	
	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(ComponentName, myTextInputField.getText());
	}
	
}