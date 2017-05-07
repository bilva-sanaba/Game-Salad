//This entire file is part of my masterpiece
//Jack Bloomfeld
package view.commands;

import entity.Entity;
import view.UtilityFactory;
import view.ViewData;
import view.window.EntityConfigurationWindow;

/**
 * Defines the edit command for the right click menu
 * @author Jack
 *
 */

public class EditCommand implements RightClickEvent {

private ViewData myData;
private UtilityFactory util;
private Entity myEntity;
	
	public EditCommand(UtilityFactory uf, ViewData data, Entity entity, double x, double y){
		myData = data;
		util = uf;
		myEntity = entity;
	}
	
	@Override
	public void execute() {
		new EntityConfigurationWindow(util, myData, myEntity);		
	}

}
