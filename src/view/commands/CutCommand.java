package view.commands;

import entity.Entity;
import view.GridView;
import view.ViewData;

public class CutCommand implements RightClickEvent{

private ViewData myData;
private Entity myEntity;
private int savedLevel;
	
	public CutCommand(ViewData data, double x, double y){
		myData = data;
	}
	
	@Override
	public void execute() {
		myEntity = myData.getUserGridSelectedEntity();
		savedLevel = myData.getCurrentLevel();
		myData.unplaceEntity(savedLevel, myEntity);
		myData.setUserSelectedEntity(null);
		myData.addEvent(this);
	}

	@Override
	public void undo() {
		myData.placeEntity(savedLevel, myEntity);
	}

}
