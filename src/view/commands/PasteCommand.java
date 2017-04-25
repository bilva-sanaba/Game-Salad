package view.commands;

import view.GridView;
import view.ViewData;

public class PasteCommand implements RightClickEvent{

private ViewData myData;
	
	public PasteCommand(ViewData data){
		myData = data;
	}
	
	@Override
	public void execute() {
		myData.pasteEntity();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
