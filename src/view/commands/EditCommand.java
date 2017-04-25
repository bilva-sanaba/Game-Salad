package view.commands;

import view.GridView;
import view.ViewData;

public class EditCommand implements RightClickEvent {

private ViewData myData;
	
	public EditCommand(ViewData data){
		myData = data;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
