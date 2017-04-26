package view.commands;

import entity.Entity;
import view.GridView;
import view.UtilityFactory;
import view.ViewData;
import view.window.EntityConfigurationWindow;

public class EditCommand implements RightClickEvent {

private ViewData myData;
private UtilityFactory util;
private Entity entity;
	
	public EditCommand(ViewData data, Entity e, UtilityFactory uf){
		myData = data;
		util = uf;
		entity = e;
	}
	
	@Override
	public void execute() {
		new EntityConfigurationWindow(util, myData, entity);		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
