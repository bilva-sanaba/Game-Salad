package view.commands;

import entity.Entity;
import view.GridView;
import view.ViewData;

public class CutCommand implements RightClickEvent{

private ViewData myData;
private Entity myEntity;
private int savedLevel;
	
	public CutCommand(ViewData data, Entity entity, double x, double y){
		myData = data;
		myEntity = entity;
	}
	
	@Override
	public void execute() {
		savedLevel = myData.getCurrentLevel();
		myData.unplaceEntity(savedLevel, myEntity);
		myData.setUserGridSelectedEntity(null);
		myData.copyEntity(myEntity);
		myData.addEvent(this);
	}

	@Override
	public void undo() {
		myData.placeEntity(savedLevel, myEntity);
	}

}
