package view.commands;

import entity.Entity;
import view.GridView;
import view.ViewData;

public class RedoCommand implements RightClickEvent {

private ViewData myData;
	
	public RedoCommand(ViewData data, Entity e, double x, double y){
		myData = data;
	}
	
	@Override
	public void execute() {
		myData.redo();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
