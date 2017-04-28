package view.commands;

import view.GridView;
import view.ViewData;

public class PasteCommand implements RightClickEvent{

private ViewData myData;
private double x;
private double y;
	
	public PasteCommand(ViewData data, double xIn, double yIn){
		myData = data;
		x = xIn;
		y = yIn;
	}
	
	@Override
	public void execute() {
		myData.pasteEntity(x, y);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
