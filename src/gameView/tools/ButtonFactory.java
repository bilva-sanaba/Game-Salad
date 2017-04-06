package gameView.tools;

import gameView.UIView;
import gameView.commands.AbstractCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonFactory {

	private UIView myView; 
	
	public ButtonFactory(UIView view) {
		myView = view;
	}
	
	public Button makeButton(AbstractCommand command) {
		Button button = new Button(command.getName());
		button.setId(command.getName().toLowerCase()); 
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {	
				command.execute(myView.getStage());
			}
		});
		return button;
	}
}
