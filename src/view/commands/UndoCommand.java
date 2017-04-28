package view.commands;

import view.ViewData;

public class UndoCommand implements RightClickEvent {

private ViewData myData;
	
	public UndoCommand(ViewData data, double x, double y){
		myData = data;
	}
	
	@Override
	public void execute() {
		myData.undoLastEvent();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
