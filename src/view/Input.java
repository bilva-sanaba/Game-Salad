package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Input {
	private Stage myStage = new Stage();
	private HBox myBox;
	private TextField myTextInputField;
	
	public Input(UtilityFactory utilF, String label) {
		myTextInputField = utilF.buildTextField(label);
		myBox = utilF.buildHBox(myTextInputField,
				utilF.buildButton("EnterInput", e -> closeAndSend()));
	}
	
	private void closeAndSend() {
		myStage.close();
	}

	public List<String> getInput() {
		myStage.setScene(new Scene(myBox));
		myStage.showAndWait();
		return new ArrayList<String>(Arrays.asList(myTextInputField.getText().split(", ")));
	}
	
	public Node getNode() {
		return myBox;
	}
}
