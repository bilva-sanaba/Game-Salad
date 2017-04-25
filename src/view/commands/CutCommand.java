package view.commands;

import entity.Entity;
import view.GridView;
import view.ViewData;

public class CutCommand implements RightClickEvent{

private ViewData myData;
private Entity myEntity;
	
	public CutCommand(ViewData data){
		myData = data;
	}
	
	@Override
	public void execute() {
		myEntity = myData.getUserSelectedEntity();
		myData.unplaceEntity();
		myData.setUserSelectedEntity(null);
		myData.addEvent(this);
	}

	@Override
	public void undo() {
		myData.placeEntity(myEntity);
	}

}
