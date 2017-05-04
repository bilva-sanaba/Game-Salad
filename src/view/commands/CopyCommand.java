package view.commands;

import entity.Entity;
import view.GridView;
import view.ViewData;

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

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
