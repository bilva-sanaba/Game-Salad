package gameView.tools;

import gameView.commands.AbstractCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.UtilityFactory;

public class ButtonFactory extends UtilityFactory {

	private Stage myStage;
	private String myFile;

	public ButtonFactory(String file, Stage s) {
		super(file);
		myStage = s;
		myFile = file;
	}

	/**
	 * Creates a button for the given command, and sets the properties of the button to execute the command on click
	 * @param command
	 * @return
	 */
	public Button makeButton(AbstractCommand command) {
		Button button;
		try {
			button = buildButton(command.getName(), "", myFile, null);
		} catch (Exception e) {  
			button = new Button("No Label Found"); 
		}
		button.setId(command.getName().toLowerCase());
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				command.execute(myStage);
			}
		});
		return button;
	}

}
