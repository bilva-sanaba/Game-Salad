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
	private final String DEFAULT_PATH = "resources/";
	private String myFile;

	public ButtonFactory(UIView view, String file) {
		super(file);
		myView = view;
		myFile = file;
		myResources = ResourceBundle.getBundle(DEFAULT_PATH + file);
	}

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
