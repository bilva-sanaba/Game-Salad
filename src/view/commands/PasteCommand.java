package view.commands;

import entity.Entity;
import view.GridView;
import view.ViewData;

public class PasteCommand implements RightClickEvent{

private ViewData myData;
private double x;
private double y;
private Entity pastedEntity;
private int savedLevel;
	
	public PasteCommand(ViewData data, double xIn, double yIn){
		myData = data;
		x = xIn;
		y = yIn;
	}
	
	@Override
	public void execute() {
		savedLevel = myData.getCurrentLevel();
		pastedEntity = myData.pasteEntity(savedLevel, x, y);
		myData.addEvent(this);
	}

	@Override
	public void undo() {
		myData.unplaceEntity(savedLevel, pastedEntity);
	}
}
