package view;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LabelEditor extends ComponentEditor {
	private Node myInputNode;
	
	protected void setInputNode(Node newNode){
		myInputNode = newNode;
	}
	
	public Node getInputNode() {
		return myInputNode;
	}
	private static final String LABEL = "Label";
	
	private HBox myBox;
	private Text myLabel = new Text(LABEL);
	private TextField myTextInputField;
	
	public LabelEditor() {
		myTextInputField = new TextField();
		myBox.getChildren().addAll(myLabel, myTextInputField);
		setInputNode(myBox);
	}
}
