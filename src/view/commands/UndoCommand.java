package view.commands;

import view.ViewData;

public class UndoCommand implements RightClickEvent {

private ViewData myData;
	
	public UndoCommand(ViewData data){
		myData = data;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
