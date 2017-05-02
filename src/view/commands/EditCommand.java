package view.commands;

import entity.Entity;
import view.GridView;
import view.UtilityFactory;
import view.ViewData;
import view.window.EntityConfigurationWindow;

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

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
