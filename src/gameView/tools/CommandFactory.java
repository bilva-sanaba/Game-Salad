package gameView.tools;

import gameView.ICommandView;
import gameView.UIView;
import gameView.commands.AbstractCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import voogasalad.util.reflection.Reflection;

public class CommandFactory {
	
	private static final String COMMAND_RESOURCE = "gameView.commands.";
	private ResourceBundle myBundle;
	private String myKey;
	
	public CommandFactory(String resource, String filePath) {
		myBundle = ResourceBundle.getBundle(filePath);
		myKey = resource;
	} 
	
	public Collection<AbstractCommand> getCommands(ICommandView s) {
		Collection<AbstractCommand> commands = new ArrayList<AbstractCommand>();
		String buttons = myBundle.getString(myKey); 
		String[] list = buttons.split(", ");
		for (String each: list) {
			AbstractCommand newCommand = (AbstractCommand) Reflection.createInstance(COMMAND_RESOURCE+each+"Command", s);
			commands.add(newCommand);
		}
		return commands;
	}
}
