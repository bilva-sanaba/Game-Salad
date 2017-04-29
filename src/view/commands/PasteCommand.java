package view.commands;

import entity.Entity;
import view.GridView;
import view.ViewData;

public class PasteCommand implements RightClickEvent{

private ViewData myData;
private double x;
private double y;
private Entity pastedEntity;
	
	public PasteCommand(ViewData data, double xIn, double yIn){
		myData = data;
		x = xIn;
		y = yIn;
	}
	
	@Override
	public void execute() {
		pastedEntity = myData.pasteEntity(x, y);
		myData.addEvent(this);
	}

	@Override
	public void undo() {
		myData.unplaceEntity(pastedEntity);
	}
}
