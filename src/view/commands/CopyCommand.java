package view.commands;

import view.GridView;
import view.ViewData;

public class CopyCommand implements RightClickEvent{
	
	private ViewData myData;
	
	public CopyCommand(ViewData data){
		myData = data;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
