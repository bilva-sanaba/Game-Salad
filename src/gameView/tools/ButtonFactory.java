package gameView.tools;

import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import gameView.UIView;
import gameView.commands.AbstractCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.UtilityFactory;

public class ButtonFactory extends UtilityFactory {

	private UIView myView;
	private ResourceBundle myResources;
	private String myFile;

	public ButtonFactory(UIView view, String file) {
		super(file);
		myView = view;
		myFile = file;
		if (myFile != null) {
			myResources = ResourceBundle.getBundle(UIView.DEFAULT_LOCATION + file);
		}
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
				command.execute(myView.getStage());
			}
		});
		return button;
	}

}
