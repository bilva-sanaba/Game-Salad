//This entire file is part of my masterpiece
//Jack Bloomfeld
package view.commands;

import entity.Entity;
import view.ViewData;
/**
 * Defines the copy command for the right click menu
 * @author Jack
 *
 */

public class CopyCommand implements RightClickEvent {

	private ViewData myData;
	private Entity myEntity;

	public CopyCommand(ViewData data, Entity entity, double x, double y) {
		myData = data;
		myEntity = entity;
	}

	@Override
	public void execute() {
		myData.copyEntity(myEntity.newCopy(myData.getPlacedEntityID()));
	}

}
