package view.editor;

import components.IComponent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import view.UtilityFactory;

public class LabelEditor extends ComponentEditor {
	private EditableComponents componentName = EditableComponents.Label;
	private static final String LABEL = "Name : ";
	
	private HBox myBox;
	private Text myLabel = new Text(LABEL);
	private TextField myTextInputField;
	
	public LabelEditor(UtilityFactory utilf) {
		myBox = new HBox();
		myTextInputField = new TextField();
		myBox.getChildren().add(myLabel);
		myBox.getChildren().add(myTextInputField);
		setInputNode(myBox);
	}
	
	@Override
	public IComponent getComponent() {
		return getCompF().getComponent(componentName.toString(), myTextInputField.getText());
	}
	
}