//This entire file is part of my masterpiece
//Jack Bloomfeld
package view.commands;

import entity.Entity;
import view.ViewData;
/**
 * Defines the cut command for the right click menu
 * @author Jack
 *
 */

public class CutCommand implements RightClickEvent{

private ViewData myData;
private Entity myEntity;
	
	public CutCommand(ViewData data, Entity entity, double x, double y){
		myData = data;
		myEntity = entity;
	}
	
	@Override
	public void execute() {
		myData.unplaceEntity(myData.getCurrentLevel(), myEntity);
		myData.setUserGridSelectedEntity(null);
		myData.copyEntity(myEntity);
		myData.addEvent(this);
	}

}
