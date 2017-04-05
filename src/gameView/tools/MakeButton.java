package gameView.tools;

import gameView.commands.AbstractCommand;
import javafx.scene.control.Button;

public class MakeButton extends Button {

	private String myText;
	private AbstractCommand myCommand;
	public MakeButton(String text, AbstractCommand com) {
		myText = text;
		myCommand = com;	
	}
}
