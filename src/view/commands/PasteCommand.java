package view.commands;

import components.entityComponents.LocationComponent;
import entity.Entity;
import view.ViewData;

public class PasteCommand implements RightClickEvent{

private ViewData myData;
private double x;
private double y;
private Entity pastedEntity;
private int savedLevel;
	
	public PasteCommand(ViewData data, Entity entity, double xIn, double yIn){
		myData = data;
		x = xIn;
		y = yIn;
	}
	
	@Override
	public void execute() {
		savedLevel = myData.getCurrentLevel();
		pastedEntity = myData.getCopiedEntity().newCopy(myData.getPlacedEntityID());
		pastedEntity.addComponent(new LocationComponent(x, y));
		myData.placeEntity(savedLevel, pastedEntity);
		myData.addEvent(this);
	}

	@Override
	public void undo() {
		myData.unplaceEntity(savedLevel, pastedEntity);
	}
}
